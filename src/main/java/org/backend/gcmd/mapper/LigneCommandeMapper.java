package org.backend.gcmd.mapper;

import org.backend.gcmd.dto.LigneCommandeDTO;
import org.backend.gcmd.entity.local.LigneCommandeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LigneCommandeMapper {


    ModelMapper modelMapper  = new ModelMapper();

    public LigneCommandeEntity toEntity(LigneCommandeDTO ligneCommandeDTO){
        return modelMapper.map(ligneCommandeDTO , LigneCommandeEntity.class);
    }

    public LigneCommandeDTO toDto(LigneCommandeEntity ligneCommandeEntity){
        return  modelMapper.map(ligneCommandeEntity , LigneCommandeDTO.class);
    }

    public Page<LigneCommandeDTO> convertToPageDto(Page<LigneCommandeEntity> lineCommandEntities) {
        return lineCommandEntities.map(this::toDto);
    }

    public List<LigneCommandeDTO> convertToDtoList(List<LigneCommandeEntity> lineCommandEntities) {
        return lineCommandEntities.stream().map(this::toDto).collect(Collectors.toCollection(ArrayList::new));
    }

    public List<LigneCommandeEntity> convertToEntitiesList(List<LigneCommandeDTO> lineCommandDTOS) {
        return lineCommandDTOS.stream().map(this::toEntity).collect(Collectors.toCollection(ArrayList::new));
    }

}
