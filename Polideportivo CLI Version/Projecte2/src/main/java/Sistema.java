import base.Writable;

import java.io.PrintStream;
import java.time.*;
import java.util.*;

public class Sistema {
    public static LinkedList<Usuarios> usuarios = new LinkedList<>();
    public static LinkedList<Trabajadores> trabajadores = new LinkedList<>();
    public static LinkedList<Deporte> deportes = new LinkedList<>();
    public static final Scanner SCANNER = new Scanner(System.in);
    public static final PrintStream PRINT_STREAM = System.out;

    public static void crearUsuarioSistema(){

        Sistema.PRINT_STREAM.print("A continuación introduzca los datos del usuario.\nPaso 1 -> Nombre: ");
        String registroNombre = SCANNER.next();
        Sistema.PRINT_STREAM.print("\nPaso 2 -> Primer apellido: ");
        String registroApellido1 = SCANNER.next();
        Sistema.PRINT_STREAM.print("\nPaso 3 -> Segundo apellido: ");
        String registroApellido2 = SCANNER.next();
        Sistema.PRINT_STREAM.print("\nPaso 4 -> Telefono: ");
        int registroTelefono = SCANNER.nextInt();
        Sistema.PRINT_STREAM.print("\nPaso 5 -> Fecha de nacimiento: \n\tAño: ");
        int registroAnyo = SCANNER.nextInt();
        Sistema.PRINT_STREAM.print("\tNumero de mes: ");
        int registroMes = SCANNER.nextInt();
        Sistema.PRINT_STREAM.print("\tDia: ");
        int registroDia = SCANNER.nextInt();
        Sistema.PRINT_STREAM.print("\nPaso 6 -> DNI: ");
        String registroDni = SCANNER.next();
        Sistema.PRINT_STREAM.print("\nPaso 7 -> Contraseña: ");
        String registroPasswd = SCANNER.next();

        Sistema.usuarios.add(new Usuarios(registroNombre, registroApellido1, registroApellido2, registroTelefono, LocalDate.of(registroAnyo, registroMes, registroDia), registroDni, registroPasswd));
        Sistema.PRINT_STREAM.println("El usuario ha sido registrado!");
    }

    public static void crearTrabajadorSistema(){
        Sistema.PRINT_STREAM.println("Especifique la profesión:\n1 - Oficinista\n2 - Entrenador/Arbitro");
        int answ = SCANNER.nextInt();
        Sistema.PRINT_STREAM.print("A continuación introduzca los datos del usuario.\nPaso 1 -> Nombre: ");
        String registroNombre = SCANNER.next();
        Sistema.PRINT_STREAM.print("\nPaso 2 -> Primer apellido: ");
        String registroApellido1 = SCANNER.next();
        Sistema.PRINT_STREAM.print("\nPaso 3 -> Segundo apellido: ");
        String registroApellido2 = SCANNER.next();
        Sistema.PRINT_STREAM.print("\nPaso 4 -> Telefono: ");
        int registroTelefono = SCANNER.nextInt();
        Sistema.PRINT_STREAM.print("\nPaso 5 -> Fecha de nacimiento: \n\tAño: ");
        int registroAnyo = SCANNER.nextInt();
        Sistema.PRINT_STREAM.print("\tNumero de mes: ");
        int registroMes = SCANNER.nextInt();
        Sistema.PRINT_STREAM.print("\tDia: ");
        int registroDia = SCANNER.nextInt();
        Sistema.PRINT_STREAM.print("\nPaso 6 -> DNI: ");
        String registroDni = SCANNER.next();
        Sistema.PRINT_STREAM.print("\nPaso 7 -> Contraseña: ");
        String registroPasswd = SCANNER.next();

        if (answ==1)
            Sistema.trabajadores.add(new Oficinistas(registroNombre, registroApellido1, registroApellido2, registroTelefono, LocalDate.of(registroAnyo, registroMes, registroDia), registroDni, registroPasswd));
        else if (answ==2)
            Sistema.trabajadores.add(new TrabajadoresDeportistas(registroNombre, registroApellido1, registroApellido2, registroTelefono, LocalDate.of(registroAnyo, registroMes, registroDia), registroDni, registroPasswd));
        Sistema.PRINT_STREAM.println("El usuario ha sido registrado!");
    }

