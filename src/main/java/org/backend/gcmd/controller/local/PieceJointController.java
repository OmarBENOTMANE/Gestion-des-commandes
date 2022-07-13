package org.backend.gcmd.controller.local;


import org.backend.gcmd.service.local.PieceJointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("files")
@RestController
public class PieceJointController {

    @Autowired
    PieceJointService pieceJointService;

    @PostMapping("/{numCmd}")
     Long uploadFile(@RequestParam MultipartFile file , @PathVariable("numCmd") String numCmd) throws IOException {
        return pieceJointService.save(file.getBytes() ,file.getOriginalFilename()  , numCmd );
    }

    @GetMapping(  value = "/{numCmd}")
    ResponseEntity<Resource> downloadFile(@PathVariable String  numCmd) throws FileNotFoundException {

        File file = pieceJointService.findFileByNumCmd(numCmd).getFile();
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION ,"attachment; filename=" + file.getName() );
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_PDF_VALUE))
                .body(resource);
    }

    @RequestMapping(value = "fileName/{numCmd}")
    public List<String> fileName(@PathVariable String numCmd  ){
        List<String> fileName  = new ArrayList<>();
        fileName.add(pieceJointService.findFileName(numCmd));
        return fileName;
    }

    @DeleteMapping("actions/delete/{numBp}")
    public Long deletePieceJointByNumBp(@PathVariable String numBp){
        return pieceJointService.delete(numBp);
    }

}
