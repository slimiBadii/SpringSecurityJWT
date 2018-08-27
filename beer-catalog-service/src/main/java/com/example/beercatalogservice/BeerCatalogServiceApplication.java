package com.example.beercatalogservice;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableDiscoveryClient
@SpringBootApplication
public class BeerCatalogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeerCatalogServiceApplication.class, args);
	}
	
	

	
}

@Entity
class Beer {
	 @Id
	    @GeneratedValue
	    private Long id;

	private String name;
    public Beer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Beer(String name) {
        this.name = name;
    }

	@Override
	public String toString() {
		return "Beer [id=" + id + ", name=" + name + "]";
	}

    

    
	
	
}
@RepositoryRestResource
interface BeerRepository extends JpaRepository<Beer, Long> {}

@Component
class BeerInitializer implements CommandLineRunner {

    private final BeerRepository beerRepository;

    BeerInitializer(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Stream.of("Kentucky Brunch Brand Stout", "Good Morning", "Very Hazy", "King Julius",
                "Budweiser", "Coors Light", "PBR")
                .forEach(beer -> beerRepository.save(new Beer(beer)));

        beerRepository.findAll().forEach(System.out::println);
    }
}