package com.ceyhunataykan.UserJPA.entity;

import com.sun.istack.Nullable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@ApiModel(value = "User Entity Model")
public class User implements Serializable {
    @Id
    @Nullable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "User Id Field")
    private int id;
    @Column(length = 100)
    @ApiModelProperty(value = "Ad Field")
    private String ad;
    @Column(length = 100)
    @ApiModelProperty(value = "Soyad Field")
    private String soyad;
    @Column(length = 100)
    @ApiModelProperty(value = "Email Field")
    private String email;
    @Column(length = 10)
    @ApiModelProperty(value = "Tel Field")
    private String tel;

}
