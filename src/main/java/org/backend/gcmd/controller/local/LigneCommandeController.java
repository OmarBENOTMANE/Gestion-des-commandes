package org.backend.gcmd.controller.local;


import com.sun.xml.bind.v2.TODO;
import org.backend.gcmd.dto.LigneCommandeDTO;
import org.backend.gcmd.entity.as400.CodeMouvementEntity;
import org.backend.gcmd.entity.local.LigneCommandeEntity;
import org.backend.gcmd.entity.local.LignePrestationEntity;
import org.backend.gcmd.service.AS400.CodeMouvementService;
import org.backend.gcmd.service.local.LigneCommandeServiceGCMD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ligneCommandes")
@CrossOrigin(origins="*")
public class LigneCommandeController {

    @Autowired
    LigneCommandeServiceGCMD ligneCommandeServiceGCMD;

    @Autowired
    CodeMouvementService codeMouvementService;

    @GetMapping("{numCmd}")
    public Page<LigneCommandeDTO> findByNumCmd(@PathVariable String numCmd , Pageable page){
        return ligneCommandeServiceGCMD.findAllByNumeroBonCommande(  page , numCmd);
    }


    @PutMapping("/actions/generateCmdLine/{numCmd}")
    public void generateLigneCmdLineFormPres(@PathVariable String numCmd   , @RequestBody List<Double> numspres){
        ligneCommandeServiceGCMD.generateLineCmdFormPres(numCmd , numspres);
    }


    @GetMapping("movements")
    public Page<CodeMouvementEntity> movements(Pageable page){
        return  codeMouvementService.findAll(page);
    }


    @GetMapping("findByNumDossier/{numDossier}")
    public Page<LignePrestationEntity> byNumeroDossier(Pageable page , @PathVariable String numDossier){
        return  ligneCommandeServiceGCMD.findAllByNumeroDossier(page , numDossier);
    }


    @PutMapping
    public LigneCommandeEntity update(@RequestBody  LigneCommandeEntity lignCmd){
        return ligneCommandeServiceGCMD.update(lignCmd);
    }

    @PutMapping("actions/selected")
    public LigneCommandeEntity selected(@RequestBody LigneCommandeEntity ligneCommandeEntity){
        return ligneCommandeServiceGCMD.selected(ligneCommandeEntity);
    }

    @DeleteMapping("action/delete/{id}")
    public void  delete(@PathVariable Long id ){
         ligneCommandeServiceGCMD.delete(id);
    }

    // TODO: 6/7/2022  find by Id 
    @GetMapping("action/{id}")
    public LigneCommandeDTO update(@PathVariable Long id){
        return  ligneCommandeServiceGCMD.findById(id);
    }
    
    


}
