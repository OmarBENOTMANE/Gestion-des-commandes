package org.backend.gcmd.service.AS400;

import org.backend.gcmd.entity.as400.CodeMouvementEntity;
import org.backend.gcmd.repository.AS400.CodeMouvementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CodeMouvementService {

    @Autowired
    CodeMouvementRepository codeMouvementRepository;

    public CodeMouvementEntity findByCodeMovement(String codeMvt){
        return codeMouvementRepository.findByCodeMouvementNavire(codeMvt);
    }

    public Page<CodeMouvementEntity> findAll(Pageable page) {
       return codeMouvementRepository.findMovementNavire(page);
    }
}
