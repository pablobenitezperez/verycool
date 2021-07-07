package com.verycool.verycoolapp.api.graphql.resolver.idea.mutation;

import com.verycool.verycoolapp.api.graphql.resolver.idea.requestInput.AddIdeaItemRequestInput;
import com.verycool.verycoolapp.api.graphql.resolver.idea.requestInput.CreateIdeaRequestInput;
import com.verycool.verycoolapp.application.idea.AddIdeaItemInput;
import com.verycool.verycoolapp.application.idea.CreateIdeaInput;
import com.verycool.verycoolapp.application.idea.IdeaService;
import com.verycool.verycoolapp.domain.idea.Idea;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class IdeaMutationTest {

    private final Idea IDEA1 = new Idea("title1", "text1");

    @InjectMocks
    private IdeaMutation ideaMutation;
    @Mock
    private IdeaService ideaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createIdea() {
        CreateIdeaInput ideaInput = new CreateIdeaInput();
        ideaInput.setText("text");
        ideaInput.setTitle("title");

        when(ideaService.create(ideaInput)).thenReturn(IDEA1);

        CreateIdeaRequestInput ideaRequestInput = new CreateIdeaRequestInput();

        Idea newIdea = ideaMutation.createIdea(ideaRequestInput);

        assertSame(IDEA1.getId(), newIdea.getId());
    }

    @Test
    void addIdeaItem_whenAddingIdeaItem_returnIdea() {

        AddIdeaItemInput ideaItemInput = new AddIdeaItemInput();
        when(ideaService.addIdeaItem(ideaItemInput)).thenReturn(IDEA1);

        AddIdeaItemRequestInput ideaItemRequestInput = new AddIdeaItemRequestInput();
        Idea newIdea = ideaMutation.addIdeaItem(ideaItemRequestInput);

        assertFalse(newIdea.getIdeaItems().isEmpty());
    }
}