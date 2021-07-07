package com.verycool.verycoolapp.application.idea.input;

import com.verycool.verycoolapp.domain.idea.IdeaItemType;

import java.util.UUID;

public class AddIdeaItemInput {

    private UUID ideaId;
    private String url;
    private IdeaItemType ideaItemType;

    public UUID getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(UUID ideaId) {
        this.ideaId = ideaId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public IdeaItemType getIdeaItemType() {
        return ideaItemType;
    }

    public void setIdeaItemType(IdeaItemType ideaItemType) {
        this.ideaItemType = ideaItemType;
    }
}
