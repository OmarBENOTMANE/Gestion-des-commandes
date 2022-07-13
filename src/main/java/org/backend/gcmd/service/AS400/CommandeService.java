package org.backend.gcmd.service.AS400;

import lombok.extern.log4j.Log4j2;
import org.backend.gcmd.dto.CommandeDTO;
import org.backend.gcmd.repository.AS400.CommandeRepository;
import org.backend.gcmd.service.local.CommandeServiceGCMD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
@Log4j2
public class CommandeService {

    @Autowired
    CommandeServiceGCMD commandeServiceGCMD;

    @Autowired
    CommandeRepository commandeRepository;

    public org.backend.gcmd.entity.as400.CommandeEntity transfertToAs400Commande(String numBp) {
        CommandeDTO cmd = commandeServiceGCMD.findByNumBp(numBp);
        org.backend.gcmd.entity.as400.CommandeEntity cmdAs400 = new org.backend.gcmd.entity.as400.CommandeEntity();
        cmdAs400.setCodeCpa(cmd.getCodeCpa());
        cmdAs400.setDateExecutionCommande(0.0);
        cmdAs400.setDateCreationCommande(0.0);
        cmdAs400.setNumeroBonCommande(cmd.getNumeroCmd());
        cmdAs400.setNumeroDossier(cmd.getNumeroBulletinPrestation());

        log.info(cmdAs400);
        commandeRepository.save(cmdAs400);

        return cmdAs400;
    }
}
