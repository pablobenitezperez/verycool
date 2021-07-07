package com.verycool.verycoolapp.application.idea;

import com.verycool.verycoolapp.domain.idea.Idea;
import com.verycool.verycoolapp.domain.idea.IdeaItemType;
import com.verycool.verycoolapp.domain.idea.IdeaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class IdeaServiceTest {

    private final Idea IDEA1 = new Idea("title1", "text1");
    private final Idea IDEA2 = new Idea("title2", "text2");

    @InjectMocks
    private IdeaService ideaService;
    @Mock
    private IdeaRepository ideaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll_whenIdeasAreFoundGetAll_returnIdeas() {
        List<Idea> ideas = new ArrayList<>();
        ideas.add(IDEA1);
        when(ideaRepository.findAll()).thenReturn(ideas);

        List<Idea> newIdeas = ideaService.getAll();

        assertEquals(ideas, newIdeas);
    }

    @Test
    void getAll_whenNoIdeasAreFoundGetAll_returnEmptyList() {
        when(ideaRepository.findAll()).thenReturn(Collections.emptyList());

        List<Idea> newIdeas = ideaService.getAll();

        assertTrue(newIdeas.isEmpty());
    }

    @Test
    void getAllAfter_whenIdIsProvided_returnIdeasAfterThatId() {
        List<Idea> ideas = new ArrayList<>();
        ideas.add(IDEA1);
        ideas.add(IDEA2);

        when(ideaRepository.findAll()).thenReturn(ideas);

        List<Idea> newIdeas = ideaService.getAllAfter(IDEA1.getId());

        assertTrue(ideas.size() > newIdeas.size());
        assertEquals(1, newIdeas.size());
        assertSame(newIdeas.get(0).getId(), IDEA2.getId());
    }

    @Test
    void create_whenIdeaIsCreated_returnIdea() {
        CreateIdeaInput ideaInput = new CreateIdeaInput();
        ideaInput.setText("text");
        ideaInput.setTitle("title");

        when(ideaRepository.save(IDEA1)).thenReturn(IDEA1);

        Idea newIdea = ideaService.create(ideaInput);

        assertSame(newIdea.getText(), IDEA1.getText());
        assertSame(newIdea.getTitle(), IDEA1.getTitle());
        assertTrue(newIdea.getIdeaItems().isEmpty());
    }

    @Test
    void addIdeaItem_whenIdeaItemIsAdded_returnIdeaWithItem() {
        UUID id = UUID.randomUUID();

        AddIdeaItemInput ideaItemInput = new AddIdeaItemInput();
        ideaItemInput.setIdeaId(id);
        ideaItemInput.setUrl("http://www.test.com");
        ideaItemInput.setIdeaItemType(IdeaItemType.Image);

        when(ideaRepository.findById(id)).thenReturn(Optional.of(IDEA1));
        when(ideaRepository.save(IDEA1)).thenReturn(IDEA1);
        Idea newIdea = ideaService.addIdeaItem(ideaItemInput);

        assertSame(newIdea.getId() , IDEA1.getId());
        assertFalse(newIdea.getIdeaItems().isEmpty());
    }
}