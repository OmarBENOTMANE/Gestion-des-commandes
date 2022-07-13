package org.backend.gcmd.service.AS400;

import lombok.extern.log4j.Log4j2;
import org.backend.gcmd.entity.as400.LignePrestationEntity;
import org.backend.gcmd.entity.local.LigneCommandeEntity;
import org.backend.gcmd.repository.AS400.LignePrestationRepository;
import org.backend.gcmd.repository.local.LigneCommandeRepository;
import org.backend.gcmd.service.local.CommandeServiceGCMD;
import org.backend.gcmd.service.local.LigneCommandeServiceGCMD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@Transactional
public class LignePrestationService {

    @Autowired
    LignePrestationRepository lignePrestationRepository;

    @Autowired
    LigneCommandeServiceGCMD ligneCommandeServiceGCMD;

    @Autowired
    CommandeServiceGCMD commandeServiceGCMD;

    @Autowired
    org.backend.gcmd.repository.local.LignePrestationRepository lignePrestationRepositoryLocal;



    public List<LignePrestationEntity> transfertToAs400LignBp(String numDossier) {

        List<org.backend.gcmd.entity.local.LignePrestationEntity> lignePrestationEntities  = lignePrestationRepositoryLocal.findAllByNumeroDossier(numDossier , Pageable.unpaged()).getContent();
        List<LignePrestationEntity> lignePrestations = new ArrayList<>();
        log.info("pres size {}"  , lignePrestationEntities.size());
        lignePrestationEntities.forEach(ligneCommandeEntity -> {
            LignePrestationEntity lignePrestation = new LignePrestationEntity();
            lignePrestation.setNumeroBonCommande(ligneCommandeEntity.getNumeroBonCommande());
            lignePrestation.setCodeCpa(ligneCommandeEntity.getCodeCpa());
            lignePrestation.setDateDebut(ligneCommandeEntity.getDateDebut());
            lignePrestation.setDateFin(ligneCommandeEntity.getDateFin());
            lignePrestation.setHeureDebut(ligneCommandeEntity.getHeureDebut());
            lignePrestation.setHeureFin(ligneCommandeEntity.getHeureFin());
            lignePrestation.setImportExport(ligneCommandeEntity.getImportExport());
            lignePrestation.setPoids(ligneCommandeEntity.getPoids());
            lignePrestation.setQuantite1(ligneCommandeEntity.getQuantite1());
            lignePrestation.setQuantite2(ligneCommandeEntity.getQuantite2());
            lignePrestation.setCodePrestation(ligneCommandeEntity.getCodePrestation());
            lignePrestation.setNumeroDossier(ligneCommandeEntity.getNumeroDossier());
            lignePrestation.setNumeroOrdrePrestation(ligneCommandeEntity.getNumeroOrdrePrestation());
            lignePrestation.setNumeroDeclaration("0");
            lignePrestation.setCodePrestation(ligneCommandeEntity.getCodePrestation());
            lignePrestationRepository.save(lignePrestation);
            lignePrestations.add(lignePrestation);

        });

        return lignePrestations;
    }


}
