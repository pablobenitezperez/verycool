package com.verycool.verycoolapp.domain.category;

import com.verycool.verycoolapp.domain.idea.Idea;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Category {

    @Id
    @Type(type = "uuid-char")
    private UUID id;

    private String name;
    private String description;

    @OneToOne(mappedBy = "category")
    private Idea idea;

    public UUID getId() {
        return id;
    }

    private Category(){
        //Required by nHibernate
    }

    public Category(String name, String description){
        this.id = UUID.randomUUID();
        this.name = checkName(name);
        this.description = description;
    }

    private String checkName(String name){
        if(name.isEmpty()){
            throw new IllegalArgumentException("Name can't be empty");
        }
        return name;
    }
}
