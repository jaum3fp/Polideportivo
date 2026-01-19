import base.Writable;

import java.io.Serial;
import java.io.Serializable;
import java.time.*;

public abstract class Persona implements Serializable {
    @Serial
    private static final long serialVersionUID = -2519428891732936546L;
    protected String nom, surn1, surn2;
    protected int telef;
    protected LocalDate datanaix;
    protected String dni, passwd;
    protected boolean habilitado = true;
    protected String sancion;
    
    public Persona(String nom, String surn1, String surn2, int telef, LocalDate datanaix, String dni, String passwd) {
        this.nom = nom;
        this.surn1 = surn1;
        this.surn2 = surn2;
        this.telef = telef;
        this.datanaix = datanaix;
            this.dni = dni;
        this.passwd=passwd;
    }

    public String getPasswd(){
        return this.passwd;
    }

    public void setPasswd(String passwd){
        this.passwd=passwd;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSurn1() {
        return surn1;
    }

    public void setSurn1(String surn1) {
        this.surn1 = surn1;
    }

    public String getSurn2() {
        return surn2;
    }

    public void setSurn2(String surn2) {
        this.surn2 = surn2;
    }

    public LocalDate getDatanaix() {
        return datanaix;
    }

    public void setDatanaix(LocalDate datanaix) {
        this.datanaix = datanaix;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getTelef() {
        return telef;
    }

    public void setTelef(int telef) {
        this.telef = telef;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public String getSancion() {
        return sancion;
    }

    public void setSancion(String sancion) {
        this.sancion = sancion;
    }
}
