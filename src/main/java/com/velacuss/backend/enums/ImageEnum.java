package com.velacuss.backend.enums;

import java.util.Arrays;

public enum ImageEnum {
    LOGO_REPORTE("reporte/images/logo-on-dark.png");

    private String value;

    ImageEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ImageEnum fromValue(String value){
        for (ImageEnum item: values()){
            if(item.value.equalsIgnoreCase(value)){
                return item;
            }
        }
        throw new IllegalArgumentException(
                "Unknown enum type " + value + ", Allowed values are" + Arrays.toString(values())
        );
    }
}
