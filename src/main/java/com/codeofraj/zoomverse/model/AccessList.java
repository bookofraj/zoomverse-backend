package com.codeofraj.zoomverse.model;

public class AccessList {

    private String userId;
    private String accessType;

    public AccessList(){
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }
}
