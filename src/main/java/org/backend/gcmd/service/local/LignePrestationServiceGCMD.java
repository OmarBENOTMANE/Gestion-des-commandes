package org.backend.gcmd.service.local;

import org.backend.gcmd.entity.local.LignePrestationEntity;
import org.backend.gcmd.repository.local.LignePrestationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class LignePrestationServiceGCMD {

    @Autowired
    LignePrestationRepository lignePrestationRepositoryLocal;

    public LignePrestationEntity findByNumeroBonCommandeAndCodePrestation(String numCmd , Double codeMvt){
        return  lignePrestationRepositoryLocal.findByNumeroBonCommandeAndCodePrestation(numCmd , codeMvt);
    }

    public LignePrestationEntity save(LignePrestationEntity lignePrestationEntity){
        return lignePrestationRepositoryLocal.save(lignePrestationEntity);
    }




}
