package com.sansei.auth.menu.bean.entity;

import com.sansei.data.jpa.BasePO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class MenuPO extends BasePO {

    @Id
    private Long menuId;

    private String menuNo;

    private String menuName;

    private Long parentMenuId;

    private Boolean hidden;

    private Integer sort;

    private String path;

    private String icon;

}
