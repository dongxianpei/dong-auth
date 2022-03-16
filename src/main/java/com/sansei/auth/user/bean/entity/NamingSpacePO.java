package com.sansei.auth.user.bean.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author ：dongxp
 * @date ：Created in 2022/3/1 14:56
 * @description：
 * @modified By：
 * @version: $
 */
@Entity
@Data
public class NamingSpacePO {

    @Id
    private Long namingSpaceId;

    private String namingSpaceNo;

    private String namingSpaceName;

}
