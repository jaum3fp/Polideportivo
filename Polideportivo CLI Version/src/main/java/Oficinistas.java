import java.io.Serial;
import java.time.LocalDate;

public class Oficinistas extends Trabajadores{
    @Serial
    private static final long serialVersionUID = 113127743386719348L;
    public Oficinistas(String nom, String surn1, String surn2, int telef, LocalDate datanaix, String dni, String passwd) {
        super(nom, surn1, surn2, telef, datanaix, dni, passwd);
    }

    @Override
    public String toString() {
        return "Oficinistas{" +
                "nom='" + nom + '\'' +
                ", surn1='" + surn1 + '\'' +
                ", surn2='" + surn2 + '\'' +
                ", telef=" + telef +
                ", datanaix=" + datanaix +
                ", dni='" + dni + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }

    public void daraltaUsuario(){
        Sistema.crearUsuarioSistema();
    }
    public void reservarUsuario(){
        Sistema.reservarUsuarioSistema();
    }
    public void asignarTrabajadorDeportista(){
        Sistema.assignarTrabajadorDeportistaSistema();
    }
    public void sancionarUsuario(){Sistema.sancionarUsuarioSistema();}
    public void darbajaUsuario(){Sistema.eliminarUsuarioSistema();}
    public void perdonarUsuario(){Sistema.perdonarUsuarioSistema();}
}
