package org.backend.gcmd.controller.local;

import org.backend.gcmd.service.AS400.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/clients")
@RestController
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("{codeClient}")
    public String findByCodeClient(@PathVariable String codeClient ){
        return clientService.findByCodeClient(codeClient).getNomClient();
    }
}
