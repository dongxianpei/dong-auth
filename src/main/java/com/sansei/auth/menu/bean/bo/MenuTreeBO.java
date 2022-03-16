package com.sansei.auth.menu.bean.bo;

import com.sansei.tools.collect.AbstractTreeNode;
import lombok.Data;


@Data
public class MenuTreeBO extends AbstractTreeNode<MenuTreeBO> {

    private Long menuId;

    private String menuNo;

    private String menuName;

    private Long parentMenuId;

    private Boolean hidden;

    private Integer sort;

    private String path;

    private String icon;

    /**
     * 菜单：1，按钮：2
     */
    private int type = 1;

    @Override
    public Long getId() {
        return this.menuId;
    }

    @Override
    public Long getParentId() {
        return this.parentMenuId;
    }

    @Override
    public int compareTo(MenuTreeBO o) {
        return this.sort.compareTo(o.getSort());
    }
}