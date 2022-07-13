package org.backend.gcmd.service.AS400;

import org.backend.gcmd.entity.as400.BddsiporClientEntity;
import org.backend.gcmd.repository.AS400.BddsiportClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BddsiporClientService {
    @Autowired
    BddsiportClientRepository bddsiportClientRepository;


    public BddsiporClientEntity findByCodeConsignataire(String codeConsignataire){
        return bddsiportClientRepository.findByCodeConsignataire(codeConsignataire);
    }
}
