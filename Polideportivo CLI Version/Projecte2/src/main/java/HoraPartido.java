import java.io.Serial;
import java.time.LocalTime;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.BLACK_TEXT;
import static com.diogonunes.jcolor.Attribute.RED_BACK;

public class HoraPartido extends Hora{
    @Serial
    private static final long serialVersionUID = -6043249500041412391L;
    private boolean estado = false;
    private Usuarios userClaim;

    public HoraPartido(LocalTime hora) {
        super(hora);
    }

    @Override
    public String getEstadoString(){
        String res = "";
        if (this.estado){
            res = colorize("Reservado ", BLACK_TEXT(), RED_BACK());
        }else {
            res = "Disponible";
        }
        return res;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setUserClaim(Usuarios userClaim) {
        this.userClaim = userClaim;
    }

    public Usuarios getUserClaim() {
        return userClaim;
    }
}
