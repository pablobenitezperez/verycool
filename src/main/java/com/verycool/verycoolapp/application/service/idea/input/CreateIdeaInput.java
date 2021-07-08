package com.verycool.verycoolapp.application.service.idea.input;

import java.util.UUID;

public class CreateIdeaInput {

    private String title;
    private String text;
    private UUID idCategory;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIdCategory(UUID idCategory) { this.idCategory = idCategory; }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public UUID getIdCategory() { return idCategory; }
}
