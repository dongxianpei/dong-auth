package com.sansei.auth.user.repository;

import com.sansei.auth.user.bean.entity.RoleActionRelationPO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author ：dongxp
 * @date ：Created in 2022/1/7 14:24
 * @description：
 * @modified By：
 * @version: $
 */
public interface RoleActionRelaRepository extends JpaRepository<RoleActionRelationPO, Long> {
    List<RoleActionRelationPO> findByRoleIdAndNamingSpaceAndDeletedFalse(Long roleId, Long namingSpace);
}
