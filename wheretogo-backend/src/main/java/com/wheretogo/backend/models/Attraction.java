package com.wheretogo.backend.models;

import com.wheretogo.backend.dto.Place;
import com.wheretogo.backend.enums.AttractionTypes;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("attractions")
public class Attraction {

    @Id
    private String id;
    private String name;
    private String photoId;
    private List<Place> locations;
    private String description;
    private String readMoreLink;
    private int cost;
    private AttractionTypes type;

    public Attraction(String id, String name, String photoId, List<Place> locations, String description, String readMoreLink, int cost, AttractionTypes type) {
        this.id = id;
        this.name = name;
        this.photoId = photoId;
        this.locations = locations;
        this.description = description;
        this.readMoreLink = readMoreLink;
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

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public List<Place> getLocations() {
        return locations;
    }

    public void setLocations(List<Place> locations) {
        this.locations = locations;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReadMoreLink() {
        return readMoreLink;
    }

    public void setReadMoreLink(String readMoreLink) {
        this.readMoreLink = readMoreLink;
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
