import java.io.Serial;
import java.time.LocalTime;
import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;

public class HoraClase extends Hora{
    @Serial
    private final static long serialVersionUID = -8890060841405095811L;
    private int estado = 0;
    public Usuarios[] claseUsuarios = new Usuarios[20];
    private TrabajadoresDeportistas trainerAssigned;

    public HoraClase(LocalTime hora) {
        super(hora);
    }

    @Override
    public String getEstadoString() {
        String res = "";
        if (estado <= 20){
            if (estado == 20){
                res += colorize(" ["+estado+"/20] ", BLACK_TEXT(), RED_BACK());
            } else if (estado >= 10) {
                res += colorize(" ["+estado+"/20] ", BLACK_TEXT(), BACK_COLOR(208));
            } else if (estado > 0) {
                res += colorize(" ["+estado+"/20] ", BLACK_TEXT(), BACK_COLOR(35));
            } else if (estado == 0) {
                res += " ["+estado+"/20] ";
            }

        }
        return res;
    }

    public void incrementEstado(){
        this.estado++;
    }
    public int getEstado(){
        return this.estado;
    }
}
