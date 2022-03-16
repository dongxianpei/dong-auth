package com.sansei.auth.menu.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.sansei.auth.menu.bean.bo.DecideBO;
import com.sansei.auth.menu.bean.bo.EntryBO;
import com.sansei.auth.menu.bean.bo.MenuBO;
import com.sansei.auth.menu.bean.bo.MenuTreeBO;
import com.sansei.auth.menu.bean.entity.MenuPO;
import com.sansei.auth.menu.bean.entity.RoleMenuRelationPO;
import com.sansei.auth.menu.bean.mapper.MenuMapper;
import com.sansei.auth.menu.repository.MenuRepository;
import com.sansei.auth.menu.repository.RoleMenuRelationRepository;
import com.sansei.auth.menu.service.MenuService;
import com.sansei.auth.user.bean.entity.RolePO;
import com.sansei.auth.user.bean.entity.UserRoleRelationPO;
import com.sansei.auth.user.repository.RoleRepository;
import com.sansei.auth.user.repository.UserRoleRelaRepository;
import com.sansei.base.exception.SanSeiException;
import com.sansei.base.rpc.http.ResponseCode;
import com.sansei.boot.security.core.userdetails.UserDetails;
import com.sansei.tools.collect.TreeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ：dongxp
 * @date ：Created in 2022/1/21 11:22
 * @description：
 * @modified By：
 * @version: $
 */
@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final RoleRepository roleRepository;

    private final UserRoleRelaRepository userRoleRelaRepository;

    private final RoleMenuRelationRepository roleMenuRelationRepository;

    private final MenuRepository menuRepository;

    @Override
    public List<MenuTreeBO> getMenuList(Long menuId, Long userId, Long namingSpaceId) {
        List<MenuBO> menuBOs = findMenu(userId, namingSpaceId);
        List<MenuTreeBO> menuTreeBOS = MenuMapper.INSTANCE.menuBOTOTreeBO(menuBOs);
        List<MenuTreeBO> build = TreeUtils.build(menuTreeBOS, s -> Objects.equals(s.getParentId(), 0L));
        MenuTreeBO menuTreeBO = build.stream().filter(t -> Objects.equals(t.getMenuId(), menuId)).
                findFirst().orElseThrow(() ->
                        SanSeiException.valueOf(ResponseCode.DATA_NOT_FOUND.getCode(), ResponseCode.DATA_NOT_FOUND.getMessage()));

        return menuTreeBO.getChildren();
    }

    @Override
    public List<EntryBO> getEntryList(Long userId, Long namingSpaceId) {
        List<MenuBO> menuBOs = findMenu(userId, namingSpaceId);
        if (CollectionUtil.isEmpty(menuBOs)) {
            return Collections.emptyList();
        }
        List<MenuBO> mainMenu = menuBOs.stream().filter(s -> Objects.equals(s.getParentMenuId(), 0L)).collect(Collectors.toList());
        return MenuMapper.INSTANCE.menuBOtoEntryBOs(mainMenu);

    }

    @Override
    public DecideBO decide(Long menuId, UserDetails userDetails) {
        List<RoleMenuRelationPO> roleMenuRelationPOS = roleMenuRelationRepository.findByMenuIdAndNamingSpaceAndDeletedFalse(menuId, userDetails.getNamingSpaceId());
        if (CollectionUtil.isEmpty(roleMenuRelationPOS)) {
            return DecideBO.builder().authorized(false).build();
        }
        Set<Long> collect = roleMenuRelationPOS.stream().map(RoleMenuRelationPO::getRoleId).collect(Collectors.toSet());
        List<RolePO> rolePOS = roleRepository.findByids(collect, userDetails.getNamingSpaceId());
        if (CollectionUtil.isEmpty(rolePOS)) {
            return DecideBO.builder().authorized(false).build();
        }
        Set<String> collect1 = rolePOS.stream().map(RolePO::getRoleNo).collect(Collectors.toSet());
        Collection<GrantedAuthority> authorities = userDetails.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (collect1.contains(authority.getAuthority())) {
                return DecideBO.builder().authorized(true).build();
            }
        }
        return DecideBO.builder().authorized(false).build();
    }

    private List<MenuBO> findMenu(Long userId, Long namingSpaceId) {
        List<UserRoleRelationPO> userRoleRelationPOS = userRoleRelaRepository.findByAccountIdAndNamingSpaceAndDeletedFalse(userId, namingSpaceId);
        List<UserRoleRelationPO> userRoleRelationPOS1 = Optional.ofNullable(userRoleRelationPOS).
                orElseThrow(() -> SanSeiException.valueOf(ResponseCode.DATA_NOT_FOUND.getCode(), ResponseCode.DATA_NOT_FOUND.getMessage()));
        Set<Long> collect = userRoleRelationPOS1.stream().map(UserRoleRelationPO::getRoleId).collect(Collectors.toSet());
        List<RoleMenuRelationPO> roleMenuRelationPOS = roleMenuRelationRepository.findByRoleIds(collect, namingSpaceId);
        List<Long> collect1 = Optional.ofNullable(roleMenuRelationPOS).get().stream().map(RoleMenuRelationPO::getMenuId).collect(Collectors.toList());
        List<MenuPO> menuPOs = menuRepository.findByIds(collect1, namingSpaceId);
        if (CollectionUtil.isEmpty(menuPOs)) {
            return Collections.emptyList();
        }
        return MenuMapper.INSTANCE.menuPOTOBO(menuPOs);
    }
}
