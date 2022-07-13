package org.backend.gcmd.service.AS400;

import lombok.extern.log4j.Log4j2;
import org.backend.gcmd.entity.prestati.ClientEntity;
import org.backend.gcmd.exceptions.technical.ObjectNotFoundException;
import org.backend.gcmd.repository.perstati.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@Log4j2
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public ClientEntity findByCodeClient(String codeClient) {
        Optional<ClientEntity> clientEntity  =  clientRepository.findByCodeClientt(codeClient);
        if (clientEntity.isPresent()){
            return clientEntity.get();
        }else {
            return null;
        }
    }
}
