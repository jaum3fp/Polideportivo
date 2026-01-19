import base.Writable;

import java.io.Serial;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deporte implements Serializable {
    @Serial
    private static final long serialVersionUID = 2694663373779913062L;
    private boolean habilitado = true;
    public Semana[] semanas = new Semana[52];
    private NombreDeporte nom;

    public Deporte(NombreDeporte nom){
        this.nom=nom;

        /* CALENDARIO */
        LocalDate start = LocalDate.parse("2023-01-01");
        LocalDate end = LocalDate.parse("2024-01-01");
        LocalDate ini = LocalDate.parse("0000-01-01"),
                fin = LocalDate.parse("0000-01-01");
        int cont = 0;

        List<LocalDate> dates = Stream.iterate(start, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(start, end))
                .collect(Collectors.toList());

        /*for (int x = 0; x < dates.size()-1; x++){
            if (dates.get(x).getDayOfWeek().equals(DayOfWeek.MONDAY)){
                ini = LocalDate.parse(dates.get(x).toString());
            }else if (dates.get(x).getDayOfWeek().equals(DayOfWeek.FRIDAY)){
                fin = LocalDate.parse(dates.get(x).toString());
            }else if (dates.get(x).getDayOfWeek().equals(DayOfWeek.SUNDAY)){
                semanas[cont]= new Semana(cont, ini, fin, this.nom);
                cont++;
            }

        }*/
    }

    @Override
    public String toString() {
        return ""+nom;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }
    public boolean getHabilitado(){return this.habilitado;}

    public NombreDeporte getNom() {
        return nom;
    }

    public void setNom(NombreDeporte nom) {
        this.nom = nom;
    }
}
