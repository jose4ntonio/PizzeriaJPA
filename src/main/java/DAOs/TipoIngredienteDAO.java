package DAOs;

import Persistencia.TipoIngrediente;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class TipoIngredienteDAO {
    private EntityManagerFactory emf;

    public TipoIngredienteDAO() {
        emf = Persistence.createEntityManagerFactory("pu_pizzeria");
    }

    // Método para crear un nuevo tipo de ingrediente
    public void create(TipoIngrediente tipo) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(tipo);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback(); // Deshacer transacción en caso de error
            throw e; // Relanzar la excepción
        } finally {
            em.close(); // Asegurarse de cerrar el EntityManager
        }
    }

    // Método para encontrar un tipo de ingrediente por su ID
    public TipoIngrediente find(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(TipoIngrediente.class, id);
        } finally {
            em.close(); // Asegurarse de cerrar el EntityManager
        }
    }

    // Método para obtener todos los tipos de ingredientes
    public List<TipoIngrediente> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<TipoIngrediente> query = em.createQuery("SELECT t FROM TipoIngrediente t", TipoIngrediente.class);
            return query.getResultList();
        } finally {
            em.close(); // Asegurarse de cerrar el EntityManager
        }
    }

    // Método para actualizar un tipo de ingrediente
    public void update(TipoIngrediente tipo) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(tipo);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback(); // Deshacer transacción en caso de error
            throw e; // Relanzar la excepción
        } finally {
            em.close(); // Asegurarse de cerrar el EntityManager
        }
    }

    // Método para eliminar un tipo de ingrediente por su ID
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            TipoIngrediente tipo = em.find(TipoIngrediente.class, id);
            if (tipo != null) {
                em.getTransaction().begin();
                em.remove(tipo);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            em.getTransaction().rollback(); // Deshacer transacción en caso de error
            throw e; // Relanzar la excepción
        } finally {
            em.close(); // Asegurarse de cerrar el EntityManager
        }
    }
}
