package Modelo;

public class Archivo {

    private String nombre;
    private String ubicacion;
    private String tipo;
    
    public Archivo(String nombre, String ubicacion){
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }
    
    public Archivo(String nombre, String ubicacion, String tipo){
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.tipo = tipo;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public boolean cmp(Archivo arch){
        if(getNombre().equals(arch.getNombre())){
            return getUbicacion().equals(arch.getUbicacion());
        }else return false;
    }
}
