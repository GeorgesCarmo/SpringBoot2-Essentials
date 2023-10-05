package academy.devdojo.springboot2.essentials.requests;

import academy.devdojo.springboot2.essentials.domain.UserRole;

public record RegisterRequest(String username, String password, UserRole role) {

}
