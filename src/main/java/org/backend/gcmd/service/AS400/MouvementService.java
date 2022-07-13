package org.backend.gcmd.service.AS400;

import org.backend.gcmd.entity.as400.MouvementEntity;
import org.backend.gcmd.repository.AS400.MouvementRepository;
import org.backend.gcmd.validator.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MouvementService {


    @Autowired
    MouvementRepository mouvementRepository;

    public List<MouvementEntity> findByNumeroEscale(Double numeroEscale) {
        Validate.notNull(numeroEscale , "numeroEscal must be not null");
        return mouvementRepository.findByNumeroEscale(numeroEscale);
    }


}
