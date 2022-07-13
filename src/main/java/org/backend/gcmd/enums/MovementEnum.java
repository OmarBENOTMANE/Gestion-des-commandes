package org.backend.gcmd.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MovementEnum {

    ACCOSATAGE(1) , CHNAGEMENT_DE_POST(2) , APPAREILLAGE_DU_DUAI(5) ;

    private int value;
}
