package com.sansei.auth.menu.repository;

import com.sansei.auth.menu.bean.entity.MenuPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

/**
 * @author ：dongxp
 * @date ：Created in 2022/1/21 11:07
 * @description：
 * @modified By：
 * @version: $
 */
public interface MenuRepository extends JpaRepository<MenuPO, Long> {
    @Query("from MenuPO where menuId in :ids and namingSpace = :namingSpace and deleted=false and hidden=false")
    List<MenuPO> findByIds(Collection<Long> ids, Long namingSpace);
}
