package org.backend.gcmd.mapper;

import org.backend.gcmd.dto.AS400.BulltinPrestationDTO;
import org.backend.gcmd.entity.as400.BulltinPrestationEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BulltinPrestationMapper {


    ModelMapper modelMapper  = new ModelMapper();

    public BulltinPrestationEntity toEntity(BulltinPrestationDTO bulltinPrestationDTO){
        return modelMapper.map(bulltinPrestationDTO , BulltinPrestationEntity.class);
    }

    public BulltinPrestationDTO toDto(BulltinPrestationEntity bulltinPrestationEntity){
        return  modelMapper.map(bulltinPrestationEntity , BulltinPrestationDTO.class);
    }

    public Page<BulltinPrestationDTO> convertToPageDto(Page<BulltinPrestationEntity> page) {
        return page.map(this::toDto);
    }

    public List<BulltinPrestationDTO> convertToDtoList(List<BulltinPrestationEntity> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toCollection(ArrayList::new));
    }

    public List<BulltinPrestationEntity> convertToEntitiesList(List<BulltinPrestationDTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(Collectors.toCollection(ArrayList::new));
    }


}
