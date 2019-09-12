package org.dmace.store.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;

@Entity
public class Puntuacion {

    @Id
    @GeneratedValue
    private Long id;

    @CreatedDate
    private Date fecha;

    private int puntuacion;

    @ManyToOne
    private Producto producto;

    public Puntuacion() {
    }

    public Puntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Puntuacion(int puntuacion, Producto producto) {
        this.puntuacion = puntuacion;
        this.producto = producto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Puntuacion that = (Puntuacion) o;
        return puntuacion == that.puntuacion &&
                id.equals(that.id) &&
                Objects.equals(fecha, that.fecha) &&
                Objects.equals(producto, that.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fecha, puntuacion, producto);
    }
}