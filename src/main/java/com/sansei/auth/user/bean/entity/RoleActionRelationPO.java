package com.sansei.auth.user.bean.entity;

import com.sansei.data.jpa.BasePO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author ：dongxp
 * @date ：Created in 2022/1/7 14:15
 * @description：
 * @modified By：
 * @version: $
 */
@Data
@Entity
public class RoleActionRelationPO extends BasePO {
    @Id
    private Long roleActionRelationId;

    private Long roleId;

    private Long actionId;
}
