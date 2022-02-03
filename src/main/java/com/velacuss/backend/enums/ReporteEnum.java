package com.velacuss.backend.enums;

import java.util.Arrays;

public enum ReporteEnum {
    REPORTE("/reporte/"),
    REPORTE_FACTURA("factura"),
    REPORTE_FACTURA_DETALLE("facturaDetalle"),
    REPORTE_LINEA_PDF("/reporte/lineas.jasper"),
    REPORTE_LINE_EXCEL("/reporte/lineasExcel.jasper");

    private String value;

    ReporteEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ReporteEnum fromValue(String value){
        for (ReporteEnum item: values()){
            if(item.value.equalsIgnoreCase(value)){
                return item;
            }
        }
        throw new IllegalArgumentException(
                "Unknown enum type " + value + ", Allowed values are" + Arrays.toString(values())
        );
    }
}
