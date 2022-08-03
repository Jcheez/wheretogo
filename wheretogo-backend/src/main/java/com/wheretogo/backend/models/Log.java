package com.wheretogo.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("logs")
public class Log {

    @Id
    private String id; // Unique Id of log document
    private long reqId; // Id of user who used that command
    private String command; // Telegram command
    private long unixTime; // Unix time when command was executed
    private String desc; // Result of command

    public Log(String id, long reqId, String command, long unixTime, String desc) {
        this.id = id;
        this.reqId = reqId;
        this.command = command;
        this.unixTime = unixTime;
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getReqId() {
        return reqId;
    }

    public void setReqId(long reqId) {
        this.reqId = reqId;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public long getUnixTime() {
        return unixTime;
    }

    public void setUnixTime(long unix_time) {
        this.unixTime = unix_time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