    public static void eliminarUsuarioSistema(){
        Usuarios userSelected = Sistema.seleccionarUsuario();
        Sistema.usuarios.remove(userSelected);
    }

    public static void eliminarTrabajadorSistema(){
        Trabajadores workerSelected = Sistema.seleccionarTrabajador();
        Sistema.trabajadores.remove(workerSelected);
    }

    public static void sancionarUsuarioSistema(){
        Usuarios userSelected = null;
        userSelected = Sistema.seleccionarUsuario();
        if (userSelected.isHabilitado()){
            userSelected.setHabilitado(false);
        }else {
            Sistema.PRINT_STREAM.println("Este usuario ya ha sido sancionado");
        }
        Sistema.PRINT_STREAM.println("Motivo de la sancion:"); String motivo = SCANNER.next();
        userSelected.setSancion(motivo);
    }

    public static void perdonarUsuarioSistema(){
        Usuarios userSelected = Sistema.seleccionarUsuario();;
        if (!userSelected.isHabilitado()){
            userSelected.setHabilitado(true);
        }else {
            Sistema.PRINT_STREAM.println("Este usuario no está sancionado");
        }
        userSelected.setSancion("");
    }

    public static void sancionarTrabajadorSistema(){
        Trabajadores workerSelected = Sistema.seleccionarTrabajador();
        if (workerSelected.isHabilitado()){
            workerSelected.setHabilitado(false);
        }else {
            Sistema.PRINT_STREAM.println("Este trabajador ya ha sido sancionado");
        }
        Sistema.PRINT_STREAM.println("Motivo de la sancion:"); String motivo = SCANNER.next();
        workerSelected.setSancion(motivo);
    }

    public static void perdonarTrabajadorSistema(){
        Trabajadores workerSelected = Sistema.seleccionarTrabajador();
        if (!workerSelected.isHabilitado()){
            workerSelected.setHabilitado(true);
        }else {
            Sistema.PRINT_STREAM.println("Este trabajador no está sancionado");
        }
        workerSelected.setSancion("");
    }

    public static void habilitarDeporteSistema(){

        /* BUSCA DEPORTE */

        NombreDeporte deporte = null;
        boolean deporteOk;
        Deporte sportSelected = null;
        /* LISTAR DEPORTES */
        StringBuilder res = new StringBuilder();
        for (int x = 0; x < Sistema.deportes.size(); x++){
            res.append("\n\t").append(x + 1).append(" - ").append(Sistema.deportes.get(x).getNom());
        }

        do {
            Sistema.PRINT_STREAM.print("Deporte: "+res);
            int answin = SCANNER.nextInt();
            deporteOk = true;
            switch (answin) {
                case 1 -> deporte = NombreDeporte.FUTBOL;
                case 2 -> deporte = NombreDeporte.BALONCESTO;
                case 3 -> deporte = NombreDeporte.TENIS;
                case 4 -> deporte = NombreDeporte.PADEL;
                case 5 -> deporte = NombreDeporte.FRONTON;
                case 6 -> deporte = NombreDeporte.YOGA;
                case 7 -> deporte = NombreDeporte.SPINNING;
            }
            for (int x = 0; x < Sistema.deportes.size(); x++) {
                if (Sistema.deportes.get(x).getNom().equals(deporte)) {
                    sportSelected = Sistema.deportes.get(x);
                }
            }
            if (sportSelected == null){
                deporteOk = false;
                Sistema.PRINT_STREAM.println("ERROR: Este deporte no está registrado.");
            }

        }while (!deporteOk);

        /* HABILITA DEPORTE */

        if (sportSelected.getHabilitado())
            Sistema.PRINT_STREAM.println("Este deporte ya estaba habilitado");
        else
            sportSelected.setHabilitado(true);
    }

    public static void deshabilitarDeporteSistema(){

        /* BUSCA DEPORTE */

        Deporte sportSelected = sportSelected = Sistema.seleccionarDeporte();

        /* DESHABILITAR DEPORTE */

        if (!sportSelected.getHabilitado())
            Sistema.PRINT_STREAM.println("Este deporte ya estaba deshabilitado");
        else
            sportSelected.setHabilitado(false);
    }

    private static LocalDate semana;

