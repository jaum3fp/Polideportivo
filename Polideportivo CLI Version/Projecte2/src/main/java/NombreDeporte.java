public enum NombreDeporte {
    FUTBOL(0),
    BALONCESTO(0),
    TENIS(0),
    PADEL(0),
    FRONTON(0),
    YOGA(1),
    SPINNING(1);

    NombreDeporte(int id){
        this.id=id;
    }

    private int id;

    public int getId(){
        return this.id;
    }
}
