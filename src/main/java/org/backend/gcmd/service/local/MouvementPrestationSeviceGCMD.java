package org.backend.gcmd.service.local;

import org.backend.gcmd.entity.local.MouvementPrestationEntity;
import org.backend.gcmd.exceptions.technical.ObjectNotFoundException;
import org.backend.gcmd.repository.local.MouvementPrestationRepository;
import org.backend.gcmd.validator.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MouvementPrestationSeviceGCMD {

    @Autowired
    MouvementPrestationRepository mouvementPrestationRepository;

    public MouvementPrestationEntity findByCodePrestation(String codePrestation) {
       return mouvementPrestationRepository.findByCodePrestation(codePrestation);
    }

    public MouvementPrestationEntity findByCodeMovement(String codeMovement) {
        Validate.notNull(codeMovement , "codeMovement must be not null");
        Optional<MouvementPrestationEntity> movementPrestation  =
                mouvementPrestationRepository.findByCodeMouvement(codeMovement);
        if (movementPrestation.isPresent()){
            return  movementPrestation.get();
        }else{
            throw new ObjectNotFoundException("il faut vérifier que le paramétrage de la " +
                    "table correspondance n’est pas trouvé  pour le code movement : " + codeMovement);
        }
    }

}
