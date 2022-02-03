package com.velacuss.backend.enums;

import java.util.Arrays;

public enum DominioEnum {

    PAIS("pais"),
    UNIDAD_MEDIDA("unidad_medida"),
    TRANSPORTE("transporte"),
    PARTE("parte"),
    DEPARTAMENTO("departamento"),
    ESTADO("estado"),
    UBICACION("ubicacion"),
    TIPO_VENTA("tipo_venta"),
    TIPO_ENTREGA("tipo_entrega"),
    TIPO_PAGO("tipo_pago"),
    TIPO_DOCUMENTO("tipo_documento"),
    TIPO_GENERO("tipo_genero"),
    TIPO_PERSONA("tipo_persona"),
    TIPO_CAMBIO("tipo_cambio"),
    TIPO_MONEDA("tipo_moneda"),
    TIPO_INGRESO("tipo_ingreso"),
    TIPO_FUENTE("tipo_fuente"),
    TIPO_UNIDAD("tipo_unidad"),
    TIPO_SUCURSAL("tipo_sucursal"),
    REPORTE_TIPO_COSTO("reporte_tipo_costo"),
    REPORTE_TIPO_CANTIDAD("reporte_tipo_cantidad"),
    TIPO_PEDIDO("tipo_pedido"),
    BANCO("banco"),
    OTRO_INGRESO("otro_ingreso"),
    DEPOSITO("deposito"),
    TIPO_GASTO("tipo_gasto"),
    TIPO_SOBRANTE_FALTANTE("tipo_sobrante_faltante"),
    TIPO_CODIGO("tipo_codigo")
    ;
    private String value;

    DominioEnum(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public static DominioEnum fromValue(String value){
        for (DominioEnum item : values()){
            if (item.value.equalsIgnoreCase(value)){
                return item;
            }
        }
        throw new IllegalArgumentException(
          "Unknown enum type " + value + ", Allowed values are" + Arrays.toString(values())
        );
    }
}
