package org.fjepa.app.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A News.
 */
@Entity
@Table(name = "news")
public class News implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "summary")
    private String summary;

    @Column(name = "author")
    private String author;

    @Column(name = "editor")
    private String editor;

    @Column(name = "pic")
    private String pic;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "create_time")
    private ZonedDateTime createTime;

    @Column(name = "update_time")
    private ZonedDateTime updateTime;

    @Column(name = "is_top")
    private Integer isTop;

    @Column(name = "ctype")
    private Integer ctype;

    @Column(name = "exp_date")
    private ZonedDateTime expDate;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public News title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public News summary(String summary) {
        this.summary = summary;
        return this;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAuthor() {
        return author;
    }

    public News author(String author) {
        this.author = author;
        return this;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEditor() {
        return editor;
    }

    public News editor(String editor) {
        this.editor = editor;
        return this;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getPic() {
        return pic;
    }

    public News pic(String pic) {
        this.pic = pic;
        return this;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getContent() {
        return content;
    }

    public News content(String content) {
        this.content = content;
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public News createTime(ZonedDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public News updateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsTop() {
        return isTop;
    }

    public News isTop(Integer isTop) {
        this.isTop = isTop;
        return this;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    public Integer getCtype() {
        return ctype;
    }

    public News ctype(Integer ctype) {
        this.ctype = ctype;
        return this;
    }

    public void setCtype(Integer ctype) {
        this.ctype = ctype;
    }

    public ZonedDateTime getExpDate() {
        return expDate;
    }

    public News expDate(ZonedDateTime expDate) {
        this.expDate = expDate;
        return this;
    }

    public void setExpDate(ZonedDateTime expDate) {
        this.expDate = expDate;
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
        News news = (News) o;
        if (news.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), news.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "News{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", summary='" + getSummary() + "'" +
            ", author='" + getAuthor() + "'" +
            ", editor='" + getEditor() + "'" +
            ", pic='" + getPic() + "'" +
            ", content='" + getContent() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", isTop='" + getIsTop() + "'" +
            ", ctype='" + getCtype() + "'" +
            ", expDate='" + getExpDate() + "'" +
            "}";
    }
}
