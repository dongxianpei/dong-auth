package com.sansei.auth.menu.bean.mapper;

import com.sansei.auth.menu.bean.bo.DecideBO;
import com.sansei.auth.menu.bean.bo.EntryBO;
import com.sansei.auth.menu.bean.bo.MenuBO;
import com.sansei.auth.menu.bean.bo.MenuTreeBO;
import com.sansei.auth.menu.bean.dto.DecideDTO;
import com.sansei.auth.menu.bean.dto.EntryDTO;
import com.sansei.auth.menu.bean.dto.MenuDTO;
import com.sansei.auth.menu.bean.dto.MenuTreeDTO;
import com.sansei.auth.menu.bean.entity.MenuPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author ：dongxp
 * @date ：Created in 2022/1/24 11:09
 * @description：
 * @modified By：
 * @version: $
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MenuMapper {
    MenuMapper INSTANCE = Mappers.getMapper(MenuMapper.class);

    List<MenuBO> menuPOTOBO(List<MenuPO> menuPOs);

    List<MenuDTO> menuBOTODTO(List<MenuBO> menuBOs);

    List<MenuTreeBO> menuPOTOTreeBO(List<MenuPO> menuPOs);

    List<MenuTreeBO> menuBOTOTreeBO(List<MenuBO> menuBOs);

    List<MenuTreeDTO> menuTreeBOTOTreeDTO(List<MenuTreeBO> menuTreeBOs);

    @Mappings({
            @Mapping(source = "menuId", target = "entryId"),
            @Mapping(source = "menuName", target = "entryName"),
            @Mapping(source = "menuId", target = "page")
    })
    EntryBO menuBOtoEntryBO(MenuBO menuBO);

    List<EntryBO> menuBOtoEntryBOs(List<MenuBO> menuBOs);

    List<EntryDTO> entryBOtoDTO(List<EntryBO> entryBOs);

    DecideDTO decideBOtoDTO(DecideBO decideBO);
}
