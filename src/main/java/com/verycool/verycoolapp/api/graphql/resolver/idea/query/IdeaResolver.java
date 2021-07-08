package com.verycool.verycoolapp.api.graphql.resolver.idea.query;

import com.verycool.verycoolapp.api.graphql.connection.CursorUtil;
import com.verycool.verycoolapp.application.service.idea.IdeaService;
import com.verycool.verycoolapp.domain.idea.Idea;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.relay.*;
import io.micrometer.core.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IdeaResolver implements GraphQLQueryResolver {

    private final IdeaService ideaService;
    private final CursorUtil cursorUtil;

    @Autowired
    public IdeaResolver(IdeaService ideaService, CursorUtil cursorUtil) {
        this.ideaService = ideaService;
        this.cursorUtil = cursorUtil;
    }

    public List<Idea> getIdeas() {
        return ideaService.getAll();
    }

    //https://graphql.org/learn/pagination/
    public Connection<Idea> getIdeasPaged(int first, @Nullable String cursor) {
        List<Edge<Idea>> edges = getIdeas(cursor)
                .stream()
                .map(idea -> new DefaultEdge<>(idea, cursorUtil.createCursorWith(idea.getId())))
                .limit(first)
                .collect(Collectors.toUnmodifiableList());

        var pageInfo =  new DefaultPageInfo(
                cursorUtil.getFirstCursorFrom(edges),
                cursorUtil.getLastCursorFrom(edges),
                cursorUtil.hasPreviousPage(cursor),
                cursorUtil.hasNextPage(first, edges));

        return new DefaultConnection<>(edges, pageInfo);
    }

    public List<Idea> getIdeas(String cursor) {
        if (cursor == null){
            return getIdeas();
        }
        return ideaService.getAllAfter(cursorUtil.decode(cursor));
    }
}
