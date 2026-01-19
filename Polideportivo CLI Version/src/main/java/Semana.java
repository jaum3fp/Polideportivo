import java.io.Serial;
import java.io.Serializable;
import java.time.*;
import static com.diogonunes.jcolor.Attribute.TEXT_COLOR;
import base.Writable;
import com.diogonunes.jcolor.AnsiFormat;

public class Semana implements Serializable {
    @Serial
    private static final long serialVersionUID = -8198688960986129192L;
    private final int id;
    private final NombreDeporte deporte;
    private final LocalDate inicio, fin;
    public Dia[] dias = new Dia[5];

    public Semana(int id, LocalDate inicio, LocalDate fin, NombreDeporte deporte){
        this.id=id;
        this.inicio=inicio; this.fin=fin; this.deporte=deporte;
        /*for(int x = 0; x < 5; x++){
            switch (x) {
                case 0 -> dias[x] = new Dia(DayOfWeek.MONDAY, this.deporte);
                case 1 -> dias[x] = new Dia(DayOfWeek.TUESDAY, this.deporte);
                case 2 -> dias[x] = new Dia(DayOfWeek.WEDNESDAY, this.deporte);
                case 3 -> dias[x] = new Dia(DayOfWeek.THURSDAY, this.deporte);
                case 4 -> dias[x] = new Dia(DayOfWeek.FRIDAY, this.deporte);
            }
        }*/
    }

    public void mostrarCalendarioSemana(){
        StringBuilder res = new StringBuilder();
        StringBuilder bar = new StringBuilder(); int barLength = 98;
        int cont = 0;

        /* GENERAR BARRA */

        for (int x = 0; x < barLength; x++){
            if (x == 0 || x == barLength-1){
                bar.append("+");
            } else {
                bar.append("-");
            }
        }

        /* CUERPO  */

        AnsiFormat calInfoColor = new AnsiFormat(TEXT_COLOR(37));

        for (int y = 0; y < 25; y++){
            if (y % 2 == 0){
                res.append(bar).append("\n");

            }else {
                switch (y) {
                    case 1 -> res.append(calInfoColor.format(" " + this.deporte + "  Semana   " + inicio + " - " + fin + "\n"));
                    case 3 -> res.append(calInfoColor.format(" Horario       Lunes           Martes           Miercoles          Jueves           Viernes \n"));
                    default -> {
                        res.append(calInfoColor.format(" " + dias[0].horas[cont].getHora() + "         ")).append(dias[0].horas[cont].getEstadoString()).append("      ").append(dias[1].horas[cont].getEstadoString()).append("       ").append(dias[2].horas[cont].getEstadoString()).append("         ").append(dias[3].horas[cont].getEstadoString()).append("       ").append(dias[4].horas[cont].getEstadoString()).append(" \n");
                        cont++;
                    }
                }
            }
        }
        Sistema.PRINT_STREAM.println(res);
    }

    public int getId() {
        return id;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public LocalDate getFin() {
        return fin;
    }


}
