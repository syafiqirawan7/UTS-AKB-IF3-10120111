//10120111 - Syafiq Pramana Irawan - IF3

package com.example.tugasuts_10120111.model;

import java.io.Serializable;

public class note implements Serializable {

    String id;
    String title;
    String desc;
    String date;

    public note(String id, String title, String desc, String date){
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
