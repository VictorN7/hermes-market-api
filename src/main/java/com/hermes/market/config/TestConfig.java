package com.hermes.market.config;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.hermes.market.domain.product.Category;
import com.hermes.market.domain.product.Product;
import com.hermes.market.domain.user.User;
import com.hermes.market.infrastructure.repository.CategoryRepository;
import com.hermes.market.infrastructure.repository.ProductRepository;
import com.hermes.market.infrastructure.repository.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {

		User user1 = new User("Victor Santos", "victor@email.com", "123456", LocalDate.of(2000, 8, 07), "12345678901");
		User user2 = new User("Admin Hermes", "admin@hermes.com", "admin123", LocalDate.of(2001, 9, 25), "98765432100");

		Category hortifruti = new Category("Hortifruti");
		Category bebidas = new Category("Bebidas");
		Category limpeza = new Category("Limpeza");

		Product banana = new Product("Banana Prata", "Banana fresca", 4.99, 100);
		Product maca = new Product("Maçã Gala", "Maçã doce", 6.49, 80);
		Product cocaCola = new Product("Coca-Cola 2L", "Refrigerante", 9.99, 50);
		Product detergente = new Product("Detergente Ypê", "Detergente neutro", 2.99, 200);

		userRepository.saveAll(Arrays.asList(user1, user2));
		productRepository.saveAll(Arrays.asList(banana, maca, cocaCola, detergente));
		categoryRepository.saveAll(Arrays.asList(hortifruti,bebidas,limpeza));

	}
}