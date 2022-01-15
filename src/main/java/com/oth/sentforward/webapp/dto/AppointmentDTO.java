package com.oth.sentforward.webapp.dto;

import java.sql.Timestamp;
import java.util.Date;

public class AppointmentDTO {

    private String name;

    private String date;

    private String startTime;

    private String endTime;

    private String description;

    private boolean isFulltime;

    private String participants;

    public String getParticipants() {
        return participants;
    }

    public AppointmentDTO() {
    }

    public AppointmentDTO(String name, String date, String startTime, String endTime, String description, boolean isFulltime, String participants) {
        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.isFulltime = isFulltime;
        this.participants = participants;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFulltime() {
        return isFulltime;
    }

    public void setFulltime(boolean fulltime) {
        isFulltime = fulltime;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }
}
