package com.verycool.verycoolapp.domain.idea;

import com.verycool.verycoolapp.domain.category.Category;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Idea {

    @Id
    @Type(type = "uuid-char")
    private UUID id;

    private long creationTimeUnix;
    private String title;
    private String text;

    @OneToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "idea", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<IdeaItem> ideaItems;

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public List<IdeaItem> getIdeaItems() {
        return ideaItems;
    }

    private Idea(){
        //Required by nHibernate
    }

    public Idea(String title, String text, Category category) {
        this.id = UUID.randomUUID();
        this.creationTimeUnix = Instant.now().toEpochMilli();
        this.title = checkTitle(title);
        this.text = text;
        this.ideaItems = new ArrayList<>();
        this.category = category;
    }

    private String checkTitle(String title){
        if(title.isEmpty()){
            throw new IllegalArgumentException("Title can't be empty");
        }
        return title;
    }

    public void addIdeaItem(String url, IdeaItemType ideaItemType) {

        IdeaItem ideaItem = new IdeaItem(this,url, ideaItemType);
        this.ideaItems.add(ideaItem);
    }
}