package org.backend.gcmd.service.local;


import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;


import lombok.extern.log4j.Log4j2;
import org.backend.gcmd.dto.CommandeDTO;
import org.backend.gcmd.dto.LigneCommandeDTO;
import org.backend.gcmd.entity.as400.*;
import org.backend.gcmd.entity.local.LignePrestationEntity;
import org.backend.gcmd.entity.local.CommandeEntity;
import org.backend.gcmd.entity.local.LigneCommandeEntity;
import org.backend.gcmd.entity.local.MouvementPrestationEntity;
import org.backend.gcmd.enums.*;
import org.backend.gcmd.exceptions.business.IllegalStatusException;
import org.backend.gcmd.exceptions.technical.AlreadyExistsException;
import org.backend.gcmd.exceptions.technical.ObjectNotFoundException;
import org.backend.gcmd.mapper.CommandeMapper;
import org.backend.gcmd.repository.AS400.BulltinPrestationRepository;
import org.backend.gcmd.repository.AS400.NavireRepository;
import org.backend.gcmd.repository.AS400.EscaleRepository;
import org.backend.gcmd.repository.local.CommandeRepository;
import org.backend.gcmd.repository.local.LigneCommandeRepository;
import org.backend.gcmd.service.AS400.*;
import org.backend.gcmd.service.AS400.LignePrestationService;
import org.backend.gcmd.validator.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@Transactional
public class CommandeServiceGCMD {

	@Autowired
	MouvementPrestationSeviceGCMD mouvementPrestationService;

	@Autowired
	CommandeRepository commandeRepository;

	@Autowired
	LigneCommandeServiceGCMD ligneCommandeServiceGCMD;

	@Autowired
	EscaleRepository escaleRepository;

	@Autowired
	MouvementService mouvementService;

	@Autowired
	BulltinPrestationService bulltinPrestationService;

	@Autowired
	CommandeService commandeService;

	@Autowired
	LignePrestationService lignePrestationService;


	@Autowired
	LigneCommandeRepository ligneCommandeRepository;

	@Autowired
	EscaleClientService escaleClientService;

	@Autowired
	IndexGeneratorService indexGeneratorService;

	@Autowired
	CommandeMapper commandeMapper;

	@Autowired
	BulltinPrestationRepository bulltinPrestationRepository;

	@Autowired
	NavireRepository navireRepository;

	@Autowired
	LignePrestationServiceGCMD lignePrestationServiceGCMD;

	@Autowired
	BddsiporClientService bddsiporClientService;




	public Page<CommandeDTO> findAll(Pageable page) {
		return commandeMapper.convertToPageDto(commandeRepository.findAllByIsDeletedFalseAndIsGeneratedFalse(page));

	}

	public Page<CommandeDTO> findHistory(Pageable page) {
		return commandeMapper.convertToPageDto(commandeRepository.findAllByIsGeneratedTrue(page));

	}

	public Page<CommandeDTO> cmdToBill(Pageable page){
		return commandeMapper.convertToPageDto(commandeRepository.cmdToBill(page));
	}

	public  Page<CommandeDTO> findBP( Pageable page) {
		return commandeMapper.convertToPageDto(commandeRepository.findAllGeneratedAndNotSent( page));
	}

	public Page<CommandeDTO> findBpSent(Pageable page) {
		return commandeMapper.convertToPageDto(commandeRepository.findAllBySent(page));
	}

	public Page<CommandeDTO> findAllPreCanceled(Pageable page){
		return commandeMapper.convertToPageDto(commandeRepository.findAllPreCanceled(page));
	}

	public Page<CommandeDTO> findAllPreValidated(Pageable page){
		return commandeMapper.convertToPageDto(commandeRepository.findAllPreValidated(page));
	}

	public CommandeDTO update(CommandeDTO cmdDTO) {
		CommandeEntity cmd  = commandeMapper.toEntity(cmdDTO);
		return  commandeMapper.toDto(commandeRepository.save(cmd));
	}


	public CommandeDTO findByNumCmd(String numCmd){
		Validate.notNull(numCmd , "numCmd must be not null");
		Optional<CommandeEntity> cmd   = commandeRepository.findByNumeroCmd(numCmd);
		if (cmd.isPresent()){
			return commandeMapper.toDto(cmd.get());
		}else{
			throw new ObjectNotFoundException("commande non trouvée ");
		}
	}

