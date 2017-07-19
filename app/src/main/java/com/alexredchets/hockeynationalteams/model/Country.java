package com.alexredchets.hockeynationalteams.model;

public class Country {

    private String name;
    private String coach;
    private String flag;
    private String captain;
    private String img_header;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public String getImg_header() {
        return img_header;
    }

    public void setImg_header(String img_header) {
        this.img_header = img_header;
    }
}
