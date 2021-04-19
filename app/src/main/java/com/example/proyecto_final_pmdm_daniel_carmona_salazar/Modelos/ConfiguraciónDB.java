package com.example.proyecto_final_pmdm_daniel_carmona_salazar.Modelos;

public class ConfiguraciónDB {
    public static final String NOMBREDB = "tienda_videojuegos_android";
    public static final String HOSTDB = "10.0.2.2";
    public static final String USUARIODB = "root";
    public static final String CLAVEDB = "Legendario11";
    public static final String PUERTOMYSQL = "3306";
    //public static final String HOSTDB = "infsalinas.sytes.net";
    //public static final String USUARIODB = "damserver1";
    //public static final String CLAVEDB = "dam1234";
    //public static final String PUERTOMYSQL = "5306";
    private static final String OPCIONES = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String URLMYSQL = "jdbc:mysql://"+ HOSTDB + ":" + PUERTOMYSQL+"/" + NOMBREDB + OPCIONES;
}
