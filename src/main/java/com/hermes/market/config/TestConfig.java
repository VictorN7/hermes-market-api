package com.hermes.market.config;

import java.time.LocalDate;
import java.util.Arrays;

import com.hermes.market.domain.product.Brand;
import com.hermes.market.infrastructure.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.hermes.market.domain.order.DeliveryMethod;
import com.hermes.market.domain.order.Order;
import com.hermes.market.domain.order.PaymentMethod;
import com.hermes.market.domain.product.Category;
import com.hermes.market.domain.product.Product;
import com.hermes.market.domain.user.User;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private BrandRepository brandRepository;

	@Override
	public void run(String... args) throws Exception {

		User user1 = new User("Victor Santos", "victor@email.com", "123456", LocalDate.of(2000, 8, 07), "12345678901");
		User user2 = new User("Admin Hermes", "admin@hermes.com", "admin123", LocalDate.of(2001, 9, 25), "98765432100");

		Category hortifruti = new Category("Hortifruti");
		Category bebidas = new Category("Bebidas");
		Category limpeza = new Category("Limpeza");

		Brand ypeBrand = new Brand("Ypê");
		Brand obaHortifrutiBrand = new Brand("Oba Hortifruti");
		Brand fazendinhayayaBrand = new Brand("Fazendinha YAYA");
		Brand cocaColaBrand = new Brand("Coca-Cola");

		brandRepository.saveAll(Arrays.asList(ypeBrand, obaHortifrutiBrand, fazendinhayayaBrand, cocaColaBrand));
		categoryRepository.saveAll(Arrays.asList(hortifruti,bebidas,limpeza));

		Product banana = new Product("Banana Prata", "Banana fresca", 4.99, 100,
				"https://img.freepik.com/psd-gratuitas/close-up-de-uma-maca-deliciosa_23-2151868338.jpg?semt=ais_hybrid&w=740&q=80",
				hortifruti,  obaHortifrutiBrand);
		Product maca = new Product("Maçã Gala", "Maçã doce", 6.49, 80,
				"https://img.freepik.com/fotos-gratis/banana-unica-isolada-sobre-um-fundo-branco_839833-17794.jpg?semt=ais_hybrid&w=740&q=80",
				hortifruti, fazendinhayayaBrand);
		Product cocaCola = new Product("Coca-Cola 2L", "Refrigerante", 9.99, 50,
				"https://img.freepik.com/psd-premium/garrafa-de-refrigerante-de-vidro-com-refrigerante-escuro-beber-bebida-fria_632498-54245.jpg?semt=ais_hybrid&w=740&q=80",
				bebidas, cocaColaBrand);
		Product detergente = new Product("Detergente Ypê", "Detergente neutro", 2.99, 200,
				"https://img.freepik.com/psd-gratuitas/renderizacao-3d-de-produto-de-limpeza_23-2149929616.jpg?semt=ais_hybrid&w=740&q=80",
				limpeza, ypeBrand);

		userRepository.saveAll(Arrays.asList(user1, user2));
		productRepository.saveAll(Arrays.asList(banana, maca, cocaCola, detergente));

		Order order1 = new Order(user1, PaymentMethod.CREDIT_CARD, DeliveryMethod.HOME_DELIVERY);
		Order order2 = new Order(user2, PaymentMethod.BOLETO, DeliveryMethod.PICKUP);
		
		order1.addItem(banana, 2);
		order1.addItem(cocaCola, 2);
		
		order2.addItem(cocaCola, 10);
		order2.addItem(detergente, 2);
		
		orderRepository.saveAll(Arrays.asList(order1, order2));
	}
}