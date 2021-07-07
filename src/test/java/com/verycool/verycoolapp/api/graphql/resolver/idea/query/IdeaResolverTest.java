package com.verycool.verycoolapp.api.graphql.resolver.idea.query;

import com.verycool.verycoolapp.api.graphql.connection.CursorUtil;
import com.verycool.verycoolapp.application.idea.IdeaService;
import com.verycool.verycoolapp.domain.category.Category;
import com.verycool.verycoolapp.domain.idea.Idea;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class IdeaResolverTest {

    private final Idea IDEA1 = new Idea("title1", "text1", new Category("name", "description"));
    private final Idea IDEA2 = new Idea("title2", "text2", new Category("name", "description"));


    @InjectMocks
    private IdeaResolver ideaResolver;
    @Mock
    private IdeaService ideaService;
    @Mock
    private CursorUtil cursorUtil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getIdeas_whenIdeasAreFoundGetIdeas_returnIdeas() {
        List<Idea> ideas = new ArrayList<>();
        ideas.add(IDEA1);
        when(ideaService.getAll()).thenReturn(ideas);

        List<Idea> newIdeas = ideaResolver.getIdeas();

        assertEquals(ideas, newIdeas);
    }

    @Test
    void getIdeas_whenNoIdeasAreFoundGetIdeas_returnEmptyList(){
        when(ideaService.getAll()).thenReturn(Collections.emptyList());

        List<Idea> newIdeas = ideaResolver.getIdeas();

        assertTrue(newIdeas.isEmpty());
    }

    @Test
    void getIdeas_whenCursorIsNull_returnAllIdeas() {
        List<Idea> ideas = new ArrayList<>();
        ideas.add(IDEA1);
        when(ideaService.getAll()).thenReturn(ideas);

        List<Idea> newIdeas = ideaResolver.getIdeas(null);

        assertEquals(newIdeas.size(), ideas.size());
    }

    @Test
    void getIdeas_whenCursorIsNotNull_returnIdeasAfterCursor() {
        List<Idea> ideas = new ArrayList<>();
        ideas.add(IDEA2);

        String cursor = Base64.getEncoder().
                encodeToString(IDEA1.getId().toString().getBytes(StandardCharsets.UTF_8));

        when(ideaService.getAllAfter(IDEA1.getId())).thenReturn(ideas);
        when(cursorUtil.decode(cursor)).thenReturn(IDEA1.getId());

        List<Idea> newIdeas = ideaResolver.getIdeas(cursor);

        assertEquals(ideas.size(), newIdeas.size());
    }
}