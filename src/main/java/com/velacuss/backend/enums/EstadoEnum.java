package com.velacuss.backend.enums;

import java.util.Arrays;

public enum EstadoEnum {

    ACTIVO("AC"),
    NO_ACTIVO("AN");

    private String value;

    EstadoEnum(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public static EstadoEnum fromValue(String value){
        for (EstadoEnum item : values()){
            if (item.value.equalsIgnoreCase(value)){
                return item;
            }
        }
        throw new IllegalArgumentException(
          "Unknown enum type " + value + ", Allowed values are" + Arrays.toString(values())
        );
    }
}
