import java.io.Serial;
import java.time.LocalDate;
import java.util.LinkedList;

public class Usuarios extends Persona{
    @Serial
    private static final long serialVersionUID = 8727154519256799213L;
    public LinkedList<Actividad> reservas = new LinkedList<>();

    public Usuarios(String nom, String surn1, String surn2, int telef, LocalDate datanaix, String dni, String passwd) {
        super(nom, surn1, surn2, telef, datanaix, dni, passwd);
    }

    public void hacerReserva(){
        Sistema.reservarUsuarioSistema(this.dni);
    }

    @Override
    public String toString() {
        return "Usuarios{" +
                "nom='" + nom + '\'' +
                ", surn1='" + surn1 + '\'' +
                ", surn2='" + surn2 + '\'' +
                ", telef=" + telef +
                ", datanaix=" + datanaix +
                ", dni='" + dni + '\'' +
                "}\n";
    }
}
