package com.example.ultimatefx.controlador;

import com.example.ultimatefx.controlador.system.Sistema;
import com.example.ultimatefx.dao.Actividad;
import com.example.ultimatefx.dao.HorasReserva;
import com.example.ultimatefx.dao.Persona;
import com.example.ultimatefx.dao.Reserva;
import com.example.ultimatefx.modelos.CalendarModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * La clase CalendarController es el controlador de la vista del calendario de reservaciones
 * @author alumne
 * @version java 20
 */
public class CalendarController {

    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private GridPane myGrid;
    @FXML
    private DatePicker dateCalendar;
    @FXML
    private ComboBox<Actividad> activityCombo;
    @FXML
    private Button exitButton;
    private final ComboBox<String> txtDni = createDniTextField();

    private List<HorasReserva> horas;
    private List<Reserva> reservas;
    private List<LocalDate> dates;
    private List<Actividad> actividades;

    /**
     * Atributo que contiene la actividad de la cual se muestran los datos en el calendario
     */
    private Actividad calendarActivitySelected = null;


    /**
     * Método que se inicializa cuando se carga la vista
     * @see #loadData()
     * @see #loadEvents()
     */
    @FXML
    private void initialize() {
        loadData();
        loadEvents();
    }

    /**
     * Método encargado de cargar la información requerida en la vista
     * @see #isUserOffice()
     * @see #setDefaultDate()
     * @see #loadActivities()
     * @see #loadHours()
     * @see #loadDynamicElements()
     */
    private void loadData(){
        if (isUserOffice()){
            rootAnchorPane.getChildren().add(txtDni);
            myGrid.setDisable(true);
        }
        setDefaultDate();
        loadActivities();
        loadHours();
        loadDynamicElements();
    }

    /**
     * Este método carga la información que el usuario puede actualizar
     * @see #loadDates()
     * @see #loadReservations()
     * @see #generateGridPaneChilds()
     */
    private void loadDynamicElements(){
        loadDates();
        loadReservations();
        generateGridPaneChilds();
    }

    /**
     * Este método establece la actividad por defecto
     * @see #activityCombo
     * @see #calendarActivitySelected
     */
    private void setDefaultActivity(){
        activityCombo.setValue(actividades.get(0));
        calendarActivitySelected = actividades.get(0);
    }

    /**
     * Este método establece la fecha por defecto
     * @see #dateCalendar
     */
    private void setDefaultDate(){
        dateCalendar.setValue(LocalDate.now());
    }

    /**
     * Método encargado de mostrar las fechas
     * @see #dates
     * @see #dateCalendar
     */
    private void loadDates(){
        if (dates!=null && !dates.isEmpty()){
            dates.clear();
        }
        dates = getDatesCurrentWeek(dateCalendar.getValue());
    }

    /**
     * Método encargado de cargar las horas disponibles
     * @see #horas
     * @see CalendarModel#requestGetHoras()
     */
    private void loadHours(){
        horas = CalendarModel.getInstance().requestGetHoras();
    }

    /**
     * Método encargado de cargar las reservas dentro del rango de semanas
     * @see #reservas
     * @see CalendarModel#requestGetReservasSemana(LocalDate, LocalDate)
     */
    private void loadReservations(){
        reservas = CalendarModel.getInstance().requestGetReservasSemana(dates.get(0), dates.get(dates.size() - 1));
    }

    /**
     * Método que carga las actividades disponibles y las añade al desplegable
     * @see #actividades
     * @see #activityCombo
     * @see #setDefaultActivity()
     */
    private void loadActivities(){
        actividades = CalendarModel.getInstance().requestGetActividades();
        for (Actividad a:
                actividades) {
            activityCombo.getItems().add(a);
        }
        setDefaultActivity();
    }

