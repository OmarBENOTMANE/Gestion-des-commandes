package org.backend.gcmd.mapper;

import org.backend.gcmd.dto.AS400.EscaleDTO;
import org.backend.gcmd.entity.as400.EscaleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class EscaleMapper {


    ModelMapper modelMapper  = new ModelMapper();

    public EscaleEntity toEntity(EscaleDTO escaleDTO){
        return modelMapper.map(escaleDTO , EscaleEntity.class);
    }

    public EscaleDTO toDto(EscaleEntity escaleEntity){
        return  modelMapper.map(escaleEntity , EscaleDTO.class);
    }

    public Page<EscaleDTO> toDtoPage(Page<EscaleEntity> escaleEntities ){
        return escaleEntities.map(this::toDto);
    }
}
