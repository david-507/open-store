package org.dmace.store.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

@Entity
public class Producto {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String nombre;

    private String descripcion;

    @Min(0)
    private double pvp;

    @Max(1)
    private double descuento = 0;

    @URL
    private String imagen;

    @NotNull
    @ManyToOne
    private Categoria categoria;

    @OneToMany(mappedBy="producto", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
    private Set<Puntuacion> puntuaciones = new HashSet<>();

    public Producto() {
    }

    public Producto(String nombre, String descripcion, float pvp, float descuento, String imagen, Categoria categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.pvp = pvp;
        this.descuento = descuento;
        this.imagen = imagen;
        this.categoria = categoria;
    }

    /* helper methods */
    public boolean isDiscounted() {
        return this.descuento > 0;
    }

    /** returns price according to current discount */
    public double finalPrice() {
        return (descuento==0) ? pvp : pvp * (1 - descuento);
    }

    public void addPuntuacion(Puntuacion puntuacion) {
        this.puntuaciones.add(puntuacion);
        puntuacion.setProducto(this);
    }


    public double getPuntuacionMedia() {
        if (this.puntuaciones.isEmpty())
            return 0;
        else
            return this.puntuaciones.stream()
                    .mapToInt(Puntuacion::getPuntuacion)
                    .average()
                    .getAsDouble();
    }

    public double getNumeroTotalPuntuaciones() {
        return this.puntuaciones.size();
    }

    /* end helper methods */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public @Min(0) double getPvp() {
        return pvp;
    }

    public void setPvp(@Min(0) double pvp) {
        this.pvp = pvp;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }


    public Categoria getCategoria() {
        return categoria;
    }


    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Set<Puntuacion> getPuntuaciones() {
        return puntuaciones;
    }

    public void setPuntuaciones(Set<Puntuacion> puntuaciones) {
        this.puntuaciones = puntuaciones;
    }

}