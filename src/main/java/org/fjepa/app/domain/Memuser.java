package org.fjepa.app.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Memuser.
 */
@Entity
@Table(name = "memuser")
public class Memuser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "truename")
    private String truename;

    @Column(name = "enname")
    private String enname;

    @Column(name = "jhi_password")
    private String password;

    @Column(name = "sex")
    private Integer sex;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "memlevel")
    private Integer memlevel;

    @Column(name = "portrait")
    private String portrait;

    @Column(name = "createtime")
    private ZonedDateTime createtime;

    @Column(name = "edittime")
    private ZonedDateTime edittime;

    @Column(name = "qqtoken")
    private String qqtoken;

    @Column(name = "wechattoken")
    private String wechattoken;

    @Column(name = "weibotoken")
    private String weibotoken;

    @Column(name = "regtype")
    private Integer regtype;

    @Lob
    @Column(name = "regphoto")
    private String regphoto;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTruename() {
        return truename;
    }

    public Memuser truename(String truename) {
        this.truename = truename;
        return this;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getEnname() {
        return enname;
    }

    public Memuser enname(String enname) {
        this.enname = enname;
        return this;
    }

    public void setEnname(String enname) {
        this.enname = enname;
    }

    public String getPassword() {
        return password;
    }

    public Memuser password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public Memuser sex(Integer sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public Memuser email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public Memuser mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getMemlevel() {
        return memlevel;
    }

    public Memuser memlevel(Integer memlevel) {
        this.memlevel = memlevel;
        return this;
    }

    public void setMemlevel(Integer memlevel) {
        this.memlevel = memlevel;
    }

    public String getPortrait() {
        return portrait;
    }

    public Memuser portrait(String portrait) {
        this.portrait = portrait;
        return this;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public ZonedDateTime getCreatetime() {
        return createtime;
    }

    public Memuser createtime(ZonedDateTime createtime) {
        this.createtime = createtime;
        return this;
    }

    public void setCreatetime(ZonedDateTime createtime) {
        this.createtime = createtime;
    }

    public ZonedDateTime getEdittime() {
        return edittime;
    }

    public Memuser edittime(ZonedDateTime edittime) {
        this.edittime = edittime;
        return this;
    }

    public void setEdittime(ZonedDateTime edittime) {
        this.edittime = edittime;
    }

    public String getQqtoken() {
        return qqtoken;
    }

    public Memuser qqtoken(String qqtoken) {
        this.qqtoken = qqtoken;
        return this;
    }

    public void setQqtoken(String qqtoken) {
        this.qqtoken = qqtoken;
    }

    public String getWechattoken() {
        return wechattoken;
    }

    public Memuser wechattoken(String wechattoken) {
        this.wechattoken = wechattoken;
        return this;
    }

    public void setWechattoken(String wechattoken) {
        this.wechattoken = wechattoken;
    }

    public String getWeibotoken() {
        return weibotoken;
    }

    public Memuser weibotoken(String weibotoken) {
        this.weibotoken = weibotoken;
        return this;
    }

    public void setWeibotoken(String weibotoken) {
        this.weibotoken = weibotoken;
    }

    public Integer getRegtype() {
        return regtype;
    }

    public Memuser regtype(Integer regtype) {
        this.regtype = regtype;
        return this;
    }

    public void setRegtype(Integer regtype) {
        this.regtype = regtype;
    }

    public String getRegphoto() {
        return regphoto;
    }

    public Memuser regphoto(String regphoto) {
        this.regphoto = regphoto;
        return this;
    }

    public void setRegphoto(String regphoto) {
        this.regphoto = regphoto;
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
        Memuser memuser = (Memuser) o;
        if (memuser.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), memuser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Memuser{" +
            "id=" + getId() +
            ", truename='" + getTruename() + "'" +
            ", enname='" + getEnname() + "'" +
            ", password='" + getPassword() + "'" +
            ", sex='" + getSex() + "'" +
            ", email='" + getEmail() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", memlevel='" + getMemlevel() + "'" +
            ", portrait='" + getPortrait() + "'" +
            ", createtime='" + getCreatetime() + "'" +
            ", edittime='" + getEdittime() + "'" +
            ", qqtoken='" + getQqtoken() + "'" +
            ", wechattoken='" + getWechattoken() + "'" +
            ", weibotoken='" + getWeibotoken() + "'" +
            ", regtype='" + getRegtype() + "'" +
            ", regphoto='" + getRegphoto() + "'" +
            "}";
    }
}
