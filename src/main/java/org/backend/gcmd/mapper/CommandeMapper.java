package org.backend.gcmd.mapper;

import org.backend.gcmd.dto.CommandeDTO;
import org.backend.gcmd.entity.local.CommandeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommandeMapper {


    ModelMapper modelMapper  = new ModelMapper();

    public CommandeEntity toEntity(CommandeDTO cmdDto){
        return modelMapper.map(cmdDto , CommandeEntity.class);
    }

    public CommandeDTO toDto(CommandeEntity commandeEntity){
        return  modelMapper.map(commandeEntity , CommandeDTO.class);
    }

    public Page<CommandeDTO> convertToPageDto(Page<CommandeEntity> page) {
        return page.map(this::toDto);
    }

    public List<CommandeDTO> convertToDtoList(List<CommandeEntity> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toCollection(ArrayList::new));
    }

    public List<CommandeEntity> convertToEntitiesList(List<CommandeDTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(Collectors.toCollection(ArrayList::new));
    }


}
