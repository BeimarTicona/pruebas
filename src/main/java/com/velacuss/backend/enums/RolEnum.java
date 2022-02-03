package com.velacuss.backend.enums;

import java.util.Arrays;

public enum RolEnum {

    ROL_ADMINISTRADOR("ADMIN"),
    ROL_ALMACEN("ALM"),
    ROL_CAJERO("CAJ"),
    ROL_ENCARGADO("ENC"),
    ROL_GERENCIA("GER"),
    ROL_GERENCIA_DE_VENTAS("GERV"),
    ROL_VENTAS("VENT");

    private String value;

    RolEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RolEnum fromValue(String value){
        for (RolEnum item: values()){
            if(item.value.equalsIgnoreCase(value)){
                return item;
            }
        }
        throw new IllegalArgumentException(
                "Unknown enum type " + value + ", Allowed values are" + Arrays.toString(values())
        );
    }
}
