package mx.edu.utez.Server.config;

import mx.edu.utez.Server.users.model.User;
import mx.edu.utez.Server.users.model.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitialDataConfig {

    private static final Logger logger = LoggerFactory.getLogger(InitialDataConfig.class);

    @Bean
    CommandLineRunner loadInitialData(UserRepository userRepository) {
        return args -> {

            if (userRepository.count() == 0) {
                logger.info(">>> No hay usuarios en la BD. Cargando datos iniciales...");

                User user1 = new User();
                user1.setNombre("Valentin");
                user1.setApellido("Roque");
                user1.setEmail("valentin@gmail.com");
                user1.setTelefono("7771234567");

                User user2 = new User();
                user2.setNombre("Asta");
                user2.setApellido("Staria");
                user2.setEmail("asta@clover.com");
                user2.setTelefono("7778901234");

                User user3 = new User();
                user3.setNombre("David");
                user3.setApellido("Martinez");
                user3.setEmail("david@cyberpunk.com");
                user3.setTelefono("7775554433");

                userRepository.save(user1);
                userRepository.save(user2);
                userRepository.save(user3);

                logger.info(">>> ¡Datos iniciales cargados!");

            } else {
                logger.info(">>> La base de datos ya tiene usuarios. No se cargaron datos iniciales.");
            }
        };
    }
}