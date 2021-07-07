package com.verycool.verycoolapp.api.graphql.resolver.idea.requestInput;

import com.verycool.verycoolapp.domain.idea.IdeaItemType;

import java.util.UUID;

public class AddIdeaItemRequestInput {

    private UUID ideaId;
    private String url;
    private IdeaItemType ideaItemType;

    public UUID getIdeaId() {
        return ideaId;
    }

    public String getUrl() {
        return url;
    }

    public IdeaItemType getIdeaItemType() {
        return ideaItemType;
    }
}
