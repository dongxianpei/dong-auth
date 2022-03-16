package com.sansei.auth.menu.bean.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author ：dongxp
 * @date ：Created in 2022/2/25 9:37
 * @description：
 * @modified By：
 * @version: $
 */
@Data
@Schema(title = "判断是否有权限的返参")
public class DecideDTO {
    boolean authorized;
}