    public static void reservarUsuarioSistema(){
        /* BUSCAR USUARIO */

        Sistema.PRINT_STREAM.print("A continuacion introduzca los datos de la reserva.\nPaso 1 -> ");

        Usuarios userSelected = Sistema.seleccionarUsuario();;

        /* BUSCAR DEPORTE */

        Sistema.PRINT_STREAM.print("Paso 2 -> ");

        Deporte sportSelected = Sistema.seleccionarDeporte();;

        /* BUSCAR SEMANA */

        Sistema.PRINT_STREAM.print("Paso 3 -> ");

        Semana weekSelected = Sistema.seleccionarSemana(sportSelected);;

        weekSelected.mostrarCalendarioSemana();

        /* BUSCAR DIA */

        Sistema.PRINT_STREAM.print("Paso 4 -> ");

        Dia daySelected = Sistema.seleccionarDia(weekSelected, semana.getDayOfWeek());;

        /* BUSCAR HORA */

        Hora hourSelected = null;
        boolean horaOcupada = false;
        do {
            Sistema.PRINT_STREAM.print("Paso 3 -> ");

            horaOcupada = false;
            hourSelected = Sistema.seleccionarHora(daySelected);

            if (sportSelected.getNom().getId()==0){
                if (((HoraPartido) hourSelected).getUserClaim()!=null){
                    Sistema.PRINT_STREAM.println("Esta hora está reservada");
                    horaOcupada = true;
                }
            } else if (sportSelected.getNom().getId()==1) {
                if (((HoraClase) hourSelected).getEstado()==20){
                    Sistema.PRINT_STREAM.println("Esta clase está llena");
                    horaOcupada = true;
                }
            }

            /* ESTABLECER RESERVA */

        }while (horaOcupada);

        Sistema.establecerReserva(userSelected, hourSelected);

        /* REGISTRAR RESERVA EN EL USUARIO */

        userSelected.reservas.add(new ActividadUsuario(sportSelected.getNom(), semana, weekSelected.getInicio(), weekSelected.getFin(), daySelected.getDiaSemana(), hourSelected.getHora()));

        /* RESULTADO */

        weekSelected.mostrarCalendarioSemana();

    }

    public static void reservarUsuarioSistema(String usuario){
        /* BUSCAR USUARIO */

        Usuarios userSelected = Sistema.seleccionarUsuarioEspecifico(usuario);

        /* BUSCAR DEPORTE */

        Sistema.PRINT_STREAM.print("A continuacion introduzca los datos de la reserva.\nPaso 1 -> ");

        Deporte sportSelected = sportSelected = Sistema.seleccionarDeporte();;

        /* BUSCAR SEMANA */

        Sistema.PRINT_STREAM.print("Paso 2 -> ");

        Semana weekSelected = Sistema.seleccionarSemana(sportSelected);;

        weekSelected.mostrarCalendarioSemana();

        /* BUSCAR DIA */

        Dia daySelected = Sistema.seleccionarDia(weekSelected, semana.getDayOfWeek());;

        /* BUSCAR HORA */
        Hora hourSelected = null;
        boolean horaOcupada = false;
        do {
            Sistema.PRINT_STREAM.print("Paso 3 -> ");

            horaOcupada = false;
            hourSelected = Sistema.seleccionarHora(daySelected);

            if (sportSelected.getNom().getId()==0){
                if (((HoraPartido) hourSelected).getUserClaim()!=null){
                    Sistema.PRINT_STREAM.println("Esta hora está reservada");
                    horaOcupada = true;
                }
            } else if (sportSelected.getNom().getId()==1) {
                if (((HoraClase) hourSelected).getEstado()==20){
                    Sistema.PRINT_STREAM.println("Esta clase está llena");
                    horaOcupada = true;
                }
            }

            /* ESTABLECER RESERVA */
        }while (horaOcupada);

                Sistema.establecerReserva(userSelected, hourSelected);

        /* REGISTRAR RESERVA EN EL USUARIO */

        userSelected.reservas.add(new ActividadUsuario(sportSelected.getNom(), semana, weekSelected.getInicio(), weekSelected.getFin(), daySelected.getDiaSemana(), hourSelected.getHora()));

        /* RESULTADO */

        weekSelected.mostrarCalendarioSemana();

    }

