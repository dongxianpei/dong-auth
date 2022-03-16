package com.sansei.auth.user.repository;

import com.sansei.auth.user.bean.entity.RolePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

/**
 * @author ：dongxp
 * @date ：Created in 2022/1/7 14:17
 * @description：
 * @modified By：
 * @version: $
 */
public interface RoleRepository extends JpaRepository<RolePO, Long> {


    @Query("from RolePO where roleId in :ids and namingSpace = :namingSpace and deleted = false")
    List<RolePO> findByids(Collection<Long> ids, Long namingSpace);
}
