package academy.devdojo.springboot2.essentials.util;

import academy.devdojo.springboot2.essentials.requests.AnimePostRequestBody;

public class AnimePostRequestBodyCreator {
	public static AnimePostRequestBody createAnimePostRequestBody() {
		return AnimePostRequestBody.builder()
				.name(AnimeCreator.createAnimeToBeSaved()
						.getName()).build();
	}
}
