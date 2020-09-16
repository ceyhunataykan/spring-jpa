package com.ceyhunataykan.UserJPA.entity;

import com.sun.istack.Nullable;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class User implements Serializable {
    @Id
    @Nullable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100)
    private String ad;
    @Column(length = 100)
    private String soyad;
    @Column(length = 100)
    private String email;
    @Column(length = 10)
    private String tel;

}
