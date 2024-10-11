package Persistencia;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Entity
public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta") // Nombre de la columna en la base de datos
    private Long idVenta; // Nombre de la variable para reflejar la columna

    @Temporal(TemporalType.TIMESTAMP) // Indica que se guarda con tiempo
    @Column(name = "fecha_venta", nullable = false)
    private Date fecha;

    @Column(name = "total", nullable = false, precision = 10, scale = 2) // Definido con precisión y escala
    private double total;

    // Relación con Producto
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "venta_id") // Campo en Producto que guarda la relación
    private List<Producto> productos; // Listado de productos relacionados a la venta

    // Constructor vacío
    public Venta() {
    }

    // Constructor solo con fecha
    public Venta(Date fecha) {
        this.fecha = fecha;
    }

    // Constructor con parámetros
    public Venta(Date fecha, double total, List<Producto> productos) {
        this.fecha = fecha;
        this.total = total;
        this.productos = productos;
    }

    // Getters y Setters
    public Long getIdVenta() { // Getter para idVenta
        return idVenta;
    }

    public void setIdVenta(Long idVenta) { // Setter para idVenta
        this.idVenta = idVenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVenta, fecha, total);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Venta other = (Venta) obj;
        return Objects.equals(idVenta, other.idVenta) // Compara idVenta
                && Objects.equals(fecha, other.fecha)
                && Double.compare(other.total, total) == 0;
    }

    @Override
    public String toString() {
        return "Venta{"
                + "idVenta=" + idVenta // Muestra idVenta
                + ", fecha=" + fecha
                + ", total=" + total
                + ", productos=" + productos // Mostrar productos relacionados
                + '}';
    }
}