    public static void assignarTrabajadorDeportistaSistema(){ /* MAL */
        Trabajadores workerSelected = null;
        Deporte sportSelected = null;
        Sistema.PRINT_STREAM.print("Paso 1 ->");
        Usuarios userSelect = Sistema.seleccionarUsuario();
        Sistema.PRINT_STREAM.println("Paso 2 -> Estas son las actividades del usuario, selecciona una "+userSelect.getNom()+"!\n");
        Actividad activitySelected = null;
        boolean isAccepted = false;
        boolean isAllAccepted = false;
        do {
            int cont = 0;
            int cont2 = 0;
            isAccepted = false;
            for (Actividad x : userSelect.reservas) {
                Sistema.PRINT_STREAM.println(cont + " - " + x);
                cont++;
                if (((ActividadUsuario) x).getEstado().equals("ACEPTADO")){
                    cont2++;
                }
            }
            if (cont2==cont){
                Sistema.PRINT_STREAM.println("Todas las actividades de este usuario ya han sido asignadas!");
                isAllAccepted = true;
            }else {
                int answ = SCANNER.nextInt();
                activitySelected = userSelect.reservas.get(answ);
                if (((ActividadUsuario)activitySelected).getEstado().equals("ACEPTADO")){
                    Sistema.PRINT_STREAM.println("Esta actividad ya ha sido asignada!");
                    isAccepted = true;
                }
            }
        }while (isAccepted);
        if (!isAllAccepted) {
            Sistema.PRINT_STREAM.print("Paso 3 ->");
            boolean parar;
            do {
                parar = false;

                workerSelected = Sistema.seleccionarTrabajador();
                sportSelected = Sistema.seleccionarDeporteEspecifico(activitySelected.getDeporte());

                ((ActividadUsuario) activitySelected).setSportWorker((TrabajadoresDeportistas) workerSelected);
                if (((TrabajadoresDeportistas) workerSelected).curriculumVitae.contains(sportSelected)) {
                    ((ActividadUsuario) activitySelected).setSportWorker((TrabajadoresDeportistas) workerSelected);
                    parar = true;
                } else {
                    Sistema.PRINT_STREAM.println("ERROR: Este trabajador no esta capacitado para este deporte.");
                }

            } while (!parar);

            ((TrabajadoresDeportistas) workerSelected).solicitud.add(new ActividadTrabajador(activitySelected.getDeporte(), activitySelected.getFecha(), activitySelected.getSemanaIni(), activitySelected.getSemanaFin(), activitySelected.getDia(), activitySelected.getHora()));

            ActividadTrabajador workerActivitySelected = null;
            for (int x = 0; x < ((TrabajadoresDeportistas) workerSelected).solicitud.size(); x++) {
                if (((TrabajadoresDeportistas) workerSelected).solicitud.get(x).getFecha() == activitySelected.getFecha()) {
                    workerActivitySelected = (ActividadTrabajador) ((TrabajadoresDeportistas) workerSelected).solicitud.get(x);
                }
            }

            if (sportSelected.getNom().getId() == 0) {
                workerActivitySelected.userAssigned = userSelect;
            } else if (sportSelected.getNom().getId() == 1) {
                workerActivitySelected.usersAssigned.add(userSelect);
            }
        }
    }

    public static void rellenarCurriculumTrabajadorDeportistaSistema(String dni){
        Sistema.PRINT_STREAM.println("A continuacion vamos a selecciona los deportes para los que estás capacitado!");
        Trabajadores workerSelected = Sistema.seleccionarTrabajadorEspecifico(dni);
        boolean heterminado = false;
        do {
            Deporte sportSelected = Sistema.seleccionarDeporte();;
            if (((TrabajadoresDeportistas) workerSelected).curriculumVitae.contains(sportSelected)){
                Sistema.PRINT_STREAM.println("Este deporte ya ha sido seleccionado");
            }else {
                ((TrabajadoresDeportistas) workerSelected).curriculumVitae.add(sportSelected);
            }
            Sistema.PRINT_STREAM.println("1 - Seguir.\n2 - Parar"); int answ = SCANNER.nextInt();
            if (answ==2){
                heterminado = true;
            }
        }while(!heterminado);
        Sistema.PRINT_STREAM.println("Así ha quedado el curriculum:"+((TrabajadoresDeportistas) workerSelected).curriculumVitae);
    }

    public static void cerrarSesion(){
        repeat = false;
    }

    public static boolean repeat = true;

