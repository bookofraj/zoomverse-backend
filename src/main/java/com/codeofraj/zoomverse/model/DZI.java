package com.codeofraj.zoomverse.model;

public class DZI {

    private AccessList accessList;
    private String dziId;
    private String dziName;
    private String dziUrl;
    private String ownerId;
    private String annotations;

    public DZI(){
    }


    public AccessList getAccessList() {
        return accessList;
    }

    public void setAccessList(AccessList accessList) {
        this.accessList = accessList;
    }

    public String getDziId() {
        return dziId;
    }

    public void setDziId(String dziId) {
        this.dziId = dziId;
    }

    public String getDziName() {
        return dziName;
    }

    public void setDziName(String dziName) {
        this.dziName = dziName;
    }

    public String getDziUrl() {
        return dziUrl;
    }

    public void setDziUrl(String dziUrl) {
        this.dziUrl = dziUrl;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getAnnotations() {
        return annotations;
    }

    public void setAnnotations(String annotations) {
        this.annotations = annotations;
    }
}
