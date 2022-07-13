package org.backend.gcmd.controller.local;

import lombok.extern.log4j.Log4j2;
import org.backend.gcmd.dto.CommandeDTO;
import org.backend.gcmd.entity.local.CancelReasonEntity;
import org.backend.gcmd.repository.local.CancelReasonRepository;
import org.backend.gcmd.service.local.CommandeServiceGCMD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/commandes")
public class CommandeController {

	@Autowired
	CommandeServiceGCMD commandeService;

	@Autowired
	CancelReasonRepository cancelReasonRepository;

	@GetMapping
	public Page<CommandeDTO> findAll(Pageable page) {
		return commandeService.findAll(page);
	}

	@GetMapping("{id}")
	public CommandeDTO findById(@PathVariable String id) {
		return commandeService.findByNumCmd(id);
	}


	@GetMapping("actions/toBill")
	public Page<CommandeDTO> pageToBill(Pageable page){
		return commandeService.cmdToBill(page);
	}

	@GetMapping("/actions/history")
	public Page<CommandeDTO> findHistory(Pageable page) {
		return commandeService.findHistory(page);
	}

	@GetMapping("/actions/generateCmd/{numEscale}")
	public CommandeDTO generateCommand(@PathVariable  Double numEscale) {
		return commandeService.generateCmd(numEscale);
	}
	
	@GetMapping("/actions/transformtobp/{numCmd}")
	public CommandeDTO generateBP(@PathVariable  String numCmd  ) {
		return commandeService.transformTOBp(numCmd);
	}

	@GetMapping("actions/cancelGeneration/{numCmd}")
	public void cancelGeneration(@PathVariable String numCmd ){
		 commandeService.CancelGeneration(numCmd);
	}

	@GetMapping("/actions/send/{numBp}")
	public CommandeDTO send(@PathVariable  String numBp  ) {
		return  commandeService.send(numBp);
	}

	@GetMapping("bulletins")
	public Page<CommandeDTO> getBp(Pageable page) {
		return  commandeService.findBP( page);
	}

	@GetMapping("bulletins/history")
	public Page<CommandeDTO> getBpHistory(Pageable page){
		return commandeService.findBpSent(page);
	}

	@GetMapping("history")
	public Page<CommandeDTO> getCmdHistory(Pageable page){
		return commandeService.findHistory(page);
	}


	@GetMapping("status/{numBp}")
	public String  getStatus(@PathVariable  String numBp ){
		return commandeService.getStatus(numBp);
	}

	@PutMapping
	public CommandeDTO update(@RequestBody CommandeDTO cmd){
		return commandeService.update(cmd);
	}

	@GetMapping("precancled")
	public Page<CommandeDTO> findAllPreCanceled(Pageable page){
		return commandeService.findAllPreCanceled(page);
	}

	@GetMapping("prevalidated")
	public Page<CommandeDTO> findAllPreValidated(Pageable page){
		return commandeService.findAllPreValidated(page);
	}

	@GetMapping("actions/preValidate/{numBp}/{value}")
	public CommandeDTO preFacture(@PathVariable String numBp , @PathVariable boolean value ){
		return commandeService.preValidate(numBp , value);
	}

	@GetMapping("/actions/validate/{numBp}/{value}")
	public void validate(@PathVariable  String numBp   , @PathVariable boolean value ) {
		commandeService.validate(numBp , value);
	}

	@PutMapping("actions/preCancel/{numBp}/{value}")
	public 	CommandeDTO PreCancel( @PathVariable String numBp  , @RequestBody String reason , @PathVariable boolean value ){
		return commandeService.preCancel(numBp , reason , value);
	}

	@GetMapping("/actions/cancel/{numBp}")
	public CommandeDTO cancel( @PathVariable  String numBp  ) {
		return  commandeService.cancel(numBp);
	}

	@GetMapping("cancel/motif")
	public List<CancelReasonEntity> cancelMotif(){
		return cancelReasonRepository.findAll();
	}

}

