
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioSantanderDevWeek {

	public String PORT = System.getenv("PORT");
	public String PGHOST = System.getenv("PGHOST");
	public String PGPORT = System.getenv("PGPORT");
	public String PGDATABASE = System.getenv("PGDATABASE");
	public String PGUSER = System.getenv("PGUSER");
	public String PGPASSWORD = System.getenv("PGPASSWORD");

	public static void main(String[] args) {
		SpringApplication.run(DesafioSantanderDevWeek.class, args);
	}

}
