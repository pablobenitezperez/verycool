package com.verycool.verycoolapp.application.idea;

public class CreateIdeaInput {

    private String title;
    private String text;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