    /**
     * Método que añade los elementos necesarios para rellenar el calendario
     * @see #myGrid
     * @see #createTitleLabel()
     * @see #createDaysOfWeekLabel(int)
     * @see #createHoursLabel(int)
     * @see #createIndividualReservationButton(int, int)
     */
    private void generateGridPaneChilds(){
        myGrid.getChildren().clear();
        for(int i = 0; i < myGrid.getColumnCount(); i++){
            for(int j = 0; j < myGrid.getRowCount(); j++) {
                int finalI = i;
                int finalJ = j;

                if (finalI == 0 && finalJ == 0){
                    myGrid.add(createTitleLabel(), i, j);
                } else if (finalJ == 0) {
                    myGrid.add(createDaysOfWeekLabel(finalI), i, j);
                } else if (finalI == 0) {
                    myGrid.add(createHoursLabel(finalJ), i, j);
                } else {
                    myGrid.add(createIndividualReservationButton(finalI, finalJ), i, j);
                }

            }
        }
    }

    /**
     * Método que crea un elemento Label para el título del calendario
     * @return Label con el título del calendario
     */
    private Label createTitleLabel(){
        return new Label("CALENDARIO");
    }

    /**
     * Método que crea un elemento Label que representa su dia de la semana correspondiente
     * @param col Coordenada de la posición del label int
     * @return Label con el nombre del dia de la semana
     */
    private Label createDaysOfWeekLabel(int col){
        return new Label(String.valueOf(dates.get(col-1).getDayOfWeek()));
    }

    /**
     * Método que crea un elemento Label que representa su hora correspondinete
     * @param row Coordenada de la posición del label int
     * @return Label con el nombre de la hora
     */
    private Label createHoursLabel(int row){
        return new Label(String.valueOf(horas.get(row-1).getTime()));
    }

    /**
     * Método que crea un elemento Button con el cual el usuario interactuará para hacer reservas
     * @param col Coordenada de la posición del label int
     * @param row Coordenada de la posición del label int
     * @return Button con la funcionalidad de reservar su fecha correspondiente
     * @see #isDisponible(LocalDateTime, String)
     * @see #setButtonClickedValues(Button)
     * @see #createReserva(int, int)
     * @see #createReservaTemp(int, int)
     * @see #setButtonClickedValues(Button)
     */
    private Button createIndividualReservationButton(int col, int row){
        Button btn = new Button("disponible");
        LocalTime localTime = LocalTime.parse(String.valueOf(horas.get(row-1).getTime()));
        LocalDateTime localDateTime = LocalDateTime.of(dates.get(col-1), localTime);
        if(!isDisponible(localDateTime, calendarActivitySelected.getNombre())) {
            setButtonClickedValues(btn);
        }
        btn.setOnAction(actionEvent -> {
            createReserva(col, row);
            createReservaTemp(col, row);
            setButtonClickedValues(btn);

        });
        return btn;
    }

    /**
     * Método que retorna los dias de la semana a la que pertenece la fecha pasado como parámetro
     * @param date Fecha de la cual se deben mostrar los datos en el calendario LocalDate
     * @return Lista con las fechas de la semana List<LocalDate>
     */
    private List<LocalDate> getDatesCurrentWeek(LocalDate date){
        List<LocalDate> dates = new ArrayList<>();
        TemporalField fieldISO = WeekFields.of(Locale.FRANCE).dayOfWeek();
        for(int i = 1; i <= 6; i++){
            dates.add(date.with(fieldISO, i));
        }
        return dates;
    }

    /**
     * Método que muestra si un botón de reserva está disponible o no
     * @param horaReserva Fecha a consultar LocalDateTime
     * @param actividad Actividad a consultar String
     * @return Retorna false en caso de que la fecha especificada esté reservada boolean
     */
    private boolean isDisponible(LocalDateTime horaReserva, String actividad){
        for (Reserva reserva: reservas){
            boolean fechaTrue = reserva.getFecha().equals(horaReserva);
            boolean activityTrue = reserva.getNomActividad().equals(actividad);
            if(fechaTrue && activityTrue){
                return false;
            }
        }
        return true;
    }

