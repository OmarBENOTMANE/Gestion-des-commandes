package org.backend.gcmd.service.local;

import org.backend.gcmd.dto.CommandeDTO;
import org.backend.gcmd.dto.LigneCommandeDTO;
import org.backend.gcmd.entity.as400.CodeMouvementEntity;
import org.backend.gcmd.entity.local.LignePrestationEntity;
import org.backend.gcmd.entity.local.LigneCommandeEntity;
import org.backend.gcmd.exceptions.technical.ObjectNotFoundException;
import org.backend.gcmd.mapper.LigneCommandeMapper;
import org.backend.gcmd.repository.local.LignePrestationRepository;
import org.backend.gcmd.repository.perstati.LibellePrestationRepository;
import org.backend.gcmd.repository.local.LigneCommandeRepository;
import org.backend.gcmd.service.AS400.CodeMouvementService;
import org.backend.gcmd.service.AS400.LibellePrestationService;
import org.backend.gcmd.validator.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LigneCommandeServiceGCMD {

    @Autowired
    LigneCommandeRepository ligneCommandeRepository;

    @Autowired
    CommandeServiceGCMD commandeServiceGCMD;

    @Autowired
    LibellePrestationRepository libellePrestationRepository;

    @Autowired
    LibellePrestationService libellePrestationService;

    @Autowired
    MouvementPrestationSeviceGCMD mouvementPrestationSevice;

    @Autowired
    LigneCommandeMapper lignCommandMapper;

    @Autowired
    CodeMouvementService codeMouvementService;

    @Autowired
    LignePrestationRepository lignePrestationRepository;


    public Page<LigneCommandeDTO> findAllByNumeroBonCommande(Pageable pageable , String numeroDossier) {
        Validate.notNull(numeroDossier  , "Numero Dossier mustc be not null ");
        return lignCommandMapper.convertToPageDto(ligneCommandeRepository.findAllByNumeroBonCommande(  numeroDossier  , pageable));
    }

    public List<LigneCommandeDTO> generateLineCmdFormPres(String numCmd  , List<Double>  codeMvt ){
        List<LigneCommandeEntity> lignesCmd = populateLigneCommande(numCmd, codeMvt);
        return lignCommandMapper.convertToDtoList(lignesCmd);
    }

    private List<LigneCommandeEntity> populateLigneCommande(String numCmd, List<Double> codeMovements) {
        CommandeDTO  commandeDTO  = commandeServiceGCMD.findByNumCmd(numCmd);
        List<LigneCommandeEntity> lignesCmd = new ArrayList<>();
        int i = ligneCommandeRepository.findAllByNumeroBonCommande(numCmd , Pageable.unpaged()).getContent().size();
        for (Double codeMovement : codeMovements) {
            LigneCommandeEntity ligneCommandeEntity = new LigneCommandeEntity();
            ligneCommandeEntity.setNumeroBonCommande(commandeDTO.getNumeroCmd());
            ligneCommandeEntity.setCodeCpa("DTV");
            ligneCommandeEntity.setDateDebut(0.0);
            ligneCommandeEntity.setDateFin(0.0);
            ligneCommandeEntity.setHeureDebut(0.0);
            ligneCommandeEntity.setIsSelected(true);
            ligneCommandeEntity.setHeureFin(0.0);
            ligneCommandeEntity.setImportExport("I");
            ligneCommandeEntity.setPoids(0.0);
            ligneCommandeEntity.setQuantite1(0.0);
            ligneCommandeEntity.setQuantite2(0.0);
            CodeMouvementEntity codeMouvement = codeMouvementService.findByCodeMovement(new DecimalFormat("#").format(codeMovement));
            ligneCommandeEntity.setCodePrestation(Double.valueOf(codeMouvement.getCodeMouvementNavire()));
            ligneCommandeEntity.setLibellePrestation(codeMouvement.getLibelle());
            ligneCommandeEntity.setNumeroOrdrePrestation((double) ++i);
            ligneCommandeEntity.setIsGenerated(true);
            ligneCommandeRepository.save(ligneCommandeEntity);
            lignesCmd.add(ligneCommandeEntity);
        }
        return lignesCmd;
    }


    public Page<LignePrestationEntity> findAllByNumeroDossier(Pageable pageable , String numeroDossier) {
        return lignePrestationRepository.findAllByNumeroDossier(  numeroDossier  , pageable);
    }

    public LigneCommandeEntity update(LigneCommandeEntity ligneCmd) {

        return ligneCommandeRepository.save(ligneCmd);
    }

    public LigneCommandeEntity selected(LigneCommandeEntity ligneCommandeEntity) {
        ligneCommandeEntity.setIsSelected( !(ligneCommandeEntity.getIsSelected()));
        return  ligneCommandeRepository.save(ligneCommandeEntity);
    }

    public LigneCommandeDTO findById(Long id) {
        Validate.notNull(id , "id must be not null");
        Optional<LigneCommandeEntity> lineCmdEntity= ligneCommandeRepository.findById(id);
        if (lineCmdEntity.isPresent()){
            return  lignCommandMapper.toDto(lineCmdEntity.get());
        }else{
            throw new ObjectNotFoundException("ligne de commande non trouvée");
        }

    }

    public LigneCommandeEntity toLineCmdEntity(LigneCommandeDTO cmdDTO){
        return  lignCommandMapper.toEntity(cmdDTO);
    }

    public Long  delete(Long id) {
        LigneCommandeDTO ligneCommandeDTO  = findById(id);
        ligneCommandeRepository.deleteById(id);
        return ligneCommandeDTO.getId();
    }
}