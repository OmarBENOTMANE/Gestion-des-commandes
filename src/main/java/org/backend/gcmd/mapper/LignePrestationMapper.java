package org.backend.gcmd.mapper;

import org.backend.gcmd.dto.LigneCommandeDTO;
import org.backend.gcmd.dto.LignePrestationDTO;
import org.backend.gcmd.entity.local.LigneCommandeEntity;
import org.backend.gcmd.entity.local.LignePrestationEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LignePrestationMapper {
    ModelMapper modelMapper  = new ModelMapper();

    public LignePrestationEntity toEntity(LignePrestationDTO lignePrestationDTO){
        return modelMapper.map(lignePrestationDTO , LignePrestationEntity.class);
    }

    public LignePrestationDTO toDto(LignePrestationEntity lignePrestationEntity){
        return  modelMapper.map(lignePrestationEntity , LignePrestationDTO.class);
    }

    public Page<LignePrestationDTO> convertToPageDto(Page<LignePrestationEntity> lignePrestationEntities) {
        return lignePrestationEntities.map(this::toDto);
    }

    public List<LignePrestationDTO> convertToDtoList(List<LignePrestationEntity> linePrestationEntities) {
        return linePrestationEntities.stream().map(this::toDto).collect(Collectors.toCollection(ArrayList::new));
    }

}
