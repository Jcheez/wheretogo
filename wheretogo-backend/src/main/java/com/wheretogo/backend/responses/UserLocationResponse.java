package com.wheretogo.backend.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserLocationResponse {
    private List<String> locations;

    public UserLocationResponse(List<String> locations) {
        this.locations = locations;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }
}
