package academy.devdojo.springboot2.essentials.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import academy.devdojo.springboot2.essentials.domain.Anime;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class SpringClient {
	public static void main(String[] args) {
		ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/{id}", Anime.class,3);
		log.info(entity);
		
		Anime object = new RestTemplate().getForObject("http://localhost:8080/animes/3", Anime.class);
		log.info(object);
		
		ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange("http://localhost:8080/animes/all", 
				HttpMethod.GET, 
				null,
				new ParameterizedTypeReference<>() {});
		log.info(exchange.getBody());
		
//		Anime kingdom = Anime.builder().name("Kingdom").build();
//		Anime kingdomSaved = new RestTemplate().postForObject("http://localhost:8080/animes", kingdom, Anime.class);
//		log.info("Saved anime {}", kingdomSaved);
		
		Anime samuraiChamploo = Anime.builder().name("Samurai Champloo").build();
		ResponseEntity<Anime> samuraiChaplooSaved = new RestTemplate().exchange("http://localhost:8080/animes",
				HttpMethod.POST, 
				new HttpEntity<>(samuraiChamploo, createJsonHeader()),
				Anime.class);
		log.info("Saved anime {}", samuraiChaplooSaved);
		
		Anime animeToBeUpdated = samuraiChaplooSaved.getBody();
		animeToBeUpdated.setName("Samurai Champloo II");
		ResponseEntity<Void> samuraiChamplooUpdated = new RestTemplate().exchange("http://localhost:8080/animes",
				HttpMethod.PUT, 
				new HttpEntity<>(animeToBeUpdated, createJsonHeader()),
				Void.class);
		log.info(samuraiChamplooUpdated);
		
		ResponseEntity<Void> samuraiChamplooDeleted = new RestTemplate().exchange("http://localhost:8080/animes/{id}",
				HttpMethod.DELETE, 
				null,
				Void.class,
				animeToBeUpdated.getId());
		log.info(samuraiChamplooDeleted);
		
	}
	
	private static HttpHeaders createJsonHeader() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return httpHeaders;
		
	}
}
