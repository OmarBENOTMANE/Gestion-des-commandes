package org.backend.gcmd.controller.AS400;

import org.backend.gcmd.dto.AS400.BulltinPrestationDTO;
import org.backend.gcmd.service.AS400.BulltinPrestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/bulltinPrestations")
@CrossOrigin(origins="*")
public class BulltinPrestationController {

    @Autowired
    BulltinPrestationService bulltinPrestationService;

    @GetMapping
    public Page<BulltinPrestationDTO> findAll(Pageable pageable) {
        return bulltinPrestationService.findAll(pageable);
    }


}
