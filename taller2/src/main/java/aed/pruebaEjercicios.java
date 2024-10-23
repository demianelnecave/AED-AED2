package aed;

public class pruebaEjercicios {
    public static void main(String args[]) {
        String mensajito = "Sacar turno para el kinesiólogo";
        Fecha fecha = new Fecha(22, 9);
        Horario horario = new Horario(12, 30);
        Recordatorio recordatorio = new Recordatorio(mensajito, fecha, horario);
        System.out.println("Recordá boludo, tenés: " + recordatorio.toString());
    }
}