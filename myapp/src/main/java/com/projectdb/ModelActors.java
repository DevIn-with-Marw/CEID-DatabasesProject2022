package com.projectdb;

public class ModelActors {
    int actorID;
    String actorFName, actorLName;

    public ModelActors(int actorID, String actorFName, String actorLName) {
        this.actorID = actorID;
        this.actorFName = actorFName;
        this.actorLName = actorLName;
    }

    public int getActorID() {
        return this.actorID;
    }

    public void setActorID(int actorID) {
        this.actorID = actorID;
    }

    public String getActorFName() {
        return this.actorFName;
    }

    public void setActorFName(String actorFName) {
        this.actorFName = actorFName;
    }

    public String getActorLName() {
        return this.actorLName;
    }

    public void setActorLName(String actorLName) {
        this.actorLName = actorLName;
    }

}
