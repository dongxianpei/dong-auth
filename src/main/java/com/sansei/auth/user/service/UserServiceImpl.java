package com.sansei.auth.user.service;

import cn.hutool.core.collection.CollectionUtil;
import com.sansei.auth.user.bean.entity.*;
import com.sansei.auth.user.repository.*;
import com.sansei.boot.security.core.RoleGrantedAuthority;
import com.sansei.boot.security.core.userdetails.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ：dongxp
 * @date ：Created in 2022/1/7 14:01
 * @description：
 * @modified By：
 * @version: $
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserRoleRelaRepository userRoleRelaRepository;
    private final RoleActionRelaRepository roleActionRelaRepository;
    private final ActionRepository actionRepository;
    private final RoleRepository roleRepository;
    private final NamingSpaceRepository namingSpaceRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<NamingSpacePO> all = namingSpaceRepository.findAll();
        AccountPO accountPO = userRepository.findbyUserName(username, all.get(0).getNamingSpaceId());
        if (accountPO == null) {
            throw new UsernameNotFoundException("没有找到该用户");
        }
        return getUserdetails(accountPO);
    }

    private UserDetails getUserdetails(AccountPO accountPO) {
        try {
            Long namingSpace = accountPO.getNamingSpace();
            List<UserRoleRelationPO> userRoleRelationPOS = userRoleRelaRepository.findByAccountIdAndNamingSpaceAndDeletedFalse(accountPO.getUserId(), namingSpace);
            List<RoleGrantedAuthority> collect3 = new ArrayList<>();
            if (CollectionUtil.isNotEmpty(userRoleRelationPOS)) {
                List<Long> collect2 = userRoleRelationPOS.stream().map(UserRoleRelationPO::getRoleId).distinct().collect(Collectors.toList());
                List<RolePO> rolePOS = roleRepository.findByids(collect2, namingSpace);
                collect3 = rolePOS.stream().map(s -> {
                    List<RoleActionRelationPO> roleActionRelaRepositories = roleActionRelaRepository.findByRoleIdAndNamingSpaceAndDeletedFalse(s.getRoleId(), namingSpace);
                    List<Long> collect = Optional.ofNullable(roleActionRelaRepositories).get().stream().map(RoleActionRelationPO::getActionId).collect(Collectors.toList());
                    List<ActionPO> allById = actionRepository.findByIds(collect, namingSpace);
                    Set<String> collect1 = Optional.ofNullable(allById).get().stream().map(ActionPO::getActionNo).collect(Collectors.toSet());
                    return new RoleGrantedAuthority(s.getRoleNo(), collect1);
                }).collect(Collectors.toList());
            }
            UserDetails userDetails = new UserDetails(accountPO.getUserId(), accountPO.getNamingSpace(), accountPO.getUsername(), accountPO.getPassword(), collect3);
            userDetails.setNickname(accountPO.getNickname());
            return userDetails;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {

    }
}
