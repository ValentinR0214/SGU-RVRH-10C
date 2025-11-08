package mx.edu.utez.Server.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CronJobs {

    private static final Logger logger = LoggerFactory.getLogger(CronJobs.class);

    // Método que se ejecuta cada minuto
    @Scheduled(cron = "0 * * * * ?")
    public void imprimirHola() {
        logger.info("Funcionando crob jobs");
    }
}
