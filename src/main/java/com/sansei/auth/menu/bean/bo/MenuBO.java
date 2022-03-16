package com.sansei.auth.menu.bean.bo;

import lombok.Data;

@Data
public class MenuBO {

    private Long menuId;

    private String menuNo;

    private String menuName;

    private Long parentMenuId;

    private Boolean hidden;

    private Integer sort;

    private String path;

    private String icon;

}