    private static Usuarios seleccionarUsuario(){
        Usuarios userSelected = null;
        boolean usuarioOk;
        do {
            Sistema.PRINT_STREAM.println("DNI de usuario: ");
            String usuario = SCANNER.next();
            usuarioOk = true;
            for (int x = 0; x < Sistema.usuarios.size(); x++) {
                if (Sistema.usuarios.get(x).getDni().equals(usuario)) {
                    userSelected = Sistema.usuarios.get(x);
                }
            }
            if (userSelected == null){
                usuarioOk = false;
                Sistema.PRINT_STREAM.println("ERROR: Este usuario no está registrado.");
            }
        }while (!usuarioOk);
        return userSelected;
    }
    private static Usuarios seleccionarUsuarioEspecifico(String usuario){
        Usuarios userSelected = null;
        boolean usuarioOk;
        do {
            usuarioOk = true;
            for (int x = 0; x < Sistema.usuarios.size(); x++) {
                if (Sistema.usuarios.get(x).getDni().equals(usuario)) {
                    userSelected = Sistema.usuarios.get(x);
                }
            }
            if (userSelected == null){
                usuarioOk = false;
                Sistema.PRINT_STREAM.println("ERROR: Este usuario no está registrado.");
            }
        }while (!usuarioOk);
        return userSelected;
    }

    private static Deporte seleccionarDeporte(){
        Deporte sportSelected = null;
        NombreDeporte deporte = null;
        boolean deporteOk;

        /* LISTAR DEPORTES */
        StringBuilder res = new StringBuilder();
        for (int x = 0; x < Sistema.deportes.size(); x++){
            res.append("\n\t").append(x + 1).append(" - ").append(Sistema.deportes.get(x).getNom());
        }

        do {
            Sistema.PRINT_STREAM.print("Deporte: "+res);
            int answin = SCANNER.nextInt();
            deporteOk = true;
            switch (answin) {
                case 1 -> deporte = NombreDeporte.FUTBOL;
                case 2 -> deporte = NombreDeporte.BALONCESTO;
                case 3 -> deporte = NombreDeporte.TENIS;
                case 4 -> deporte = NombreDeporte.PADEL;
                case 5 -> deporte = NombreDeporte.FRONTON;
                case 6 -> deporte = NombreDeporte.YOGA;
                case 7 -> deporte = NombreDeporte.SPINNING;
            }
            for (int x = 0; x < Sistema.deportes.size(); x++) {
                if (Sistema.deportes.get(x).getNom().equals(deporte)) {
                    sportSelected = Sistema.deportes.get(x);
                }
            }
            if (sportSelected == null){
                deporteOk = false;
                Sistema.PRINT_STREAM.println("ERROR: Este deporte no está registrado.");
            }
            if (sportSelected != null && !sportSelected.getHabilitado()){
                deporteOk = false;
                Sistema.PRINT_STREAM.println("ERROR: Un administrador ha deshabilitado este deporte.");
            }
        }while (!deporteOk);
        return sportSelected;
    }
    private static Deporte seleccionarDeporteEspecifico(NombreDeporte deporte){
        Deporte sportSelected = null;
        boolean deporteOk;
        do {
            deporteOk = true;
            for (int x = 0; x < Sistema.deportes.size(); x++) {
                if (Sistema.deportes.get(x).getNom().equals(deporte)) {
                    sportSelected = Sistema.deportes.get(x);
                }
            }
            if (sportSelected == null){
                deporteOk = false;
                Sistema.PRINT_STREAM.println("ERROR: Este deporte no está registrado.");
            }
            if (sportSelected != null && !sportSelected.getHabilitado()){
                deporteOk = false;
                Sistema.PRINT_STREAM.println("ERROR: Un administrador ha deshabilitado este deporte.");
            }
        }while (!deporteOk);
        return sportSelected;
    }

