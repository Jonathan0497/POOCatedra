package Clases;

import java.util.regex.Pattern;

public class Validaciones_ExpresionesRegulares {
    public static boolean validarNumero(String numero, int numeroCaracteres) {
        String regex = "^\\d{"+numeroCaracteres+"}$";
        return Pattern.matches(regex, numero);
    }

    public static boolean validarTexto(String texto) {
        String regex = "^[a-zA-Z\\s]{1,50}$";
        return Pattern.matches(regex, texto);
    }

    public static boolean validacionSala(String numero) {
        String regex = "^[1-9][0-9]*$";
        return Pattern.matches(regex, numero);
    }


    public static boolean validarContrasena(String contrasena) {
        String regex = "^[a-zA-Z0-9]+$";
        return Pattern.matches(regex, contrasena);
    }

    public static boolean validarCorreo(String correo) {
        String regex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        return Pattern.matches(regex, correo);
    }
}
