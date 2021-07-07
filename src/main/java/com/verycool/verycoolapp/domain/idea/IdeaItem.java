package com.verycool.verycoolapp.domain.idea;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.net.URL;
import java.util.UUID;

@Entity
public class IdeaItem {


//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
    @Id
    @Type(type = "uuid-char")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "idea_id", nullable = false)
    private Idea idea;

    //private Date creationTime;

    @Enumerated(EnumType.STRING)
    private IdeaItemType ideaItemType;

    private String url;
    //private Map<String, Object> metadata;

    private IdeaItem(){
        //Required by nHibernate
    }

    public IdeaItem(Idea idea, String url, IdeaItemType ideaItemType) {
        this.idea = idea;
        this.id = UUID.randomUUID();
        this.ideaItemType = ideaItemType;
        this.url = checkUrl(url);
    }

    private String checkUrl(String url){

        try {
            new URL(url).toURI();
            return url;
        }
        catch (Exception e){
            throw new IllegalArgumentException("Url not valid");
        }
    }
}