package com.verycool.verycoolapp.api.graphql.resolver.idea.mutation;

import com.verycool.verycoolapp.api.graphql.resolver.idea.requestInput.AddIdeaItemRequestInput;
import com.verycool.verycoolapp.api.graphql.resolver.idea.requestInput.CreateIdeaRequestInput;
import com.verycool.verycoolapp.application.idea.input.AddIdeaItemInput;
import com.verycool.verycoolapp.application.idea.input.CreateIdeaInput;
import com.verycool.verycoolapp.application.idea.IdeaService;
import com.verycool.verycoolapp.domain.category.Category;
import com.verycool.verycoolapp.domain.idea.Idea;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class IdeaMutationTest {

    private final Idea IDEA1 = new Idea("title1", "text1", new Category("name", "description"));

    @InjectMocks
    private IdeaMutation ideaMutation;
    @Mock
    private IdeaService ideaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void createIdea_whenCreatingIdea_returnIdea() {
//        CreateIdeaInput ideaInput = new CreateIdeaInput();
//        ideaInput.setText("text");
//        ideaInput.setTitle("title");
//
//        when(ideaService.create(ideaInput)).thenReturn(IDEA1);
//
//        CreateIdeaRequestInput ideaRequestInput = new CreateIdeaRequestInput();
//
//        Idea newIdea = ideaMutation.createIdea(ideaRequestInput);
//
//        assertSame(IDEA1.getId(), newIdea.getId());
//    }

//    @Test
//    void addIdeaItem_whenAddingIdeaItem_returnIdea() {
//        AddIdeaItemInput ideaItemInput = new AddIdeaItemInput();
//        ideaItemInput.setIdeaId(IDEA1.getId());
//        when(ideaService.addIdeaItem(ideaItemInput)).thenReturn(IDEA1);
//
//        AddIdeaItemRequestInput ideaItemRequestInput = new AddIdeaItemRequestInput();
//        ideaItemRequestInput.setIdeaId(IDEA1.getId());
//        Idea newIdea = ideaMutation.addIdeaItem(ideaItemRequestInput);
//
//        assertFalse(newIdea.getIdeaItems().isEmpty());
//    }
}