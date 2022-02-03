package com.velacuss.backend.enums;

import java.util.Arrays;

public enum SistemaEnum {

    PRODUCCION("prod"),
    DESARROLLO("dev");

    private String value;

    SistemaEnum(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public static SistemaEnum fromValue(String value){
        for (SistemaEnum item : values()){
            if (item.value.equalsIgnoreCase(value)){
                return item;
            }
        }
        throw new IllegalArgumentException(
          "Unknown enum type " + value + ", Allowed values are" + Arrays.toString(values())
        );
    }
}
