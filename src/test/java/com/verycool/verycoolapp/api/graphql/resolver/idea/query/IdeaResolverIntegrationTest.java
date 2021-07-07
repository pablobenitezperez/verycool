package com.verycool.verycoolapp.api.graphql.resolver.idea.query;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.verycool.verycoolapp.api.graphql.connection.CursorUtil;
import com.verycool.verycoolapp.application.idea.IdeaService;
import com.verycool.verycoolapp.domain.category.Category;
import com.verycool.verycoolapp.domain.idea.Idea;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

//@GraphQLTest
public class IdeaResolverIntegrationTest {

    private static final String GRAPHQL_QUERY_REQUEST_PATH = "graphql/%s.graphql";

    private final Idea IDEA1 = new Idea("title1", "text1", new Category("name", "description"));
    private final Idea IDEA2 = new Idea("title2", "text2", new Category("name", "description"));

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @MockBean
    IdeaService ideaService;
    @MockBean
    CursorUtil cursorUtil;

    @Test
    @Disabled
    public void getAll() throws Exception {
        List<Idea> ideas = new ArrayList<>();
        ideas.add(IDEA1);
        ideas.add(IDEA2);

        doReturn(ideas).when(ideaService).getAll();

        GraphQLResponse graphQLResponse = graphQLTestTemplate.postForResource(format(GRAPHQL_QUERY_REQUEST_PATH, "getIdeas"));
        assertTrue(graphQLResponse.isOk());
        assertNotNull(graphQLResponse.get("$.getIdeas.id"));
    }
}