	public CommandeDTO findByNumBp(String numBp){
		Validate.notNull(numBp , "numBp must be not null ");
		Optional<CommandeEntity> cmd   = commandeRepository.findByNumeroBulletinPrestation(numBp);
		if (cmd.isPresent()){
			return commandeMapper.toDto(cmd.get());
		}else{
			throw new ObjectNotFoundException("bulletin de prestation non trouvé");
		}
	}


	public CommandeDTO send(String numBP) {
		CommandeDTO bp  = findByNumBp(numBP);
		if (bp.getStatus() == BpStatusEnum.VALIDATED) {
			bulltinPrestationService.transfertToAs400Bp(numBP);
			commandeService.transfertToAs400Commande(numBP);
			lignePrestationService.transfertToAs400LignBp(numBP);
			commandeRepository.sent(numBP);
			return findByNumBp(numBP);
		}
		throw new IllegalStatusException("Satut non accepeté");

	}

	public CommandeDTO generateCmd(Double numEscale) {
		Optional<CommandeEntity> command  = commandeRepository.findByNumeroEscale(numEscale);
		if (!command.isPresent() || command.get().getStatus() == BpStatusEnum.CANCELED){
			EscaleClientEntity escaleClientEntity = escaleClientService.findByNumEscal(numEscale);
			BddsiporClientEntity client = null;
			if(escaleClientEntity != null){
				String codeClient = escaleClientEntity.getCodeClient().trim();
				 client = bddsiporClientService.findByCodeConsignataire(codeClient);
			}
			CommandeEntity commande = populateCommandeEntity(numEscale, client);
			CommandeEntity savedCmd = commandeRepository.save(commande);
			populateCMDLines(numEscale, savedCmd);
			return commandeMapper.toDto(savedCmd);
		}else{
			throw new AlreadyExistsException("Commande Déjà generée");
		}
	}

	private void populateCMDLines(Double numEscale, CommandeEntity command) {
		List<MouvementEntity> movements = mouvementService.findByNumeroEscale(numEscale);

		int i = 1;
		for (MouvementEntity movement : movements) {
			LigneCommandeEntity ligneCommandeEntity = new LigneCommandeEntity();
			ligneCommandeEntity.setNumeroBonCommande(command.getNumeroCmd());
			ligneCommandeEntity.setCodeCpa("DTV");
			Double codeMvt = Double.valueOf(movement.getCodeMouvementNavire());
			ligneCommandeEntity.setCodePrestation(codeMvt);
			MouvementPrestationEntity movementPrestation = mouvementPrestationService.findByCodeMovement(doubleFormat(codeMvt));
			ligneCommandeEntity.setLibellePrestation("NA");
			ligneCommandeEntity.setLibellePrestation(movementPrestation.getLibelleMouvement());
			ligneCommandeEntity.setDateDebut(movement.getDate());
			ligneCommandeEntity.setDateFin(movement.getDate());
			ligneCommandeEntity.setHeureDebut(movement.getHeure());
			ligneCommandeEntity.setHeureFin(movement.getHeure());
			ligneCommandeEntity.setIsSelected(true);
			ligneCommandeEntity.setImportExport(ImportExportEnum.IMPORT.getValue());
			ligneCommandeEntity.setPoids(0.0);
			ligneCommandeEntity.setQuantite1(0.0);
			ligneCommandeEntity.setQuantite2(0.0);
			ligneCommandeEntity.setNumeroOrdrePrestation((double) i++);
			ligneCommandeRepository.save(ligneCommandeEntity);
		}
	}

	private CommandeEntity populateCommandeEntity(Double numEscale, BddsiporClientEntity client) {
		CommandeEntity command = new CommandeEntity();
		String numCmd = indexGeneratorService.generateNumCMD(KeyGenertorEnum.CMD);
		if(client == null){
			command.setNomClient("NA");
			command.setCodeClient("NA");

		}else {
			command.setNomClient(client.getNomClient());
			command.setCodeClient(client.getCodeConsignataire());
		}
		EscaleEntity escaleEntity  = escaleRepository.findByNumeroEscale(numEscale);
		NavireEntity navire  = navireRepository.findByNumeroLloyd(escaleEntity.getNumeroLloyd());
		// TODO: 6/7/2022 create navire with the shipLength in local
	//	command.setShipLength(navire.getLongeurNavire());
		command.setShipLength(123.0);
		command.setNumeroBulletinPrestation(null);
		command.setNumeroCmd(numCmd);
		command.setNumeroEscale(numEscale);
		command.setStatus(BpStatusEnum.INITIAL);
		command.setCodeNaturePrestation(NaturePrestationEnum.NAVIRE.getValue());
		command.setCodeCpa("DTV");
		command.setIsGenerated(false);
		command.setCodeOperation("0");
		command.setCodeOperation1("O");
		command.setCodeOperation2("");
		command.setCodeOperation3("");
		command.setBulltinAnnule(null);
		command.setCodeCauseAnnulation(null);
		command.setNumeroFacture(null);
		command.setDateLimiteFacture(null);
		return command;
	}

