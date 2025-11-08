package mx.edu.utez.Server.utils;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/*@Configuration
public class DBConnection {
    @Value("${db.host}")
    private String host;

    @Value("${db.port}")
    private String port;

    @Value("${db.name}")
    private String name;

    @Value("${db.user}")
    private String user;

    @Value("${db.password}")
    private String pass;

    @Bean
    public DataSource getDBConnection(){
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        source.setUrl("jdbc:mysql://" + host + ":" + port + "/" + name );
        source.setUsername(user);
        source.setPassword(pass);

        return source;
    }
}
*/