package org.backend.gcmd.service.AS400;

import org.backend.gcmd.entity.prestati.LibellePrestationEntity;
import org.backend.gcmd.exceptions.technical.ObjectNotFoundException;
import org.backend.gcmd.repository.perstati.LibellePrestationRepository;
import org.backend.gcmd.validator.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class LibellePrestationService {

    @Autowired
    LibellePrestationRepository libellePrestationRepository;

    public LibellePrestationEntity findByNumPrest(Double numPres) {
        Validate.notNull(numPres  , "num prestation must be not null ");
        Optional<LibellePrestationEntity> libellePrestationEntity = libellePrestationRepository.findByCodePrestation(numPres);
        if(libellePrestationEntity.isPresent()){
            return libellePrestationEntity.get();
        }else{
            throw new ObjectNotFoundException("libelle Prestation not found ");
        }

    }

}
