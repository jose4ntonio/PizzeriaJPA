package DAOs;

import Persistencia.Ingrediente;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class IngredienteDAO {
    private EntityManagerFactory emf;

    // Constructor que inicializa el EntityManagerFactory
    public IngredienteDAO() {
        emf = Persistence.createEntityManagerFactory("pu_pizzeria");
    }

    // Método para crear un nuevo ingrediente
    public void create(Ingrediente ingrediente) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(ingrediente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback(); // Deshacer transacción en caso de error
            throw e; // Relanzar la excepción
        } finally {
            em.close(); // Asegurarse de cerrar el EntityManager
        }
    }

    // Método para encontrar un ingrediente por su ID
    public Ingrediente find(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Ingrediente.class, id);
        } finally {
            em.close(); // Asegurarse de cerrar el EntityManager
        }
    }

    // Método para obtener todos los ingredientes
    public List<Ingrediente> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Ingrediente> query = em.createQuery("SELECT i FROM Ingrediente i", Ingrediente.class);
            return query.getResultList();
        } finally {
            em.close(); // Asegurarse de cerrar el EntityManager
        }
    }

    // Método para actualizar un ingrediente
    public void update(Ingrediente ingrediente) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(ingrediente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback(); // Deshacer transacción en caso de error
            throw e; // Relanzar la excepción
        } finally {
            em.close(); // Asegurarse de cerrar el EntityManager
        }
    }

    // Método para eliminar un ingrediente por su ID
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Ingrediente ingrediente = em.find(Ingrediente.class, id);
            if (ingrediente != null) {
                em.getTransaction().begin();
                em.remove(ingrediente);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            em.getTransaction().rollback(); // Deshacer transacción en caso de error
            throw e; // Relanzar la excepción
        } finally {
            em.close(); // Asegurarse de cerrar el EntityManager
        }
    }

    // Método para buscar ingredientes cuyo nombre inicie o termine con la letra A
    public List<Ingrediente> findByNameStartingOrEndingWithA() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Ingrediente> query = em.createQuery("SELECT i FROM Ingrediente i WHERE i.nombre_ingrediente LIKE 'A%' OR i.nombre_ingrediente LIKE '%A'", Ingrediente.class);
            return query.getResultList(); // Se retorna la lista de ingredientes que cumplen la condición
        } finally {
            em.close(); // Asegurarse de cerrar el EntityManager
        }
    }
}
