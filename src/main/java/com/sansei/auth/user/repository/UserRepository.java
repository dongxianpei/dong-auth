package com.sansei.auth.user.repository;

import com.sansei.auth.user.bean.entity.AccountPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author ：dongxp
 * @date ：Created in 2022/1/7 12:01
 * @description：
 * @modified By：
 * @version: $
 */
public interface UserRepository extends JpaRepository<AccountPO, Long> {

    @Query("from AccountPO where username= :userName and namingSpace= :namingSpaceId and deleted=false")
    AccountPO findbyUserName(String userName, Long namingSpaceId);
}
