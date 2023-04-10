package com.neoris.tst.pruebatecnica.utility.validations;

public class CrearCuentaUsuarioValidate {
    public final static String NUMERO_CUENTA_REQUIRED = "El número de cuenta es obligatorio";
    public final static String NUMERO_CUENTA_NOT_EMPTY = "El número de cuenta no puede estar vacío";
    public final static String TIPO_CUENTA_DESCRIPCION_REQUIRED = "El tipo de cuenta es obligatorio";
    public final static String TIPO_CUENTA_DESCRIPCION_NOT_EMPTY = "El tipo de cuenta no puede estar vacío";
    public final static String SALDO_INICIAL_NOT_NULL = "El saldo inicial de la cuenta es obligatorio";
    public final static int SALDO_INICIAL_MIN_VALUE = 0;
    public final static String SALDO_INICIAL_MIN = "El saldo inicial debe ser mayor o igual a {value}";
    public final static String NOMBRE_NOT_NULL = "El nombre del cliente es obligatorio";
    public final static String NOMBRE_NOT_EMPTY = "El nombre del cliente no puede estar vacío";
}
