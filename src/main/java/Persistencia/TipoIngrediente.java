package Persistencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tipos")
public class TipoIngrediente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_tipo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion", nullable = false)  // Aseguramos que la descripción no sea nula
    private String descripcion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipo") // Cambiado a CascadeType.ALL para persistir ingredientes automáticamente
    private List<Ingrediente> ingredientes = new ArrayList<>(); // Inicializamos la lista

    // Constructor sin parámetros
    public TipoIngrediente() {
    }

    // Constructor con un parámetro
    public TipoIngrediente(String descripcion) {
        this.descripcion = descripcion;
    }

    // Constructor con dos parámetros
    public TipoIngrediente(Long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    // Getters y Setters para los atributos de la clase
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
        // Actualizar el tipo en cada ingrediente si se establece la lista
        for (Ingrediente ingrediente : ingredientes) {
            ingrediente.setTipo(this);
        }
    }

    // Método para agregar un ingrediente y mantener la relación bidireccional
    public void addIngrediente(Ingrediente ingrediente) {
        ingredientes.add(ingrediente);
        ingrediente.setTipo(this); // Establecer el tipo en el ingrediente
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoIngrediente)) {
            return false;
        }
        TipoIngrediente other = (TipoIngrediente) object;
        // Comparación de ids para verificar igualdad
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TipoIngrediente{" + "id=" + id + ", descripcion=" + descripcion + '}';
    }
}
