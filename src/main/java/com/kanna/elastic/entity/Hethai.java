package com.kanna.elastic.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.annotation.processing.Generated;

@Document(indexName = "hethai")
public class Hethai {

@Id
    private int id;
    private String name;
    private String place;
    private String description;
    private int peopleVisited;

    public int getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPeopleVisited() {
        return peopleVisited;
    }

    public void setPeopleVisited(int peopleVisited) {
        this.peopleVisited = peopleVisited;
    }
}
