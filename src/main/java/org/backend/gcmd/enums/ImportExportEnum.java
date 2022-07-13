package org.backend.gcmd.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ImportExportEnum {

    IMPORT("I"), EXPORT("E");

    private String value;
}
