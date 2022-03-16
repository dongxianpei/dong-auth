package com.sansei.auth.menu.bean.entity;


import com.sansei.data.jpa.BasePO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class RoleMenuRelationPO extends BasePO {

    @Id
    private Long roleMenuRelationId;

    private Long menuId;

    private Long roleId;

}
