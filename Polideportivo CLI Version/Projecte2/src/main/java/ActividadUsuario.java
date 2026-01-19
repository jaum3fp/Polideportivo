import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class ActividadUsuario extends Actividad{
    private String estado;
    private TrabajadoresDeportistas sportWorker;
    public ActividadUsuario(NombreDeporte deporte, LocalDate fecha, LocalDate semanaIni, LocalDate semanaFin, DayOfWeek dia, LocalTime hora) {
        super(deporte, fecha, semanaIni, semanaFin, dia, hora);
    }

    @Override
    public String toString() {
        if (sportWorker==null && this.fecha.equals(LocalDate.now())){ //LocalDate.of(2023, 4, 20)
            estado = "CANCELADO";
        } else if (sportWorker==null ){
            estado = "PENDIENTE";
        }else {
            estado = "ACEPTADO";
        }
        return "Actividad{" +
                "deporte=" + deporte +
                ", fecha=" + fecha +
                ", dia=" + dia +
                ", hora=" + hora +
                ", ESTADO=" + estado +
                '}';
    }

    public void setSportWorker(TrabajadoresDeportistas sportWorker) {
        this.sportWorker = sportWorker;
    }


    public String getEstado() {
        return estado;
    }
}
