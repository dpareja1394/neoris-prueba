package com.neoris.tst.pruebatecnica.utility;

public class MensajeExcepcionService {

    public final static String CLIENTE_NO_EXISTE_POR_IDENTIFICACION_MENSAJE =
            "Cliente con identificación %s y estado %s no existe como cliente";
    public final static String CUENTA_EXISTE_POR_CLIENTE_TIPO_MENSAJE =
            "La cuenta de %s número %s para el cliente %s y estado %s ya se encuentra registrada en el sistema";
    public final static String CUENTA_NO_EXISTE_POR_NUMERO_TIPO_MENSAJE =
            "La cuenta de %s número %s y estado %s no se ha encontrado en el sistema";
    public final static String GENERO_NO_ENCONTRADO_MENSAJE = "El género %s no se ha encontrado";
    public final static String PERSONA_EXISTE_POR_IDENTIFICACION_MENSAJE =
            "La persona con identificación %s y estado %s ya existe en el sistema";
    public final static String PERSONA_NO_EXISTE_POR_NOMBRE_MENSAJE =
            "%s y estado %s no existe como cliente";
    public final static String TIPO_CUENTA_NO_EXISTE_POR_DESCRIPCION_MENSAJE =
            "El Tipo de Cuenta %s con estado %s no existe en el sistema";
    public final static String RETIRO_EXCEDE_SALDO_CUENTA_MENSAJE =
            "El valor a retirar es mayor al saldo actual de la cuenta";
    public final static String CLIENTE_NO_EXISTE_POR_NOMBRE_MENSAJE =
            "%s con estado %s no existe como cliente";


}
