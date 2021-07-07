package com.verycool.verycoolapp.domain.idea;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Idea {


//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;

    @Id
    @Type(type = "uuid-char")
    private UUID id;

//    public long getId() {
//        return id;
//    }

    public UUID getId() {
        return id;
    }

    private long creationTimeUnix;

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    private String title;
    private String text;

    public List<IdeaItem> getIdeaItems() {
        return ideaItems;
    }

    @OneToMany(mappedBy = "idea", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<IdeaItem> ideaItems;



    private Idea(){
        //Required by nHibernate
    }

    public Idea(String title, String text) {
        this.id = UUID.randomUUID();
        this.creationTimeUnix = Instant.now().toEpochMilli();
        this.title = checkTitle(title);
        this.text = text;
        this.ideaItems = new ArrayList<>();
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