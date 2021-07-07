package com.verycool.verycoolapp.api.graphql.resolver.idea.requestInput;

import java.util.UUID;

public class CreateIdeaRequestInput {

    private String title;
    private String text;
    private UUID idCategory;

    public String getTitle() { return title; }
    public String getText() {
        return text;
    }
    public UUID getIdCategory() { return idCategory; }
}
