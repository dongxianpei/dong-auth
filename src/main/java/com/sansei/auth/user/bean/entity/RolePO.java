package com.sansei.auth.user.bean.entity;


import com.sansei.data.jpa.BasePO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class RolePO extends BasePO {
    @Id
    private Long roleId;

    private String roleNo;

    private String roleName;

    private long roleTypeCode;
}
