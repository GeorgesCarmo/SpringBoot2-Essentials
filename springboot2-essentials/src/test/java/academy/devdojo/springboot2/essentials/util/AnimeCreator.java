package academy.devdojo.springboot2.essentials.util;

import academy.devdojo.springboot2.essentials.domain.Anime;

public class AnimeCreator {
	public static Anime createAnimeToBeSaved() {
		return Anime.builder().name("Hajime no Ippo").build();
	}
	
	public static Anime createValidAnime() {
		return Anime.builder().name("Hajime no Ippo").id(1L).build();
	}
	
	public static Anime createValidUpdatedAnime() {
		return Anime.builder().name("Hajime no Ippo II").id(1L).build();
	}

}
