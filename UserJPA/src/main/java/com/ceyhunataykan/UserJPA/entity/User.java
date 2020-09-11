package com.ceyhunataykan.UserJPA.entity;

import com.sun.istack.Nullable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class User implements Serializable {

    @Id
    @Nullable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column(unique = true)
    private String tc;
    @Column(length = 100)
    private String ad;
    @Column(length = 100)
    private String soyad;
    @Column(length = 100)
    private String email;
    @Column(length = 10)
    private String tel;

    public User() {
    }

    public User(int id, String ad, String soyad, String email, String tel) {
        this.id = id;
        this.ad = ad;
        this.soyad = soyad;
        this.email = email;
        this.tel = tel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

}
