package com.hermes.market.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import com.hermes.market.domain.product.Brand;
import com.hermes.market.infrastructure.repository.*;
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

	private final CategoryRepository categoryRepository;
	private final ProductRepository productRepository;
	private final UserRepository userRepository;
	private final OrderRepository orderRepository;
	private final BrandRepository brandRepository;


	public TestConfig(CategoryRepository categoryRepository, ProductRepository productRepository,
					  UserRepository userRepository, OrderRepository orderRepository, BrandRepository brandRepository) {

		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
		this.userRepository = userRepository;
		this.orderRepository = orderRepository;
		this.brandRepository = brandRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		// =========================
		// USERS
		// =========================

		User user1 = new User("Victor Santos", "victor@email.com", "123456", LocalDate.of(2000, 8, 7), "12345678901");
		User user2 = new User("Admin Hermes", "admin@hermes.com", "admin123", LocalDate.of(2001, 9, 25), "98765432100");
		User user3 = new User("Mariana Lima", "mariana@email.com", "123456", LocalDate.of(1998, 3, 15), "11122233344");
		User user4 = new User("Carlos Souza", "carlos@email.com", "123456", LocalDate.of(1995, 12, 2), "55566677788");
		User user5 = new User("Ana Oliveira", "ana@email.com", "123456", LocalDate.of(2002, 6, 18), "99988877766");

		userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));

		// =========================
		// CATEGORIES
		// =========================

		Category hortifruti = new Category("Hortifruti");
		Category bebidas = new Category("Bebidas");
		Category limpeza = new Category("Limpeza");

		categoryRepository.saveAll(Arrays.asList(hortifruti, bebidas, limpeza));

		// =========================
		// BRANDS
		// =========================

		Brand ypeBrand = new Brand("Ypê");
		Brand fazendinhaBrand = new Brand("Fazendinha YAYA");
		Brand colheitaBrand = new Brand("Colheita Feliz");
		Brand cocaColaBrand = new Brand("Coca-Cola");

		brandRepository.saveAll(Arrays.asList(ypeBrand, fazendinhaBrand, cocaColaBrand, colheitaBrand));

		// =========================
		// PRODUCTS (10)
		// =========================

		Product banana = new Product(
				"Banana Prata",
				"Banana fresca",
				BigDecimal.valueOf(4.99),
				100,
				"https://images.unsplash.com/photo-1574226516831-e1dff420e8f8",
				hortifruti,
				fazendinhaBrand
		);

		Product maca = new Product(
				"Maçã Gala",
				"Maçã doce",
				BigDecimal.valueOf(6.49),
				80,
				"https://images.unsplash.com/photo-1567306226416-28f0efdc88ce",
				hortifruti,
				colheitaBrand
		);

		Product laranja = new Product(
				"Laranja Pera",
				"Laranja fresca",
				BigDecimal.valueOf(5.99),
				120,
				"https://images.unsplash.com/photo-1580910051074-3eb694886505",
				hortifruti,
				colheitaBrand
		);

		Product alface = new Product(
				"Alface Crespa",
				"Alface fresca",
				BigDecimal.valueOf(3.49),
				60,
				"https://images.unsplash.com/photo-1582515073490-dc4b8c6bcb28",
				hortifruti,
				fazendinhaBrand
		);

		Product cocaCola = new Product(
				"Coca-Cola 2L",
				"Refrigerante",
				BigDecimal.valueOf(9.99),
				50,
				"https://images.unsplash.com/photo-1622484212850-eb596d769edc",
				bebidas,
				cocaColaBrand
		);

		Product suco = new Product(
				"Suco de Uva 1L",
				"Suco integral",
				BigDecimal.valueOf(7.99),
				40,
				"https://images.unsplash.com/photo-1600271886742-f049cd451bba",
				bebidas,
				cocaColaBrand
		);

		Product agua = new Product(
				"Água Mineral 1.5L",
				"Água sem gás",
				BigDecimal.valueOf(2.49),
				150,
				"https://images.unsplash.com/photo-1564419320461-6870880221ad",
				bebidas,
				cocaColaBrand
		);

		Product detergente = new Product(
				"Detergente Ypê",
				"Detergente neutro",
				BigDecimal.valueOf(2.99),
				200,
				"https://images.unsplash.com/photo-1581578731548-c64695cc6952",
				limpeza,
				ypeBrand
		);

		Product sabao = new Product(
				"Sabão em Pó Ypê",
				"Sabão 1kg",
				BigDecimal.valueOf(12.99),
				90,
				"https://images.unsplash.com/photo-1598032895397-b9472444bf93",
				limpeza,
				ypeBrand
		);

		Product desinfetante = new Product(
				"Desinfetante Ypê",
				"Lavanda 2L",
				BigDecimal.valueOf(6.99),
				110,
				"https://images.unsplash.com/photo-1583947581924-860bda6a26df",
				limpeza,
				ypeBrand
		);

		// Associação bidirecional
		hortifruti.addProduct(banana);
		hortifruti.addProduct(maca);
		hortifruti.addProduct(laranja);
		hortifruti.addProduct(alface);

		bebidas.addProduct(cocaCola);
		bebidas.addProduct(suco);
		bebidas.addProduct(agua);

		limpeza.addProduct(detergente);
		limpeza.addProduct(sabao);
		limpeza.addProduct(desinfetante);

		fazendinhaBrand.addProduct(banana);
		colheitaBrand.addProduct(maca);
		colheitaBrand.addProduct(laranja);
		fazendinhaBrand.addProduct(alface);

		cocaColaBrand.addProduct(cocaCola);
		cocaColaBrand.addProduct(suco);
		cocaColaBrand.addProduct(agua);

		ypeBrand.addProduct(detergente);
		ypeBrand.addProduct(sabao);
		ypeBrand.addProduct(desinfetante);

		productRepository.saveAll(Arrays.asList(
				banana, maca, laranja, alface,
				cocaCola, suco, agua,
				detergente, sabao, desinfetante
		));

		// =========================
		// ORDERS (5)
		// =========================

		Order order1 = new Order(user1, PaymentMethod.CREDIT_CARD, DeliveryMethod.HOME_DELIVERY);
		Order order2 = new Order(user1, PaymentMethod.BOLETO, DeliveryMethod.PICKUP);
		Order order3 = new Order(user3, PaymentMethod.PIX, DeliveryMethod.HOME_DELIVERY);
		Order order4 = new Order(user4, PaymentMethod.CREDIT_CARD, DeliveryMethod.PICKUP);
		Order order5 = new Order(user4, PaymentMethod.PIX, DeliveryMethod.HOME_DELIVERY);

		order1.addItem(banana, 2);
		order1.addItem(cocaCola, 2);

		order2.addItem(cocaCola, 10);
		order2.addItem(detergente, 2);

		order3.addItem(laranja, 5);
		order3.addItem(suco, 3);

		order4.addItem(sabao, 1);
		order4.addItem(alface, 4);

		order5.addItem(agua, 6);
		order5.addItem(desinfetante, 2);

		orderRepository.saveAll(Arrays.asList(order1, order2, order3, order4, order5));
	}
}