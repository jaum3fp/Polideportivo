import base.Writable;

import java.io.Serial;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class Dia extends Writable {
    @Serial
    private static final long serialVersionUID = -353077589068766697L;
    private DayOfWeek diaSemana;
    private NombreDeporte deporte;
    public Hora[] horas = new Hora[10];

    public Dia(DayOfWeek diaSemana, NombreDeporte deporte){
        this.diaSemana=diaSemana; this.deporte=deporte;
        int cont = 8;



        /*for (int x = 0; x < 10; x++){
            if (deporte.getId()==0){
                horas[x] = new HoraPartido(LocalTime.of(cont, 0));
            } else if (deporte.getId()==1) {
                horas[x] = new HoraClase(LocalTime.of(cont, 0));
            }

            if (cont==13){
                cont = cont + 4;
            }else {
                cont++;
            }
        }*/
    }

    public DayOfWeek getDiaSemana() {
        return diaSemana;
    }
}
