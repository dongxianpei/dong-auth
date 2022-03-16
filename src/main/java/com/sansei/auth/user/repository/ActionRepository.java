package com.sansei.auth.user.repository;

import com.sansei.auth.user.bean.entity.ActionPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

/**
 * @author ：dongxp
 * @date ：Created in 2022/1/7 14:21
 * @description：
 * @modified By：
 * @version: $
 */
public interface ActionRepository extends JpaRepository<ActionPO, Long> {

    @Query("from ActionPO where actionId in :ids and namingSpace = :namingSpace and deleted = false")
    List<ActionPO> findByIds(Collection<Long> ids, Long namingSpace);
}
