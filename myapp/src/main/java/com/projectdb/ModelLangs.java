package com.projectdb;

public class ModelLangs {
    int langsID;
    String langsName;

    public ModelLangs(int langsID, String langsName) {
        this.langsID = langsID;
        this.langsName = langsName;
    }

    public int getLangsID() {
        return this.langsID;
    }

    public void setLangsID(int langsID) {
        this.langsID = langsID;
    }

    public String getLangsName() {
        return this.langsName;
    }

    public void setLangsName(String langsName) {
        this.langsName = langsName;
    }

}
