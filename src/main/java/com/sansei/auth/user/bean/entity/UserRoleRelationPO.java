package com.sansei.auth.user.bean.entity;

import com.sansei.data.jpa.BasePO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ：dongxp
 * @date ：Created in 2022/1/7 14:08
 * @description：
 * @modified By：
 * @version: $
 */
@Data
@Entity
@Table(name = "ACCOUNT_ROLE_RELATION")
public class UserRoleRelationPO extends BasePO {
    @Id
    private Long accountRoleRelationId;

    private Long accountId;

    private Long roleId;

}
