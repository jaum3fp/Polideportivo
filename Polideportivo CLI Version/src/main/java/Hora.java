import base.Writable;

import java.io.Serial;
import java.time.LocalTime;

public abstract class Hora extends Writable {
    @Serial
    private final static long serialVersionUID = -3810966011154227673L;
    protected LocalTime hora;

    public Hora(LocalTime hora){
        this.hora = hora;
    }


    public abstract String getEstadoString();

    public LocalTime getHora() {
        return hora;
    }

}
