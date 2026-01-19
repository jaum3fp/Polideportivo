import base.Writable;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Actividad extends Writable {
    protected final NombreDeporte deporte;
    protected final LocalDate semanaIni;
    protected final LocalDate semanaFin;
    protected final DayOfWeek dia;
    protected final LocalTime hora;
    protected final LocalDate fecha;


    public Actividad(NombreDeporte deporte, LocalDate fecha, LocalDate semanaIni, LocalDate semanaFin, DayOfWeek dia, LocalTime hora) {
        this.deporte = deporte;
        this.fecha=fecha;
        this.semanaIni = semanaIni;
        this.semanaFin = semanaFin;
        this.dia = dia;
        this.hora = hora;
    }

    public NombreDeporte getDeporte() {
        return deporte;
    }

    public LocalDate getSemanaIni() {
        return semanaIni;
    }

    public LocalDate getSemanaFin() {
        return semanaFin;
    }

    public DayOfWeek getDia() {
        return dia;
    }

    public LocalTime getHora() {
        return hora;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}
