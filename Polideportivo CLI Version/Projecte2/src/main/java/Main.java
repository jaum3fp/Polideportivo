import util.FileManager;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        /* CARGAR DATOS */

        Sistema.PRINT_STREAM.println("Cargando Datos...");
        for (Object u:
                FileManager.loadFileList("data/Usuarios.txt")) {
            Sistema.usuarios.add((Usuarios) u);
        }

        for (Object t:
                FileManager.loadFileList("data/Trabajadores.txt")) {
            Sistema.trabajadores.add((Trabajadores) t);
        }

        for (Object d:
                FileManager.loadFileList("data/Deportes.txt")) {
            Sistema.deportes.add((Deporte) d);
        }

        /* DATOS VOLÁTILES */

        /*Administradores adm = new Administradores("admin", null, null, 0, null, "09090909Z", "admin");
        Sistema.trabajadores.add(adm);

        Usuarios us1 = new Usuarios("Donatello", "Bonaparte", "Malaparte",
                827936283, LocalDate.of(1987, 4, 17), "00000000V", "Calambre");
        TrabajadoresDeportistas wk2 = new TrabajadoresDeportistas("Albaca", "Tamboro", "Kentaro",
                732957305, LocalDate.of(1995, 8, 12), "99999999S", "passwd");
        Oficinistas of1 = new Oficinistas("Ricardo", "Buenafuente", "Carrasco",
                666666666, LocalDate.of(1987, 4, 17), "12345678A", "forger");

        Sistema.usuarios.add(us1);
        Sistema.trabajadores.add(wk2);
        Sistema.trabajadores.add(of1);

        for (int x = 1; x <= 7; x++){
            switch (x) {
                case 1 -> Sistema.deportes.add(new Deporte(NombreDeporte.FUTBOL));
                case 2 -> Sistema.deportes.add(new Deporte(NombreDeporte.BALONCESTO));
                case 3 -> Sistema.deportes.add(new Deporte(NombreDeporte.TENIS));
                case 4 -> Sistema.deportes.add(new Deporte(NombreDeporte.PADEL));
                case 5 -> Sistema.deportes.add(new Deporte(NombreDeporte.FRONTON));
                case 6 -> Sistema.deportes.add(new Deporte(NombreDeporte.YOGA));
                case 7 -> Sistema.deportes.add(new Deporte(NombreDeporte.SPINNING));
            }
        }*/

        /* INTERFAZ DE USUARIO */

        boolean exitPass = false;

        do {

            /* SELECCION DE APLICACCION */

            Sistema.PRINT_STREAM.println("""
                    Binevenido a la aplicacon del poli.
                    1 - Usuario
                    2 - Trabajador
                    0 - Salir""");
            int answ0 = sc.nextInt();

            if (answ0 == 1) {
                boolean loginNombrePass = false;
                boolean loginPasswdPass = false;
                boolean loginPass = false;
                int loginAttemps = 2;
                Usuarios userSelected = null;

                /* LOGIN USUARIO */

                do {
                    Sistema.PRINT_STREAM.println("DNI de usuario: ");
                    String loginName = sc.next();
                    int cont = Sistema.usuarios.size();
                    for (int x = 0; x < Sistema.usuarios.size(); x++) {
                        if (Sistema.usuarios.get(x).getDni().equals(loginName)) {
                            loginNombrePass = true;
                            userSelected = Sistema.usuarios.get(x);
                            break;
                        } else {
                            cont--;
                        }
                        if (cont == 0){
                            Sistema.PRINT_STREAM.println("Este usuario no existe.");
                        }
                    }
                } while (!loginNombrePass);
                do {
                    Sistema.PRINT_STREAM.println("Introduzca su contraseña: ");
                    String loginPasswd = sc.next();
                    if (userSelected.getPasswd().equals(loginPasswd)) {
                        loginPasswdPass = true;
                        loginPass = true;
                    } else if (loginAttemps == 0) {
                        loginPasswdPass = true;
                    } else {
                        Sistema.PRINT_STREAM.println("Incorrecto, prueba otra vez.");
                        loginAttemps--;
                    }

                } while (!loginPasswdPass);

                /* ACCIONES USUARIO */

                if (loginPass && userSelected.habilitado) {
                    do {
                        Sistema.repeat = true;
                        Sistema.PRINT_STREAM.println("Hola USUARIO " + userSelected.getNom() +
                                "!\n1 - Perfil" +
                                "\n2 - Hacer reserva" +
                                "\n3 - Ver reservas" +
                                "\n0 - Salir");
                        int answ = sc.nextInt();
                        switch (answ) {
                            case 0 -> Sistema.cerrarSesion();
                            case 1 -> Sistema.PRINT_STREAM.println(userSelected);
                            case 2 -> userSelected.hacerReserva();
                            case 3 -> Sistema.PRINT_STREAM.println(userSelected.reservas);
                        }
                    }while (Sistema.repeat);
                } else if (!userSelected.habilitado){
                    Sistema.PRINT_STREAM.println("Esta cuenta esta desactivada por el siguiente motivo:\n"+userSelected.getSancion());
                } else {
                    Sistema.PRINT_STREAM.println("Inicio de sesión incorrecto.");
                }

            }else if (answ0==2){
                boolean loginNombrePassWorker = false;
                boolean loginPasswdPassWorker = false;
                boolean loginPassWorker = false;
                int loginAttempsWorker = 2;
                Trabajadores workerSelected = null;

                /* LOGIN TRABAJADOR */

                do {
                    Sistema.PRINT_STREAM.println("DNI de trabajador: ");
                    String loginName;
                    do {
                        loginName = sc.next();
                        if (loginName.length()!=9){
                            Sistema.PRINT_STREAM.println("Un dni no tiene esta longitud.");
                        }
                    }while (loginName.length()!=9);
                    int cont = Sistema.trabajadores.size();
                    for (int x = 0; x < Sistema.trabajadores.size(); x++) {
                        if (Sistema.trabajadores.get(x).getDni().equals(loginName)) {
                            loginNombrePassWorker = true;
                            workerSelected = Sistema.trabajadores.get(x);
                            break;
                        }else {
                            cont--;
                        }
                        if (cont == 0){
                            Sistema.PRINT_STREAM.println("Este trabajador no existe.");
                        }
                    }
                } while (!loginNombrePassWorker);
                do {
                    Sistema.PRINT_STREAM.println("Introduzca su contraseña: ");
                    String loginPasswd = sc.next();
                    if (workerSelected.getPasswd().equals(loginPasswd)) {
                        loginPasswdPassWorker = true;
                        loginPassWorker = true;
                    } else if (loginAttempsWorker == 0) {
                        loginPasswdPassWorker = true;
                    } else {
                        Sistema.PRINT_STREAM.println("Incorrecto, prueba otra vez.");
                        loginAttempsWorker--;
                    }

                } while (!loginPasswdPassWorker);

                if (loginPassWorker){

                    /* ACCIONES TRABAJADOR */

                    if (workerSelected instanceof Administradores){
                        do {
                            Sistema.repeat = true;
                            Sistema.PRINT_STREAM.println("Hola ADMINISTRADOR " + workerSelected.getNom() +
                                    "!\n1 - Perfil" +
                                    "\n2 - Crear a un trabajador" +
                                    "\n3 - Eliminar a un trabajador" +
                                    "\n4 - Sancionar a un trabajador" +
                                    "\n5 - Perdonar a un trabajador" +
                                    "\n6 - Deshabilitar un deporte." +
                                    "\n7 - Habilitar un deporte." +
                                    "\n0 - Salir");
                            int answ = sc.nextInt();
                            switch (answ) {
                                case 0 -> Sistema.cerrarSesion();
                                case 1 -> Sistema.PRINT_STREAM.println(workerSelected);
                                case 2 -> ((Administradores) workerSelected).crearTrabajador();
                                case 3 -> ((Administradores) workerSelected).eliminarTrabajador();
                                case 4 -> ((Administradores) workerSelected).sancionarTrabajador();
                                case 5 -> ((Administradores) workerSelected).perdonarTrabajador();
                                case 6 -> ((Administradores) workerSelected).deshabilitarDeporte();
                                case 7 -> ((Administradores) workerSelected).habilitarDeporte();
                            }
                        } while (Sistema.repeat);
                    }else if (workerSelected instanceof Oficinistas){
                        do {
                            Sistema.repeat = true;
                            Sistema.PRINT_STREAM.println("Hola OFICINISTA " + workerSelected.getNom() +
                                    "!\n1 - Perfil" +
                                    "\n2 - Dar de alta a un usuario." +
                                    "\n3 - Dar de baja a un usuario" +
                                    "\n4 - Reservar a nombre de un usuario." +
                                    "\n5 - Asignar un entrenador." + "" +
                                    "\n6 - Sancionar a un usuario." +
                                    "\n7 - Perdonar a un usuario." +
                                    "\n0 - Salir.");
                            int answ = sc.nextInt();
                            switch (answ) {
                                case 0 -> Sistema.cerrarSesion();
                                case 1 -> Sistema.PRINT_STREAM.println(workerSelected);
                                case 2 -> ((Oficinistas) workerSelected).daraltaUsuario();
                                case 3 -> ((Oficinistas) workerSelected).darbajaUsuario();
                                case 4 -> ((Oficinistas) workerSelected).reservarUsuario();
                                case 5 -> ((Oficinistas) workerSelected).asignarTrabajadorDeportista();
                                case 6 -> ((Oficinistas) workerSelected).sancionarUsuario();
                                case 7 -> ((Oficinistas) workerSelected).perdonarUsuario();
                            }
                        } while (Sistema.repeat);
                    }else if (workerSelected instanceof TrabajadoresDeportistas){ /*STRING*/
                        do {
                            Sistema.repeat = true;
                            Sistema.PRINT_STREAM.println("Hola ENTRENADOR " + workerSelected.getNom() +
                                    "!\n1 - Perfil" +
                                    "\n2 - Rellenar curriculum" +
                                    "\n3 - Consultar actividades" +
                                    "\n0 - Salir");
                            int answ = sc.nextInt();
                            switch (answ) {
                                case 0 -> Sistema.cerrarSesion();
                                case 1 -> Sistema.PRINT_STREAM.println(workerSelected);
                                case 2 -> ((TrabajadoresDeportistas) workerSelected).rellenarCurriculumTrabajadorDeportista();
                                case 3 -> Sistema.PRINT_STREAM.println(((TrabajadoresDeportistas) workerSelected).solicitud);
                            }
                        }while (Sistema.repeat);
                    }
                } else {
                    Sistema.PRINT_STREAM.println("Inicio de sesión incorrecto.");
                }
            }else if (answ0==0){
                exitPass = true;

                /* ACTUALIZAR DATOS */

                FileManager.saveFileList("data/Usuarios.txt", Sistema.usuarios);
                FileManager.saveFileList("data/Deportes.txt", Sistema.deportes);
                FileManager.saveFileList("data/Trabajadores.txt", Sistema.trabajadores);
            }
        }while(!exitPass);
        Sistema.PRINT_STREAM.println("Saliendo....");
    }

}