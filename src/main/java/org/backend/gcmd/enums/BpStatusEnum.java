package org.backend.gcmd.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BpStatusEnum {

    SAVED("SAVED")  ,
    PREVALIDATED("PREVALIDATED"),
    VALIDATED("VALIDATED") ,
    SENT("SENT") ,
    PRECANCELED("PRECANCELED"),
    CANCELED("CANCELED"),
    INITIAL("INITIALED");

    private String value;
}
