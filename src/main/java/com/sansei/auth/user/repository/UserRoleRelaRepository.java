package com.sansei.auth.user.repository;

import com.sansei.auth.user.bean.entity.UserRoleRelationPO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author ：dongxp
 * @date ：Created in 2022/1/7 14:23
 * @description：
 * @modified By：
 * @version: $
 */
public interface UserRoleRelaRepository extends JpaRepository<UserRoleRelationPO, Long> {

    List<UserRoleRelationPO> findByAccountIdAndNamingSpaceAndDeletedFalse(Long accountId, Long namingSpace);
}
