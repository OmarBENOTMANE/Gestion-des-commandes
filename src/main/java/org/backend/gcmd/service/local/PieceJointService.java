package org.backend.gcmd.service.local;

import lombok.extern.log4j.Log4j2;
import org.backend.gcmd.entity.local.PieceJointEntity;
import org.backend.gcmd.exceptions.technical.FileNotFoundException;
import org.backend.gcmd.repository.local.PieceJointRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Optional;

@Log4j2
@Service
@Transactional
public class PieceJointService {

    @Autowired
    PieceJointRepository pieceJointRepository;

    @Autowired
    CommandeServiceGCMD commandeServiceGCMD;


    @Autowired
    Environment env;

    private String saveFile(byte[] content, String fileName) throws IOException {
        String RESOURCES_DIR = env.getProperty("RESOURCES_DIR");
        Path newFile = Paths.get(RESOURCES_DIR + new Date().getTime() + "-" + fileName);
        Files.createDirectories(newFile.getParent());

        Files.write(newFile, content);

        return newFile.toAbsolutePath()
                .toString();
    }

    private FileSystemResource findFile(String location) {
        try {
            return new FileSystemResource(Paths.get(location));
        } catch (Exception e) {
            throw new FileNotFoundException("No file found");
        }
    }

    public Long save(byte[] bytes, String fileName  , String numCmd) throws IOException {
        String location = saveFile(bytes, fileName);
        PieceJointEntity pieceJoint  = findByNumCmd(numCmd);
        if(pieceJoint == null){
            pieceJoint =   new PieceJointEntity();
        }
        pieceJoint.setDesignation(location);
        pieceJoint.setNumCmd(numCmd);
        pieceJoint.setFileName(fileName);
        return pieceJointRepository.save(pieceJoint).getId();
    }

    private PieceJointEntity findByNumCmd(String numCmd) {
        Optional<PieceJointEntity> pieceJointEntity =  pieceJointRepository.findByNumCmd(numCmd);
        return pieceJointEntity.orElse(null);
    }


    public FileSystemResource findFileByNumCmd(String numCmd ) {
        Optional <PieceJointEntity> fileOptional= pieceJointRepository.findByNumCmd(numCmd);
        if(fileOptional.isPresent()){
            PieceJointEntity file  = fileOptional.get();
            return findFile(file.getDesignation());
        }else{
            throw new FileNotFoundException("Exonération Tva Not Found ");
        }
    }

    public String findFileName(String numCmd){
        Optional<PieceJointEntity> pieceJoint  = pieceJointRepository.findByNumCmd(numCmd);
        return pieceJoint.map(PieceJointEntity::getFileName).orElse(null);

    }

    public Long delete(String numCmd){
        return pieceJointRepository.removeByNumCmd(numCmd);
    }

}