	public CommandeDTO transformTOBp(String numCmd) {
		CommandeEntity cmd = commandeMapper.toEntity(findByNumCmd(numCmd));
			if (Boolean.FALSE.equals(cmd.getIsGenerated())) {
				String numBp = indexGeneratorService.generateNumBP(KeyGenertorEnum.BP);
				cmd.setNumeroBulletinPrestation(numBp);
				cmd.setIsGenerated(true);
				cmd.setStatus(BpStatusEnum.SAVED);
				populatePrestationLine(numCmd ,numBp);
				return  commandeMapper.toDto(commandeRepository.save(cmd));
			}else{
				throw new IllegalStatusException("bulletin de prestation  déjà généré");
			}
	}

	private void populatePrestationLine(String numCmd, String NumBp ) {
		List<LigneCommandeDTO> lineCommandDTOs = ligneCommandeServiceGCMD
				.findAllByNumeroBonCommande(Pageable.unpaged()  , numCmd ).getContent();
		int i = 0;
		for(LigneCommandeDTO ligneCommandeDTO :lineCommandDTOs){

			if (Boolean.TRUE.equals(ligneCommandeDTO.getIsSelected())) {
				Double codeMvt = ligneCommandeDTO.getCodePrestation();
				MouvementPrestationEntity movementPrestation = mouvementPrestationService
						.findByCodeMovement(doubleFormat(codeMvt));
				LignePrestationEntity lignePrestationEntity  =
						lignePrestationServiceGCMD.findByNumeroBonCommandeAndCodePrestation(numCmd , Double.valueOf(movementPrestation.getCodePrestation()));
				if (lignePrestationEntity != null){
					lignePrestationServiceGCMD.save(populateDate(lignePrestationEntity , ligneCommandeDTO));
				}else {
					LignePrestationEntity lignePrestationEntity01  = new LignePrestationEntity();
					lignePrestationEntity01.setNumeroOrdrePrestation((double)++i);
					lignePrestationEntity01.setNumeroDossier(NumBp);
					lignePrestationEntity01.setLibellePrestation(movementPrestation.getLibellePrestation());
					lignePrestationEntity01.setCodePrestation(Double.valueOf(movementPrestation.getCodePrestation()));
					lignePrestationEntity01.setNumeroBonCommande(ligneCommandeDTO.getNumeroBonCommande());
					lignePrestationEntity01.setCodeCpa(ligneCommandeDTO.getCodeCpa());
					lignePrestationEntity01.setImportExport(ImportExportEnum.IMPORT.getValue());
					lignePrestationEntity01.setPoids(ligneCommandeDTO.getPoids());
					lignePrestationEntity01.setQuantite1(ligneCommandeDTO.getQuantite1());
					lignePrestationEntity01.setQuantite2(ligneCommandeDTO.getQuantite2());
					lignePrestationServiceGCMD.save(populateDate(lignePrestationEntity01 , ligneCommandeDTO));
				}
			}
		}
	}

	private LignePrestationEntity populateDate(LignePrestationEntity lignePrestationEntity, LigneCommandeDTO lineCommandDTO1){
		if(lineCommandDTO1.getCodePrestation() == MovementEnum.ACCOSATAGE.getValue()){
			lignePrestationEntity.setDateDebut(lineCommandDTO1.getDateDebut());
			lignePrestationEntity.setHeureDebut(lineCommandDTO1.getHeureDebut());
		}else if (lineCommandDTO1.getCodePrestation() ==  MovementEnum.APPAREILLAGE_DU_DUAI.getValue()){
			lignePrestationEntity.setDateFin(lineCommandDTO1.getDateFin());
			lignePrestationEntity.setHeureFin(lineCommandDTO1.getHeureFin());

		}else {
			lignePrestationEntity.setDateDebut(lineCommandDTO1.getDateDebut());
			lignePrestationEntity.setHeureDebut(lineCommandDTO1.getHeureDebut());
			lignePrestationEntity.setDateFin(lineCommandDTO1.getDateFin());
			lignePrestationEntity.setHeureFin(lineCommandDTO1.getHeureFin());
		}
		return lignePrestationEntity;
	}