    /**
     * Carga en memoria la reserva realizada para poder mostrarlo en la vista sin actualizar todo
     * @param date Fecha de la reserva
     * @param hour Hora de la reserva
     * @see #getUserSelected()
     * @see #dates
     * @see #calendarActivitySelected
     * @see Reserva#Reserva(int, String, LocalDateTime, String, String, String)
     */
    private void createReservaTemp(int date, int hour){
        String cliente = getUserSelected().getDni();
        String fecha = dates.get(date-1).toString()+'T'+horas.get(hour-1).getTime();
        String actividad = calendarActivitySelected.getNombre();
        reservas.add(
                new Reserva(0, cliente, LocalDateTime.parse(fecha), actividad)
        );
    }

    /**
     * Método que le pide al modelo establecer la reserva en la base de datos
     * Nota: Este método actua diferente dependiendo del usuario que haya iniciado sesión.
     * En caso de que sea cliente, el dni será el suyo y si el usuario es un oficinista el dni será el que este seleccione en el desplegable.
     * @param date Fecha de la reserva
     * @param hour Hora de la reserva
     * @see #getUserSelected()
     * @see #txtDni
     * @see CalendarModel#requestSetReserva(String, String, int)
     */
    private void createReserva(int date, int hour){
        String userDni;
        if (getUserSelected().getType().equals("Oficinista")){
            userDni = txtDni.getValue();
        } else {
            userDni = getUserSelected().getDni();
        }
        String reservationDate = dates.get(date-1).toString()+' '+horas.get(hour-1).getTime();
        int activityId = calendarActivitySelected.getId();
        CalendarModel.getInstance().requestSetReserva(userDni, reservationDate, activityId);
    }

    /**
     * Retorna un ComboBox con los dni de los clientes.
     * @return ComboBox<String> con el dni de los clientes.
     * @see #txtDni
     * @see CalendarModel#requestGetDniClientes()
     */
    private ComboBox<String> createDniTextField(){
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setValue("User DNI");
        comboBox.setLayoutX(527);
        comboBox.setLayoutY(43);

        for (String s: CalendarModel.getInstance().requestGetDniClientes()){
            comboBox.getItems().add(s);
        }

        return comboBox;
    }

    /**
     * Retorna si el usuario del sistema es de tipo Oficinista
     * @return Retorna si el usuario es Oficinista boolean
     * @see #getUserSelected()
     */
    private boolean isUserOffice(){
        return getUserSelected().getType().equals("Oficinista");
    }

    /**
     * Método que establece las propiedades de un botón cuya actividad está reservada
     * @param btn Botton a cambiar
     */
    private void setButtonClickedValues(Button btn){
        btn.setText("No disponible");
        btn.setDisable(true);
    }

    /**
     * Método que almacena los eventos de los elementos FXML
     * @see #dateCalendar
     * @see #loadDynamicElements()
     * @see #activityCombo
     * @see #activityComboEvent()
     * @see #exitButton
     * @see #exitStage()
     * @see #txtDni
     * @see #myGrid
     */
    private void loadEvents(){
        // Seleccionar fecha y acyualizar botones
        dateCalendar.setOnAction(actionEvent -> loadDynamicElements());
        // Seleccionar actividad y actualizar botones
        activityCombo.setOnAction(actionEvent -> activityComboEvent());
        // Salir de la escena
        exitButton.setOnAction(actionEvent -> exitStage());
        // Si el combobox que se genera si el usuario seleccionado es un oficinista está vacío, el grid se deshabilitará
        txtDni.setOnAction(actionEvent -> myGrid.setDisable(false));
    }

    /**
     * Método que actualiza las actividades cuando el usuario cambia la actividad en el desplegable
     * @see #calendarActivitySelected
     * @see #loadDynamicElements()
     */
    private void activityComboEvent(){
        calendarActivitySelected = activityCombo.getSelectionModel().getSelectedItem();
        loadDynamicElements();
    }

    /**
     * @return Retorna la persona que ha iniciado sesión
     * @see Sistema#userSelected
     */
    private Persona getUserSelected(){
        return  Sistema.getInstance().userSelected;
    }

    /**
     * Método encargado de cambiar la escena
     * @see Sistema#cambiarEscena(String, int, int)
     */
    private void exitStage(){
        Sistema.getInstance().cambiarEscena("user-view.fxml", 900, 600);
    }

}