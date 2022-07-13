package org.backend.gcmd.service.local;

import org.backend.gcmd.entity.local.IndexGeneratorEntity;
import org.backend.gcmd.enums.KeyGenertorEnum;
import org.backend.gcmd.repository.local.IndexGeneratorRepository;
import org.backend.gcmd.validator.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Transactional
public class IndexGeneratorService {

    @Autowired
    IndexGeneratorRepository indexGeneratorRepository;

    public String generateNumBP(KeyGenertorEnum  key){
        Validate.notNull(key,"key must be not null ");
       IndexGeneratorEntity indexGeneratorEntity = indexGeneratorRepository.findByKey(key.getValue());
        int currentIndex  = indexGeneratorEntity.getValue();
        String numBp =format(currentIndex );
        indexGeneratorEntity.setValue(++currentIndex);
        indexGeneratorRepository.save(indexGeneratorEntity);
        return numBp;

    }
    public String generateNumCMD(KeyGenertorEnum  key){
        Validate.notNull(key,"key must be not null ");
        IndexGeneratorEntity indexGeneratorEntity = indexGeneratorRepository.findByKey(key.getValue());
        int currentIndex  = indexGeneratorEntity.getValue();
        String  numCmd  = byPaddingZeros(currentIndex , 8);
        indexGeneratorEntity.setValue(++currentIndex);
        indexGeneratorRepository.save(indexGeneratorEntity);
        return numCmd;
    }

    @Scheduled(cron = "0 0 12 1 * *")
    public void resetIndexes(){
        indexGeneratorRepository.resetIndexes();
    }

    private String format(int number){
        String date = getCurrentDate();
        String  index  = byPaddingZeros(number,4);
        return "DTV " + date + index ;
    }

    private String getCurrentDate() {
        String pattern = "yyyyMM";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }

    private static String byPaddingZeros(int value, int paddingLength) {
        return String.format("%0" + paddingLength + "d", value);
    }

}
