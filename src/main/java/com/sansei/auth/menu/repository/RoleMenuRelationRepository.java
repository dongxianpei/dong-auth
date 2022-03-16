package com.sansei.auth.menu.repository;

import com.sansei.auth.menu.bean.entity.RoleMenuRelationPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

/**
 * @author ：dongxp
 * @date ：Created in 2022/1/24 10:22
 * @description：
 * @modified By：
 * @version: $
 */
public interface RoleMenuRelationRepository extends JpaRepository<RoleMenuRelationPO, Long> {

    @Query("from RoleMenuRelationPO where roleId in :ids and namingSpace= :namingSpace and deleted =false")
    List<RoleMenuRelationPO> findByRoleIds(Collection<Long> ids, Long namingSpace);

    List<RoleMenuRelationPO> findByMenuIdAndNamingSpaceAndDeletedFalse(Long MenuId, Long NamingSpace);
}
