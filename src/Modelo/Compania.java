
package Modelo;


public class Compania {
    
    int idCompania;
    String nombre;
    String direccion;
    
    public Compania() {
    }

    public Compania(int idCompania, String nombre, String direccion) {
        this.idCompania = idCompania;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public int getIdCompania() {
        return idCompania;
    }

    public void setIdCompania(int idCompania) {
        this.idCompania = idCompania;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
        
    }
    
   
    
}
