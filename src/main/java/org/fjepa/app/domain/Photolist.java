package org.fjepa.app.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Photolist.
 */
@Entity
@Table(name = "photolist")
public class Photolist implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jhi_uid")
    private String uid;

    @Column(name = "aid")
    private String aid;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "story")
    private String story;

    @Column(name = "picurl")
    private String picurl;

    @Column(name = "sys_type")
    private String sysType;

    @Column(name = "own_type")
    private String ownType;

    @Column(name = "is_login_check")
    private Boolean isLoginCheck;

    @Column(name = "is_recomment")
    private Boolean isRecomment;

    @Column(name = "upload_time")
    private ZonedDateTime uploadTime;

    @Column(name = "orderindex")
    private Integer orderindex;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public Photolist uid(String uid) {
        this.uid = uid;
        return this;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAid() {
        return aid;
    }

    public Photolist aid(String aid) {
        this.aid = aid;
        return this;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getTitle() {
        return title;
    }

    public Photolist title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public Photolist author(String author) {
        this.author = author;
        return this;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStory() {
        return story;
    }

    public Photolist story(String story) {
        this.story = story;
        return this;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getPicurl() {
        return picurl;
    }

    public Photolist picurl(String picurl) {
        this.picurl = picurl;
        return this;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getSysType() {
        return sysType;
    }

    public Photolist sysType(String sysType) {
        this.sysType = sysType;
        return this;
    }

    public void setSysType(String sysType) {
        this.sysType = sysType;
    }

    public String getOwnType() {
        return ownType;
    }

    public Photolist ownType(String ownType) {
        this.ownType = ownType;
        return this;
    }

    public void setOwnType(String ownType) {
        this.ownType = ownType;
    }

    public Boolean isIsLoginCheck() {
        return isLoginCheck;
    }

    public Photolist isLoginCheck(Boolean isLoginCheck) {
        this.isLoginCheck = isLoginCheck;
        return this;
    }

    public void setIsLoginCheck(Boolean isLoginCheck) {
        this.isLoginCheck = isLoginCheck;
    }

    public Boolean isIsRecomment() {
        return isRecomment;
    }

    public Photolist isRecomment(Boolean isRecomment) {
        this.isRecomment = isRecomment;
        return this;
    }

    public void setIsRecomment(Boolean isRecomment) {
        this.isRecomment = isRecomment;
    }

    public ZonedDateTime getUploadTime() {
        return uploadTime;
    }

    public Photolist uploadTime(ZonedDateTime uploadTime) {
        this.uploadTime = uploadTime;
        return this;
    }

    public void setUploadTime(ZonedDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getOrderindex() {
        return orderindex;
    }

    public Photolist orderindex(Integer orderindex) {
        this.orderindex = orderindex;
        return this;
    }

    public void setOrderindex(Integer orderindex) {
        this.orderindex = orderindex;
    }
    // jhipster-needle-entity-add-getters-setters - Jhipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Photolist photolist = (Photolist) o;
        if (photolist.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), photolist.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Photolist{" +
            "id=" + getId() +
            ", uid='" + getUid() + "'" +
            ", aid='" + getAid() + "'" +
            ", title='" + getTitle() + "'" +
            ", author='" + getAuthor() + "'" +
            ", story='" + getStory() + "'" +
            ", picurl='" + getPicurl() + "'" +
            ", sysType='" + getSysType() + "'" +
            ", ownType='" + getOwnType() + "'" +
            ", isLoginCheck='" + isIsLoginCheck() + "'" +
            ", isRecomment='" + isIsRecomment() + "'" +
            ", uploadTime='" + getUploadTime() + "'" +
            ", orderindex='" + getOrderindex() + "'" +
            "}";
    }
}
