package com.api.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UserRsDTO {

    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm", timezone = "GMT-3")
    private Date created;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm", timezone = "GMT-3")
    private Date modified;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm", timezone = "GMT-3")
    private Date lastLogin;
    private boolean isActive;
    private String userToken;

    public UserRsDTO() {
    }

    public UserRsDTO(final Long id, final Date created, final Date modified, final Date lastLogin, final boolean isActive, final String userToken) {
        this.id = id;
        this.created = created;
        this.modified = modified;
        this.lastLogin = lastLogin;
        this.isActive = isActive;
        this.userToken = userToken;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(final Date created) {
        this.created = created;
    }

    public Date getModified() {
        return this.modified;
    }

    public void setModified(final Date modified) {
        this.modified = modified;
    }

    public Date getLastLogin() {
        return this.lastLogin;
    }

    public void setLastLogin(final Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(final boolean active) {
        isActive = active;
    }

    public String getUserToken() {
        return this.userToken;
    }

    public void setUserToken(final String userToken) {
        this.userToken = userToken;
    }

    @Override
    public String toString() {
        return "UserRsDTO{" +
                "id=" + id +
                ", created=" + created +
                ", modified=" + modified +
                ", lastLogin=" + lastLogin +
                ", isActive=" + isActive +
                ", userToken='" + userToken + '\'' +
                '}';
    }
}
