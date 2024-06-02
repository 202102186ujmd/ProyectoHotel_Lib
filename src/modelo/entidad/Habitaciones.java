package modelo.entidad;

/**
 * Universidad Dr. Jose Matias Delgado
 * Catedra Programacion de computadoras 4
 * @author Pedro Navarrete
 *          202102186
 */
public class Habitaciones {
    private int idhabitacion;
    private String typehabitacion;
    private float precioxnoche;
    private String estado;

    public Habitaciones() {
    }

    public Habitaciones(int idhabitacion, String typehabitacion, float precioxnoche, String estado) {
        this.idhabitacion = idhabitacion;
        this.typehabitacion = typehabitacion;
        this.precioxnoche = precioxnoche;
        this.estado = estado;
    }

    public int getIdhabitacion() {
        return idhabitacion;
    }

    public void setIdhabitacion(int idhabitacion) {
        this.idhabitacion = idhabitacion;
    }

    public String getTypehabitacion() {
        return typehabitacion;
    }

    public void setTypehabitacion(String typehabitacion) {
        this.typehabitacion = typehabitacion;
    }

    public float getPrecioxnoche() {
        return precioxnoche;
    }

    public void setPrecioxnoche(float precioxnoche) {
        this.precioxnoche = precioxnoche;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return this.idhabitacion + " " + this.typehabitacion + " " + this.precioxnoche + " " + this.estado + " " ;
    }
}
