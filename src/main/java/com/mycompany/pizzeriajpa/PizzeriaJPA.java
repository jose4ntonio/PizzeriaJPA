package com.mycompany.pizzeriajpa;

import Persistencia.Ingrediente;
import Persistencia.Producto;
import Persistencia.TipoIngrediente;
import Persistencia.Venta;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PizzeriaJPA {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_pizzeria");
        EntityManager em = emf.createEntityManager();

        try {
            // Comenzar la transacción para tipos de ingredientes
            em.getTransaction().begin();
            List<TipoIngrediente> tipos = new ArrayList<>();
            tipos.add(new TipoIngrediente("Refrigerados"));
            tipos.add(new TipoIngrediente("Secos"));
            tipos.add(new TipoIngrediente("Congelados"));
            tipos.add(new TipoIngrediente("Frescos"));
            tipos.add(new TipoIngrediente("Enlatados"));

            for (TipoIngrediente tipo : tipos) {
                em.persist(tipo);
            }
            em.getTransaction().commit();
            System.out.println("Tipos de ingredientes insertados.");

            // Crear y persistir Ingredientes
            em.getTransaction().begin();
            List<Ingrediente> ingredientes = new ArrayList<>();
            ingredientes.add(new Ingrediente("Salami", 10.0, tipos.get(0))); // Refrigerados
            ingredientes.add(new Ingrediente("Queso Mozzarella", 50.0, tipos.get(0))); // Refrigerados
            ingredientes.add(new Ingrediente("Tomate Seco", 15.0, tipos.get(1))); // Secos
            ingredientes.add(new Ingrediente("Harina", 20.0, tipos.get(1))); // Secos
            ingredientes.add(new Ingrediente("Pescado", 25.0, tipos.get(2))); // Congelados
            ingredientes.add(new Ingrediente("Albahaca Fresca", 5.0, tipos.get(3))); // Frescos
            ingredientes.add(new Ingrediente("Aceitunas", 8.0, tipos.get(4))); // Enlatados
            ingredientes.add(new Ingrediente("Champiñones", 12.0, tipos.get(0))); // Refrigerados
            ingredientes.add(new Ingrediente("Pimientos", 7.0, tipos.get(3))); // Frescos
            ingredientes.add(new Ingrediente("Cebolla", 6.0, tipos.get(3))); // Frescos
            ingredientes.add(new Ingrediente("Anchoas", 15.0, tipos.get(0))); // Refrigerados
            ingredientes.add(new Ingrediente("Albahaca", 5.0, tipos.get(3))); // Frescos

            for (Ingrediente ingrediente : ingredientes) {
                em.persist(ingrediente);
            }
            em.getTransaction().commit();
            System.out.println("Ingredientes insertados.");

            // Crear y persistir Productos
            em.getTransaction().begin();
            List<Producto> productos = new ArrayList<>();
            productos.add(new Producto(99.9f, "Pizza de Salami", "Pizza con salami y queso"));
            productos.add(new Producto(89.9f, "Pizza de Queso", "Pizza con queso mozzarella"));
            productos.add(new Producto(300.0f, "Pizza de Pescado", "Pizza con pescado y albahaca")); // Precio > 250
            productos.add(new Producto(110.0f, "Pizza Vegetariana", "Pizza con vegetales frescos"));
            productos.add(new Producto(250.0f, "Pizza de Champiñones", "Pizza con champiñones y cebolla")); // Precio > 250

            for (Producto producto : productos) {
                em.persist(producto);
            }
            em.getTransaction().commit();
            System.out.println("Productos insertados.");

            // Crear y persistir Ventas
            em.getTransaction().begin();
            List<Venta> ventas = new ArrayList<>();
            Calendar calendar = Calendar.getInstance();
            calendar.set(2024, Calendar.AUGUST, 15);
            ventas.add(new Venta(calendar.getTime()));
            calendar.set(2024, Calendar.AUGUST, 20);
            ventas.add(new Venta(calendar.getTime()));
            calendar.set(2024, Calendar.SEPTEMBER, 10);
            ventas.add(new Venta(calendar.getTime()));
            calendar.set(2024, Calendar.SEPTEMBER, 25);
            ventas.add(new Venta(calendar.getTime()));

            for (Venta venta : ventas) {
                em.persist(venta);
            }
            em.getTransaction().commit();
            System.out.println("Ventas insertadas.");

            // Obtener algunos productos para pruebas
            Producto pizza = em.find(Producto.class, 3L);
            Producto pizza2 = em.find(Producto.class, 4L);

            // Aquí puedes realizar operaciones con los productos obtenidos o cualquier otra lógica
            if (pizza != null) {
                System.out.println("Producto encontrado: " + pizza.getNombre());
            }
            if (pizza2 != null) {
                System.out.println("Producto encontrado: " + pizza2.getNombre());
            }

            // Consultas y mostrar resultados
            List<Producto> productosCaros = obtenerProductosCaros(em);
            System.out.println("Productos con precio mayor a 250:");
            for (Producto p : productosCaros) {
                System.out.println(p.getNombre() + " - Precio: " + p.getPrecio());
            }

            List<Ingrediente> ingredientesConA = obtenerIngredientesConA(em);
            System.out.println("Ingredientes cuyo nombre inicia o termina con 'A':");
            for (Ingrediente i : ingredientesConA) {
                System.out.println(i.getNombre());
            }

            List<Venta> ventasAgostoSeptiembre = obtenerVentasEntreFechas(em, "2024-08-01", "2024-09-30");
            System.out.println("Ventas realizadas entre agosto y septiembre de 2024:");
            for (Venta v : ventasAgostoSeptiembre) {
                System.out.println("Venta ID: " + v.getIdVenta() + " - Fecha: " + v.getFecha());
            }

        } catch (Exception e) {
            System.err.println("Error durante la transacción: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar el EntityManager y la fábrica
            em.close();
            emf.close();
        }
    }

    private static List<Producto> obtenerProductosCaros(EntityManager em) {
        TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p WHERE p.precio > 250", Producto.class);
        return query.getResultList();
    }

    private static List<Ingrediente> obtenerIngredientesConA(EntityManager em) {
        TypedQuery<Ingrediente> query = em.createQuery("SELECT i FROM Ingrediente i WHERE i.nombre LIKE 'A%' OR i.nombre LIKE '%A'", Ingrediente.class);
        return query.getResultList();
    }

    private static List<Venta> obtenerVentasEntreFechas(EntityManager em, String fechaInicio, String fechaFin) {
        TypedQuery<Venta> query = em.createQuery("SELECT v FROM Venta v WHERE v.fecha BETWEEN :inicio AND :fin", Venta.class);
        query.setParameter("inicio", java.sql.Date.valueOf(fechaInicio));
        query.setParameter("fin", java.sql.Date.valueOf(fechaFin));
        return query.getResultList();
    }
}
