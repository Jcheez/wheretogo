package com.wheretogo.backend.models;

import com.wheretogo.backend.enums.AttractionTypes;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;

@Document("users")
public class User {

    @Id
    private long chatId;
    private String userName;
    private String firstName;
    private HashMap<AttractionTypes, List<String>> options;

    public User(long chatId, HashMap<AttractionTypes, List<String>> options, String userName, String firstName) {
        this.chatId = chatId;
        this.userName = userName;
        this.firstName = firstName;
        this.options = options;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public HashMap<AttractionTypes, List<String>> getOptions() {
        return options;
    }

    public void setOptions(HashMap<AttractionTypes, List<String>> options) {
        this.options = options;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
