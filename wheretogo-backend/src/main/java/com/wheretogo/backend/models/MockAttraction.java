package com.wheretogo.backend.models;

import com.wheretogo.backend.enums.AttractionTypes;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("mockAttractions")
public class MockAttraction {

    @Id
    private String id;
    private String name;
    private int cost;
    private AttractionTypes type;

    public MockAttraction(String id, String name, int cost, AttractionTypes type) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public AttractionTypes getType() {
        return type;
    }

    public void setType(AttractionTypes type) {
        this.type = type;
    }
}
