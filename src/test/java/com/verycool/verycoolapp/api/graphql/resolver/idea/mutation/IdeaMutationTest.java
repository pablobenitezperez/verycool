package com.verycool.verycoolapp.api.graphql.resolver.idea.mutation;

import com.verycool.verycoolapp.api.graphql.resolver.idea.requestInput.AddIdeaItemRequestInput;
import com.verycool.verycoolapp.api.graphql.resolver.idea.requestInput.CreateIdeaRequestInput;
import com.verycool.verycoolapp.application.idea.IdeaService;
import com.verycool.verycoolapp.domain.category.Category;
import com.verycool.verycoolapp.domain.idea.Idea;
import com.verycool.verycoolapp.domain.idea.IdeaItemType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

    @Test
    void createIdea_whenCreatingIdea_returnIdea() {
        when(ideaService.create(any())).thenReturn(IDEA1);

        Idea newIdea = ideaMutation.createIdea(new CreateIdeaRequestInput());

        assertSame(IDEA1.getId(), newIdea.getId());
    }

    @Test
    void addIdeaItem_whenAddingIdeaItem_returnIdea() {
        IDEA1.addIdeaItem("http://www.test.com", IdeaItemType.Image);
        when(ideaService.addIdeaItem(any())).thenReturn(IDEA1);

        Idea newIdea = ideaMutation.addIdeaItem(new AddIdeaItemRequestInput());

        assertFalse(newIdea.getIdeaItems().isEmpty());
    }
}