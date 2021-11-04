package curso.java.tienda;

import java.net.URL;
import org.apache.log4j.PropertyConfigurator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TiendaMiguelPerezLeonApplication {

	public static void main(String[] args) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL url = loader.getResource("log4j.properties");
		PropertyConfigurator.configure(url);
		
		SpringApplication.run(TiendaMiguelPerezLeonApplication.class, args);
	}

}
