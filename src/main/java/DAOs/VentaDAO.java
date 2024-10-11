package DAOs;

import Persistencia.Venta;
import javax.persistence.*;
import java.util.List;

public class VentaDAO {
    private EntityManagerFactory entityManagerFactory;

    public VentaDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    // Método para guardar una nueva venta
    public void guardar(Venta venta) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(venta);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback(); // Deshacer transacción en caso de error
            throw e; // Relanzar la excepción
        } finally {
            entityManager.close(); // Asegurarse de cerrar el EntityManager
        }
    }

    // Método para obtener una venta por su ID
    public Venta obtenerPorId(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Venta.class, id);
        } finally {
            entityManager.close(); // Asegurarse de cerrar el EntityManager
        }
    }

    // Método para obtener todas las ventas
    public List<Venta> obtenerTodas() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<Venta> query = entityManager.createQuery("SELECT v FROM Venta v", Venta.class);
            return query.getResultList();
        } finally {
            entityManager.close(); // Asegurarse de cerrar el EntityManager
        }
    }

    // Método para actualizar una venta
    public void actualizar(Venta venta) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(venta);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback(); // Deshacer transacción en caso de error
            throw e; // Relanzar la excepción
        } finally {
            entityManager.close(); // Asegurarse de cerrar el EntityManager
        }
    }

    // Método para eliminar una venta
    public void eliminar(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Venta venta = entityManager.find(Venta.class, id);
            if (venta != null) {
                entityManager.remove(venta);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback(); // Deshacer transacción en caso de error
            throw e; // Relanzar la excepción
        } finally {
            entityManager.close(); // Asegurarse de cerrar el EntityManager
        }
    }

    // Método para buscar ventas realizadas entre agosto y septiembre de 2024
    public List<Venta> obtenerVentasEntreFechas() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<Venta> query = entityManager.createQuery(
                "SELECT v FROM Venta v WHERE v.fecha_venta BETWEEN :inicio AND :fin", Venta.class
            );
            query.setParameter("inicio", "2024-08-01 00:00:00");
            query.setParameter("fin", "2024-09-30 23:59:59");
            return query.getResultList(); // Se retorna la lista de ventas que cumplen con la condición
        } finally {
            entityManager.close(); // Asegurarse de cerrar el EntityManager
        }
    }
}
