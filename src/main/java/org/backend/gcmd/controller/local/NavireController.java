package org.backend.gcmd.controller.local;

import org.backend.gcmd.dto.AS400.EscaleDTO;
import org.backend.gcmd.entity.as400.MouvementEntity;
import org.backend.gcmd.entity.prestati.ClientEntity;
import org.backend.gcmd.repository.AS400.MouvementRepository;
import org.backend.gcmd.repository.perstati.ClientRepository;
import org.backend.gcmd.service.AS400.NavireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/navires")
@CrossOrigin(origins="*")
public class NavireController {

    @Autowired
    NavireService navireService;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    MouvementRepository mouvementRepository;


    @GetMapping("actions/filter")
    public Page<EscaleDTO> findNavirTOBill(@RequestParam(required = false) Double numeroEscale, @RequestParam(required = false)
            String nomNavire, @RequestParam(required = false) String numeroLloyd, Pageable page) {
        return navireService.filter(numeroEscale, nomNavire, numeroLloyd, page);
    }

    @GetMapping("actions/toBill")
    public Page<EscaleDTO> NavireToBill(Pageable page) {
        return navireService.escalToBill(page);
    }

    @GetMapping
    public Page<EscaleDTO> findAll(Pageable pageable) {
        return navireService.findAll(pageable);
    }


}
