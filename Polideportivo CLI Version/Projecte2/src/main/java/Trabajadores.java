import java.io.Serial;
import java.time.LocalDate;

public abstract class Trabajadores extends Persona{
    @Serial
    private final static long serialVersionUID = 8929955013085376663L;
    public Trabajadores(String nom, String surn1, String surn2, int telef, LocalDate datanaix, String dni, String passwd) {
        super(nom, surn1, surn2, telef, datanaix, dni, passwd);
    }

}
