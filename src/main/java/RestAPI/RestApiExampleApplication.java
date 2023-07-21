package RestAPI;

import RestAPI.Model.Item;
import RestAPI.Persistence.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApiExampleApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RestApiExampleApplication.class, args);
	}

	@Autowired
	ItemRepository itemRepository;

	@Override
	public void run(String... args) throws Exception {
		itemRepository.save(new Item(null,"Tablet Samsung", "Tablet Samsung Galaxy Tab A A7 Lite with Book Cover SM-T220",2599));
		itemRepository.save(new Item(null,"Celular Samsung","Samsung Galaxy A14 4G (Exynos) Dual SIM 128 GB black 4 GB RAM",3699));
		itemRepository.save(new Item(null,"Pantalla Samsung","Smart TV Samsung Series 7 UN55AU7000FXZX LED Tizen 4K 55\" 100V - 127V",9787));
	}
}
