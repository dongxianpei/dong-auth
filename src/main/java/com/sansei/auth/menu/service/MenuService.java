package com.sansei.auth.menu.service;

import com.sansei.auth.menu.bean.bo.DecideBO;
import com.sansei.auth.menu.bean.bo.EntryBO;
import com.sansei.auth.menu.bean.bo.MenuTreeBO;
import com.sansei.boot.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @author ：dongxp
 * @date ：Created in 2022/1/21 11:22
 * @description：
 * @modified By：
 * @version: $
 */
public interface MenuService {

    List<MenuTreeBO> getMenuList(Long menuId, Long userId, Long namingSpaceId);

    List<EntryBO> getEntryList(Long userId, Long namingSpaceId);

    DecideBO decide(Long menuId, UserDetails userDetails);
}