	private String doubleFormat(Double codeMvt) {
		return new DecimalFormat("#").format(codeMvt);
	}

	public String getStatus(String numBP) {
		CommandeDTO cmd = findByNumBp(numBP);
		return cmd.getStatus().getValue();
	}



	public void CancelGeneration(String numCmd) {
		CommandeDTO commandeDTO   = findByNumCmd(numCmd);
		if (Boolean.TRUE.equals(commandeDTO.getIsGenerated())){
			commandeRepository.setIsGeneratedFalse(numCmd);
			commandeRepository.setStatusToInitial(numCmd);
			commandeRepository.setNumBpToNull(numCmd);
			cancelLinePrestation(numCmd);
			commandeRepository.setIsGeneratedFalse(numCmd);
		}else{
			throw new IllegalStatusException("BP not generated yet to cancel generation ");
		}
	}

	private void cancelLinePrestation(String numCmd) {
		List<LigneCommandeDTO> lineCmdDTOs = ligneCommandeServiceGCMD.
				findAllByNumeroBonCommande(  Pageable.unpaged() , numCmd).getContent();
		lineCmdDTOs.forEach(lineCmdDTO -> {
				LigneCommandeEntity ligneCommandeEntity  = ligneCommandeServiceGCMD.toLineCmdEntity(lineCmdDTO);
				ligneCommandeEntity.setNumeroDossier(null);
				ligneCommandeRepository.save(ligneCommandeEntity);
	    });
	}

	public CommandeDTO preValidate(String  numBp , boolean value) {
		CommandeDTO command  = findByNumBp(numBp);
		if (value){
			if (command.getStatus() == BpStatusEnum.SAVED){
				command.setStatus(BpStatusEnum.PREVALIDATED);
			}else {
				throw new IllegalStatusException("Bp not generated yet to PreValidate");
			}

		}else {
			if (command.getStatus() == BpStatusEnum.PREVALIDATED){
				command.setStatus(BpStatusEnum.SAVED);
			}else {
				throw new IllegalStatusException("Bp not preValidated to cancel preValidation ");
			}
			command.setStatus(BpStatusEnum.SAVED);
		}
		CommandeEntity commandeEntity = commandeMapper.toEntity(command);
		commandeEntity = commandeRepository.save(commandeEntity);
		return commandeMapper.toDto(commandeEntity);
	}

	public CommandeDTO validate(String numBP , boolean value) {
		findByNumBp(numBP);
		if(value){
			commandeRepository.setIsValidate(numBP );
			log.info("validate BP number :{}" , numBP);
		}else{
			commandeRepository.cancelValidation(numBP);
			log.info("cancel Validation  BP number :{}" , numBP);
		}
		return findByNumBp(numBP);
	}

	public CommandeDTO preCancel(String numBp , String cancelReason , boolean value ) {
		CommandeDTO command = findByNumBp(numBp);
		if(value){
			if(command.getStatus().equals(BpStatusEnum.SENT)){
				command.setStatus(BpStatusEnum.PRECANCELED);
				command.setBulltinAnnule("O");
				command.setCodeCauseAnnulation(cancelReason);
				CommandeEntity commandeEntity = commandeMapper.toEntity(command);
				commandeEntity = commandeRepository.save(commandeEntity);
				return commandeMapper.toDto(commandeEntity);
			}else{
				throw new IllegalStatusException("Bp not sent to preCancel");
			}
		}else {
			if(command.getStatus().equals(BpStatusEnum.PRECANCELED)){
				command.setStatus(BpStatusEnum.SENT);
				command.setBulltinAnnule(null);
				command.setCodeCauseAnnulation(null);
				CommandeEntity commandeEntity = commandeMapper.toEntity(command);
				commandeEntity = commandeRepository.save(commandeEntity);
				return commandeMapper.toDto(commandeEntity);
			}else{
				throw new IllegalStatusException("Bp not preCancel");
			}
		}

	}

	public CommandeDTO cancel(String numBp ) {
		CommandeDTO cmd  = findByNumBp(numBp);
		if(cmd.getStatus().equals(BpStatusEnum.PRECANCELED)){
			commandeRepository.setStatusCancel(numBp);
			bulltinPrestationService.cancel(numBp);
			return cmd;
		}else{
			throw new IllegalStatusException("BP not precancel to Cancel");
		}
	}
}
