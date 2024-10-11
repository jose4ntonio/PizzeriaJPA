package Persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ingrediente")
public class Ingrediente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_ingrediente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_ingrediente", nullable = false) // Aseguramos que el nombre no sea nulo
    private String nombre;

    @Column(name = "cantidad", nullable = false) // Aseguramos que la cantidad no sea nula
    private Double cantidad;

    @ManyToOne(cascade = CascadeType.ALL) // Cambiado a CascadeType.ALL para persistir el tipo automáticamente
    @JoinColumn(name = "id_tipo", nullable = false) // Relación con TipoIngrediente
    private TipoIngrediente tipo;

    @ManyToMany(mappedBy = "ingredientes")
    private List<Producto> productos;

    // Getters y Setters
    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    // Constructor sin parámetros
    public Ingrediente() {
    }

    // Constructor con parámetros
    public Ingrediente(String nombre, Double cantidad, TipoIngrediente tipo) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipo = tipo;
    }

    // Getters y Setters para los atributos de la clase
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

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public TipoIngrediente getTipo() {
        return tipo;
    }

    public void setTipo(TipoIngrediente tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Ingrediente)) {
            return false;
        }
        Ingrediente other = (Ingrediente) object;
        // Comparación de ids para verificar igualdad
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ingrediente{" + "id=" + id + ", nombre=" + nombre + ", cantidad=" + cantidad + '}';
    }
}
