import java.time.LocalDate;

public class Administradores extends Trabajadores{

    public Administradores(String nom, String surn1, String surn2, int telef, LocalDate datanaix, String dni, String passwd) {
        super(nom, surn1, surn2, telef, datanaix, dni, passwd);
    }

    public void crearTrabajador(){
        Sistema.crearTrabajadorSistema();
    }
    public void eliminarTrabajador(){Sistema.eliminarTrabajadorSistema();}
    public void sancionarTrabajador(){Sistema.sancionarTrabajadorSistema();}
    public void perdonarTrabajador(){Sistema.perdonarTrabajadorSistema();}
    public void habilitarDeporte(){
        Sistema.habilitarDeporteSistema();
    }
    public void deshabilitarDeporte(){
        Sistema.deshabilitarDeporteSistema();
    }
    
}
