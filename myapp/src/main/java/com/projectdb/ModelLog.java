package com.projectdb;

public class ModelLog {
    int log_id;
    String email;
    String datetime;
    String type;
    int success;
    String table_name;

    public ModelLog(int log_id, String email, String datetime, String type, int success, String table_name) {
        this.log_id = log_id;
        this.email = email;
        this.datetime = datetime;
        this.type = type;
        this.success = success;
        this.table_name = table_name;
    }

    public int getLog_id() {
        return this.log_id;
    }

    public void setLog_id(int log_id) {
        this.log_id = log_id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDatetime() {
        return this.datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSuccess() {
        return this.success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getTable_name() {
        return this.table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

}
