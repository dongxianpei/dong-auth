package com.sansei.auth.menu.bean.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author ：dongxp
 * @date ：Created in 2022/2/23 14:20
 * @description：
 * @modified By：
 * @version: $
 */
@Data
@Schema(title = "主列表返回结果")
public class EntryDTO {

    private Long page;

    private Long entryId;

    private String icon;

    private String entryName;

    private String path;
}
