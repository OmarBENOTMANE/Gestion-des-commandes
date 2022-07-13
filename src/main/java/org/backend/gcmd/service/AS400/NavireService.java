package org.backend.gcmd.service.AS400;

import org.backend.gcmd.dto.AS400.EscaleDTO;
import org.backend.gcmd.entity.as400.EscaleEntity;
import org.backend.gcmd.entity.as400.NavireEntity;
import org.backend.gcmd.mapper.EscaleMapper;
import org.backend.gcmd.repository.AS400.EscaleRepository;
import org.backend.gcmd.repository.AS400.NavireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NavireService {


    @Autowired
    EscaleRepository escaleRepository;
    @Autowired
    EscaleMapper escaleMapper;
    @Autowired
    NavireRepository  navireRepository;


    public Page<EscaleDTO> findAll(Pageable pageable) {
        return escaleMapper.toDtoPage(escaleRepository.findAll(pageable));
    }


    public Page<EscaleDTO> escalToBill(Pageable page) {
        return escaleMapper.toDtoPage(escaleRepository.findNavirTOBill(page));
    }


    public Page<EscaleDTO> filter(Double numeroEscale, String nomNavire, String numeroLloyd, Pageable page) {
        return  escaleMapper.toDtoPage(escaleRepository.findNavirTOBillFiltered(numeroEscale, nomNavire , numeroLloyd , page  ));
    }


}
