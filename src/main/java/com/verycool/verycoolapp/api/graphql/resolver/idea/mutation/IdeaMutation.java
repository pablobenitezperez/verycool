package com.verycool.verycoolapp.api.graphql.resolver.idea.mutation;

import com.verycool.verycoolapp.api.graphql.resolver.idea.requestInput.AddIdeaItemRequestInput;
import com.verycool.verycoolapp.api.graphql.resolver.idea.requestInput.CreateIdeaRequestInput;
import com.verycool.verycoolapp.application.idea.AddIdeaItemInput;
import com.verycool.verycoolapp.application.idea.CreateIdeaInput;
import com.verycool.verycoolapp.application.idea.IdeaService;
import com.verycool.verycoolapp.domain.idea.Idea;
import com.verycool.verycoolapp.domain.idea.IdeaItem;
import com.verycool.verycoolapp.domain.idea.IdeaItemType;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IdeaMutation implements GraphQLMutationResolver {

    private final IdeaService ideaService;

    @Autowired
    public IdeaMutation(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    public Idea createIdea(CreateIdeaRequestInput createIdeaRequestInput){

        String title = createIdeaRequestInput.getTitle();
        String text = createIdeaRequestInput.getText();

        CreateIdeaInput ideaInput = new CreateIdeaInput();
        ideaInput.setText(text);
        ideaInput.setTitle(title);

        return ideaService.create(ideaInput);
    }

    public Idea addIdeaItem(AddIdeaItemRequestInput addIdeaItemRequestInput){

        UUID ideaId = addIdeaItemRequestInput.getIdeaId();
        String url = addIdeaItemRequestInput.getUrl();
        IdeaItemType ideaItemType = addIdeaItemRequestInput.getIdeaItemType();

        AddIdeaItemInput ideaItemInput = new AddIdeaItemInput();
        ideaItemInput.setIdeaId(ideaId);
        ideaItemInput.setUrl(url);
        ideaItemInput.setIdeaItemType(ideaItemType);

        return ideaService.addIdeaItem(ideaItemInput);
    }
}
