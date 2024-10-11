package DAOs;

import Persistencia.Producto;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProductoDAO {
    private EntityManagerFactory emf;

    // Constructor que inicializa el EntityManagerFactory
    public ProductoDAO() {
        emf = Persistence.createEntityManagerFactory("pu_pizzeria");
    }

    // Método para crear un nuevo producto
    public void create(Producto producto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(producto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback(); // Deshacer transacción en caso de error
            throw e; // Relanzar la excepción
        } finally {
            em.close(); // Asegurarse de cerrar el EntityManager
        }
    }

    // Método para encontrar un producto por su ID
    public Producto find(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close(); // Asegurarse de cerrar el EntityManager
        }
    }

    // Método para obtener todos los productos
    public List<Producto> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p", Producto.class);
            return query.getResultList();
        } finally {
            em.close(); // Asegurarse de cerrar el EntityManager
        }
    }

    // Método para actualizar un producto
    public void update(Producto producto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(producto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback(); // Deshacer transacción en caso de error
            throw e; // Relanzar la excepción
        } finally {
            em.close(); // Asegurarse de cerrar el EntityManager
        }
    }

    // Método para eliminar un producto por su ID
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Producto producto = em.find(Producto.class, id);
            if (producto != null) {
                em.getTransaction().begin();
                em.remove(producto);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            em.getTransaction().rollback(); // Deshacer transacción en caso de error
            throw e; // Relanzar la excepción
        } finally {
            em.close(); // Asegurarse de cerrar el EntityManager
        }
    }

    // Método para buscar productos cuyo precio sea mayor a 250
    public List<Producto> findByPriceGreaterThan250() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p WHERE p.precio > :precio", Producto.class);
            query.setParameter("precio", 250f); // Se establece el parámetro de precio
            return query.getResultList(); // Se retorna la lista de productos que cumplen la condición
        } finally {
            em.close(); // Asegurarse de cerrar el EntityManager
        }
    }
}
