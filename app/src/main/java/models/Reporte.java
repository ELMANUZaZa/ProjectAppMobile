package models;

public class Reporte {
    private String nombre_interesado;
    private String direccion;
    private String colonia;
    private String celular;
    private String correo;
    private String tipo;
    private String descripcion;

    private String imagen_url;

    public String getNombreInteresado() {
        return nombre_interesado;
    }
    public void setNombreInteresado(String nombre_interesado) {
        this.nombre_interesado = nombre_interesado;
    }

    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getColonia() {
        return colonia;
    }
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCelular() {
        return celular;
    }
    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagenUrl() {
        return imagen_url;
    }
    public void setImagenUrl(String imagen_url) {
        this.imagen_url = imagen_url;
    }

}
