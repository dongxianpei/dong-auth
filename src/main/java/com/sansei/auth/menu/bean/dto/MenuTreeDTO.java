package com.sansei.auth.menu.bean.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "菜单树展示")
public class MenuTreeDTO {

    @Schema(title = "菜单id")
    private Long menuId;

    @Schema(title = "菜单编码")
    private String menuNo;

    @Schema(title = "菜单名称")
    private String menuName;

    @Schema(title = "父id")
    private Long parentMenuId;

    @Schema(title = "是否隐藏")
    private Boolean hidden;

    @Schema(title = "排序")
    private Integer sort;

    @Schema(title = "路径")
    private String path;

    @Schema(title = "图标")
    private String icon;

    @Schema(title = "菜单：1，按钮：2")
    private int type = 1;

    @Schema(title = "子类")
    private List<MenuTreeDTO> children;

}