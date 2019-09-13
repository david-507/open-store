package org.dmace.store;

import org.dmace.store.model.Producto;
import org.dmace.store.model.Puntuacion;
import org.dmace.store.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class MainApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }


    /**
     * Executed at startup time.
     * Creates random points for products
     *
     *
     * @param productRepository Repositorio de productos
     * @return Una instancia de CommandLineRunner, que será ejecutada al iniciar el proyecto
     */

    @Bean
    public CommandLineRunner initData(ProductRepository productRepository) {

        return args -> {

            // Rescatamos todos los productos
            List<Producto> productos = productRepository.findAll();

            Random r = new Random();

            // Para cada uno de ellos
            for (Producto p : productos) {
                // Vamos a añadirle un número aleatorio de puntuaciones, entre 1 y 10
                for (int i = 0; i < r.nextInt(10); i++)
                    // Lo valoramos con una puntuación aleatoria, entre 3 y 5.
                    p.addPuntuacion(new Puntuacion(3 + r.nextInt(2)));
            }

            // Actualizamos los productos, almacenando así su puntuación
            productRepository.saveAll(productos);

        };

    }
}
