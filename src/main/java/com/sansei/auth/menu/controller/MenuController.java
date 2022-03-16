package com.sansei.auth.menu.controller;


import com.sansei.auth.menu.bean.bo.DecideBO;
import com.sansei.auth.menu.bean.bo.EntryBO;
import com.sansei.auth.menu.bean.dto.DecideDTO;
import com.sansei.auth.menu.bean.dto.EntryDTO;
import com.sansei.auth.menu.bean.mapper.MenuMapper;
import com.sansei.auth.menu.service.MenuService;
import com.sansei.base.rpc.http.SanSeiGet;
import com.sansei.boot.security.core.userdetails.CurrentUser;
import com.sansei.boot.security.core.userdetails.UserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：dongxp
 * @date ：Created in 2022/1/21 11:11
 * @description：
 * @modified By：
 * @version: $
 */
@Tag(name = "系统相关操作")
@RestController
@RequestMapping("/system")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;


    @Operation(summary = "判断是否有权限访问")
    @SanSeiGet("/auth/{menuId}/decide")
    public DecideDTO decide(@PathVariable Long menuId, @CurrentUser UserDetails userDetails) {
        DecideBO decide = menuService.decide(menuId, userDetails);
        return MenuMapper.INSTANCE.decideBOtoDTO(decide);
    }

    @Operation(summary = "获取主菜单")
    @SanSeiGet("/entry")
    public List<EntryDTO> findEntry(@AuthenticationPrincipal UserDetails userDetails) {
        List<EntryBO> menuList = menuService.getEntryList(userDetails.getUserId(), userDetails.getNamingSpaceId());
        return MenuMapper.INSTANCE.entryBOtoDTO(menuList);
    }

}
