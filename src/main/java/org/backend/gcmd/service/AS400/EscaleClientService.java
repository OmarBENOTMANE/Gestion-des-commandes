package org.backend.gcmd.service.AS400;

import org.backend.gcmd.entity.as400.EscaleClientEntity;
import org.backend.gcmd.exceptions.technical.ObjectNotFoundException;
import org.backend.gcmd.repository.AS400.EscaleClientRepository;
import org.backend.gcmd.validator.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EscaleClientService {

    @Autowired
    EscaleClientRepository escale_clientRepository;


    public EscaleClientEntity findByNumEscal(Double numEscal){
        Validate.notNull(numEscal ,"Num escal must be not null ");
        Optional<EscaleClientEntity> escale_client  = escale_clientRepository.findByNumEscal(numEscal);
        if(escale_client.isPresent()){
            return escale_client.get();
        }else{
            return  null;
        }
    }
}
