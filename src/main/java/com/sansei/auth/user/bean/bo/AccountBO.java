package com.sansei.auth.user.bean.bo;

import lombok.Data;


@Data

public class AccountBO {

    private Long userId;
    private String username;
    private String nickname;
    private String password;
    private String phoneNo;
    private String mail;
    private boolean enabled;
    private String remark;
    private Long namingspace;
}