    private static Semana seleccionarSemana(Deporte sportSelected){
        Semana weekSelected = null;
        boolean semanaOk;
        int contErr = 0;
        do {
            Sistema.PRINT_STREAM.print("Fecha: 2023-");
            semana = LocalDate.parse("2023-"+ SCANNER.next());
            /*System.out.print("Semana(ID): ");*/
            semanaOk = true;
            for (int x = 0; x < sportSelected.semanas.length; x++){
                if (semana.isBefore(sportSelected.semanas[x].getFin()) && semana.isAfter(sportSelected.semanas[x].getInicio()) || semana.equals(sportSelected.semanas[x].getFin()) || semana.equals(sportSelected.semanas[x].getInicio())){
                    weekSelected = sportSelected.semanas[x];
                } else {
                    contErr++;
                }
            }

            /*for (int x = 0; x < sportSelected.semanas.length; x++) {
                if (sportSelected.semanas[x].getId() == semana) {
                    weekSelected = sportSelected.semanas[x];
                }
            }*/
            if (weekSelected == null){
                semanaOk = false;
            }
            if (contErr==sportSelected.semanas.length){
                Sistema.PRINT_STREAM.println("ERROR: Los fines de semana no estamos abiertos.");
                contErr = 0;
            }
        }while (!semanaOk);
        return weekSelected;
    }

    private static Dia seleccionarDia(Semana weekSelected, DayOfWeek dia){
        Dia daySelected = null;
        boolean diaOk;

        do {
            diaOk = true;
            for (int x = 0; x < weekSelected.dias.length; x++) {
                if (weekSelected.dias[x].getDiaSemana().equals(dia)) {
                    daySelected = weekSelected.dias[x];
                }
            }
            if (daySelected == null){
                diaOk = false;
                Sistema.PRINT_STREAM.println("ERROR: Este dia no estamos abiertos.");
            }
        }while (!diaOk);
        return daySelected;
    }

    private static Hora seleccionarHora(Dia daySelected){
        Hora hourSelected = null;
        LocalTime hora = null;
        boolean horaOk;

        do {
            Sistema.PRINT_STREAM.print("Hora(HH:MM): ");
            hora = LocalTime.parse(SCANNER.next());
            horaOk = true;
            for (int x = 0; x < daySelected.horas.length; x++) {
                if (daySelected.horas[x].getHora().equals(hora)) {
                    hourSelected = daySelected.horas[x];
                }
            }
            if (hourSelected == null){
                horaOk = false;
                Sistema.PRINT_STREAM.println("ERROR: Esta hora no es seleccionable. Comprueba que ha sido escrita correctamente");
            }
        }while (!horaOk);
        return hourSelected;
    }

    private static Trabajadores seleccionarTrabajador(){
        Trabajadores workerSelected = null;
        boolean workerOk;
        do {
            Sistema.PRINT_STREAM.println("DNI de usuario: ");
            String usuario = SCANNER.next();
            workerOk = true;
            for (int x = 0; x < Sistema.trabajadores.size(); x++) {
                if (Sistema.trabajadores.get(x).getDni().equals(usuario)) {
                    workerSelected = Sistema.trabajadores.get(x);
                }
            }
            if (workerSelected == null){
                workerOk = false;
                Sistema.PRINT_STREAM.println("ERROR: Este usuario no está registrado.");
            }
        }while (!workerOk);
        return workerSelected;
    }

    private static Trabajadores seleccionarTrabajadorEspecifico(String usuario){
        Trabajadores workerSelected = null;
        boolean workerOk;
        do {
            workerOk = true;
            for (int x = 0; x < Sistema.trabajadores.size(); x++) {
                if (Sistema.trabajadores.get(x).getDni().equals(usuario)) {
                    workerSelected = Sistema.trabajadores.get(x);
                }
            }
            if (workerSelected == null){
                workerOk = false;
                Sistema.PRINT_STREAM.println("ERROR: Este usuario no está registrado.");
            }
        }while (!workerOk);
        return workerSelected;
    }


    private static void establecerReserva(Usuarios userSelected, Hora hourSelected){
        if (hourSelected instanceof HoraPartido){
            ((HoraPartido)hourSelected).setEstado(true);
            ((HoraPartido) hourSelected).setUserClaim(userSelected);
        } else if (hourSelected instanceof HoraClase) {
            ((HoraClase) hourSelected).incrementEstado();

            for (int x = 0; x < ((HoraClase) hourSelected).claseUsuarios.length; x++){
                if (((HoraClase) hourSelected).claseUsuarios[x]==null){
                    ((HoraClase) hourSelected).claseUsuarios[x] = userSelected;
                    x = ((HoraClase) hourSelected).claseUsuarios.length;
                }
            }
        }
    }

}
