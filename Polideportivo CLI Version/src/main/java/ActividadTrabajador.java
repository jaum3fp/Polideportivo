import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

public class ActividadTrabajador extends Actividad{
    public Usuarios userAssigned;
    public LinkedList<Usuarios> usersAssigned = new LinkedList<>();

    public ActividadTrabajador(NombreDeporte deporte, LocalDate fecha, LocalDate semanaIni, LocalDate semanaFin, DayOfWeek dia, LocalTime hora) {
        super(deporte, fecha, semanaIni, semanaFin, dia, hora);
    }

    @Override
    public String toString() {
        String res = "";
        if (deporte.getId()==0){
            res+= "ActividadTrabajador{" +
                    "deporte=" + deporte +
                    ", fecha=" + fecha +
                    ", dia=" + dia +
                    ", hora=" + hora +
                    ", USUARIO:\n" + userAssigned +
                    '}';
        } else if (deporte.getId()==1) {
            res+= "ActividadTrabajador{" +
                    "deporte=" + deporte +
                    ", fecha=" + fecha +
                    ", dia=" + dia +
                    ", hora=" + hora +
                    ", USUARIOs:\n" + getUsersAssigned() +
                    '}';
        }
        return res;
    }

    public LinkedList<Usuarios> getUsersAssigned() {
        return usersAssigned;
    }
}
