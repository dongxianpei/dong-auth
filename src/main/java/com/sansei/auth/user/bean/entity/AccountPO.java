package com.sansei.auth.user.bean.entity;


import com.sansei.data.jpa.BasePO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@Entity
@Table(name = "ACCOUNT")
public class AccountPO extends BasePO {
    @Id
    @Column(name = "ACCOUNT_ID")
    private Long userId;
    @Column(name = "ACCOUNT_NO")
    private String username;
    @Column(name = "NICK_NAME")
    private String nickname;

    @Column(name = "`PASSWORD`")
    private String password;
    @Column(name = "PHONE")
    private String phoneNo;
    private String mail;
    private boolean enabled;
    private String remark;
}
