package Cliente;

public class Inicio
{
    public static void main(final String[] args) {
        final Posicion p = new Posicion();
        final Tablero t = new Tablero(p);
        final Logica juego = new Logica(t, p);
        juego.Conecto();
        final int turno = juego.turno();
        p.cargaLetra((turno == 1) ? 'X' : 'O');
        juego.inicio(turno);
    }
}