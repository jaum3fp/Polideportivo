import java.io.Serial;
import java.time.LocalDate;
import java.util.LinkedList;

public class TrabajadoresDeportistas extends Trabajadores{
    @Serial
    private static final long serialVersionUID = -531883538846623078L;
    public LinkedList<Deporte> curriculumVitae = new LinkedList<>();
    public LinkedList<Actividad> solicitud = new LinkedList<>();

    public TrabajadoresDeportistas(String nom, String surn1, String surn2, int telef, LocalDate datanaix, String dni, String passwd) {
        super(nom, surn1, surn2, telef, datanaix, dni, passwd);
    }

    @Override
    public String toString() {
        return "TrabajadoresDeportistas{" +
                "nom='" + nom + '\'' +
                ", surn1='" + surn1 + '\'' +
                ", surn2='" + surn2 + '\'' +
                ", telef=" + telef +
                ", datanaix=" + datanaix +
                ", dni='" + dni + '\'' +
                '}';
    }

    public void rellenarCurriculumTrabajadorDeportista(){
        Sistema.rellenarCurriculumTrabajadorDeportistaSistema(this.dni);
    }

}
