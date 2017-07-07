package com.example.checkingsystem.entity;

import java.io.Serializable;

/**
 * Created by eggyer on 2017/7/3.
 */
public class AssistantVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String uuid;
    private String timestamp;
    private Assistant assistant;

    public AssistantVo() {}

    public AssistantVo(String uuid, String timestamp, Assistant assistant) {
        this.uuid = uuid;
        this.timestamp = timestamp;
        this.assistant = assistant;
    }


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Assistant getAssistant() {
        return assistant;
    }

    public void setAssistant(Assistant assistant) {
        this.assistant = assistant;
    }
}
