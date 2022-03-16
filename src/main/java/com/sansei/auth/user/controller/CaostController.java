package com.sansei.auth.user.controller;


import com.sansei.auth.menu.bean.bo.MenuTreeBO;
import com.sansei.auth.menu.bean.dto.MenuTreeDTO;
import com.sansei.auth.menu.bean.mapper.MenuMapper;
import com.sansei.auth.menu.service.MenuService;
import com.sansei.base.rpc.http.SanSeiGet;
import com.sansei.base.rpc.http.SanSeiPost;
import com.sansei.boot.security.core.userdetails.CurrentUser;
import com.sansei.boot.security.core.userdetails.UserDetails;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：dongxp
 * @date ：Created in 2022/1/12 10:08
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class CaostController {

    private final MenuService menuService;

    @SanSeiPost(value = "/logout", consumes = {"application/x-www-form-urlencoded"})
    public void findName() {
        SecurityContextHolder.clearContext();
    }

    @Operation(summary = "获取菜单")
    @SanSeiGet("/{menuId}/menu")
    public List<MenuTreeDTO> findMenu(@PathVariable Long menuId, @CurrentUser UserDetails userDetails) {
        List<MenuTreeBO> menuList = menuService.getMenuList(menuId, userDetails.getUserId(), userDetails.getNamingSpaceId());
        return MenuMapper.INSTANCE.menuTreeBOTOTreeDTO(menuList);
    }
}
