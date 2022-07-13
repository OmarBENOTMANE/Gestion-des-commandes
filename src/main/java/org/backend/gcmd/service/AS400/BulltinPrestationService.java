package org.backend.gcmd.service.AS400;

import lombok.extern.log4j.Log4j2;
import org.backend.gcmd.dto.AS400.BulltinPrestationDTO;
import org.backend.gcmd.dto.CommandeDTO;
import org.backend.gcmd.entity.as400.BulltinPrestationEntity;
import org.backend.gcmd.exceptions.technical.ObjectNotFoundException;
import org.backend.gcmd.mapper.BulltinPrestationMapper;
import org.backend.gcmd.repository.AS400.BulltinPrestationRepository;
import org.backend.gcmd.service.local.CommandeServiceGCMD;
import org.backend.gcmd.validator.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@Log4j2
@Service
@Transactional
public class BulltinPrestationService {

    @Autowired
    BulltinPrestationRepository bulltinPrestationRepository;
    @Autowired
    BulltinPrestationMapper bulltinPrestationMapper;

    @Autowired
    CommandeServiceGCMD commandeServiceGCMD;


    public BulltinPrestationEntity   transfertToAs400Bp(String numBp) {
        CommandeDTO cmdDto = commandeServiceGCMD.findByNumBp( numBp);
        BulltinPrestationEntity bp = populateBpAs400(cmdDto);
        log.info(bp);

        return bulltinPrestationRepository.save(bp);

    }

    private BulltinPrestationEntity populateBpAs400(CommandeDTO cmdDto) {
        BulltinPrestationEntity bp = new BulltinPrestationEntity();
        bp.setNumeroBulletinPrestation(cmdDto.getNumeroBulletinPrestation());
        bp.setNumeroEscale(cmdDto.getNumeroEscale());
        String[] nomClient  = cmdDto.getNomClient().split("(?<=\\G.{" + 35 + "})");
        bp.setNomClient(nomClient[0]);
        bp.setCodeClient(cmdDto.getCodeClient());
        bp.setCodeNaturePrestation(cmdDto.getCodeNaturePrestation());
        bp.setCodeCpa(cmdDto.getCodeCpa());
        bp.setCodeOperation(cmdDto.getCodeOperation());
        bp.setCodeOperation1(cmdDto.getCodeOperation1());
        bp.setCodeOperation2(cmdDto.getCodeOperation2());
        bp.setCodeOperation3(cmdDto.getCodeOperation3());
        bp.setBulltinAnnule("");
        bp.setCodeCauseAnnulation("");
        bp.setNumeroFacture("");
        bp.setDateLimiteFacture(0.0);
        return bp;
    }

    public void cancel(String numBp){
        BulltinPrestationEntity bulltinPrestationEntity = findByNumeroBulletinPrestation(numBp);
        CommandeDTO commandeDTO = commandeServiceGCMD.findByNumBp(numBp);
        bulltinPrestationEntity.setBulltinAnnule(commandeDTO.getBulltinAnnule());
        bulltinPrestationEntity.setCodeCauseAnnulation(commandeDTO.getCodeCauseAnnulation());
        bulltinPrestationRepository.save(bulltinPrestationEntity);
    }

    private BulltinPrestationEntity findByNumeroBulletinPrestation(String numBp) {
        Validate.notNull(numBp  , "numBp  must be not null");
        Optional<BulltinPrestationEntity> bulltinPrestationEntity =  bulltinPrestationRepository.findByNumeroBulletinPrestation(numBp);
        if (bulltinPrestationEntity.isPresent()){
            return  bulltinPrestationEntity.get();
        }else{
            throw new ObjectNotFoundException("Bulletin de prestation AS400 non trouvé");
        }
    }

    public Page<BulltinPrestationDTO> findAll(Pageable pageable) {
       return bulltinPrestationMapper.convertToPageDto(bulltinPrestationRepository.findAll(pageable));
    }

}
