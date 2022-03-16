package com.sansei.auth.menu.bean.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "按钮")
public class MenuDTO {

    private Long menuId;

    @Schema(title = "菜单代码")
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


}
