package org.backend.gcmd.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum  KeyGenertorEnum {
    BP("bp"),
    CMD("cmd"),
    CMDID("cmdId"),
    LCMDID("lCmdId");

    private String value;
}
