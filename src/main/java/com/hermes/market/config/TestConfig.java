package com.hermes.market.config;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;

import com.hermes.market.domain.product.*;
import com.hermes.market.domain.user.Address;
import com.hermes.market.infrastructure.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.hermes.market.domain.order.DeliveryMethod;
import com.hermes.market.domain.order.Order;
import com.hermes.market.domain.order.PaymentMethod;
import com.hermes.market.domain.user.User;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	private final CategoryRepository categoryRepository;
	private final ProductRepository productRepository;
	private final UserRepository userRepository;
	private final OrderRepository orderRepository;
	private final BrandRepository brandRepository;
	private final PromotionRepository promotionRepository;
	private final AddressRepository addressRepository;

	public TestConfig(CategoryRepository categoryRepository, ProductRepository productRepository,
					  UserRepository userRepository, OrderRepository orderRepository,
					  BrandRepository brandRepository, PromotionRepository promotionRepository, AddressRepository addressRepository) {

		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
		this.userRepository = userRepository;
		this.orderRepository = orderRepository;
		this.brandRepository = brandRepository;
		this.promotionRepository = promotionRepository;
		this.addressRepository = addressRepository;
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
		User user6 = new User("Felipe Rocha", "felipe@email.com", "123456", LocalDate.of(1993, 4, 10), "33344455566");
		User user7 = new User("Juliana Costa", "juliana@email.com", "123456", LocalDate.of(1997, 7, 22), "77788899900");
		User user8 = new User("Roberto Alves", "roberto@email.com", "123456", LocalDate.of(1990, 11, 5), "44455566677");
		User user9 = new User("Camila Ferreira", "camila@email.com", "123456", LocalDate.of(2001, 2, 14), "22233344455");
		User user10 = new User("Lucas Mendes", "lucas@email.com", "123456", LocalDate.of(1999, 9, 30), "66677788899");

		userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10));

		// =========================
		// CATEGORIES
		// =========================

		Category hortifruti = new Category("Hortifruti");
		Category bebidas = new Category("Bebidas");
		Category limpeza = new Category("Limpeza");
		Category graos = new Category("Grãos e Cereais");
		Category laticinios = new Category("Laticínios");
		Category carnes = new Category("Carnes e Aves");
		Category padaria = new Category("Padaria e Confeitaria");
		Category higiene = new Category("Higiene Pessoal");
		Category frios = new Category("Frios e Embutidos");
		Category congelados = new Category("Congelados");

		categoryRepository.saveAll(Arrays.asList(
				hortifruti, bebidas, limpeza, graos, laticinios,
				carnes, padaria, higiene, frios, congelados
		));

		// =========================
		// BRANDS
		// =========================

		Brand ypeBrand = new Brand("Ypê");
		Brand fazendinhaBrand = new Brand("Fazendinha YAYA");
		Brand colheitaBrand = new Brand("Colheita Feliz");
		Brand cocaColaBrand = new Brand("Coca-Cola");
		Brand nestleBrand = new Brand("Nestlé");
		Brand quakerBrand = new Brand("Quaker");
		Brand sadiaBrand = new Brand("Sadia");
		Brand searaBrand = new Brand("Seara");
		Brand perdigaoBrand = new Brand("Perdigão");
		Brand vigorBrand = new Brand("Vigor");
		Brand itambeBrand = new Brand("Itambé");
		Brand pullmanBrand = new Brand("Pullman");
		Brand gilletteBrand = new Brand("Gillette");
		Brand xeretaBrand = new Brand("Xereta");

		brandRepository.saveAll(Arrays.asList(
				ypeBrand, fazendinhaBrand, colheitaBrand, cocaColaBrand,
				nestleBrand, quakerBrand, sadiaBrand, searaBrand, perdigaoBrand,
				vigorBrand, itambeBrand, pullmanBrand, gilletteBrand, xeretaBrand
		));

		// =========================
		// PRODUCTS — HORTIFRUTI
		// =========================

		Product banana = new Product("Banana Prata", "Banana fresca", BigDecimal.valueOf(4.99), 100, "https://images.unsplash.com/photo-1574226516831-e1dff420e8f8", hortifruti, fazendinhaBrand);
		Product maca = new Product("Maçã Gala", "Maçã doce e crocante", BigDecimal.valueOf(6.49), 80, "https://images.unsplash.com/photo-1567306226416-28f0efdc88ce", hortifruti, colheitaBrand);
		Product laranja = new Product("Laranja Pera", "Laranja fresca e suculenta", BigDecimal.valueOf(5.99), 120, "https://images.unsplash.com/photo-1580910051074-3eb694886505", hortifruti, colheitaBrand);
		Product alface = new Product("Alface Crespa", "Alface fresca", BigDecimal.valueOf(3.49), 60, "https://images.unsplash.com/photo-1582515073490-dc4b8c6bcb28", hortifruti, fazendinhaBrand);
		Product tomate = new Product("Tomate Italiano", "Tomate firme e vermelho", BigDecimal.valueOf(5.49), 90, "https://images.unsplash.com/photo-1546094096-0df4bcaaa337", hortifruti, colheitaBrand);
		Product cenoura = new Product("Cenoura", "Cenoura fresca 1kg", BigDecimal.valueOf(3.99), 70, "https://images.unsplash.com/photo-1598170845058-32b9d6a5da37", hortifruti, fazendinhaBrand);
		Product batata = new Product("Batata Inglesa", "Batata 1kg", BigDecimal.valueOf(4.49), 110, "https://images.unsplash.com/photo-1518977676601-b53f82aba655", hortifruti, colheitaBrand);
		Product cebola = new Product("Cebola Branca", "Cebola 1kg", BigDecimal.valueOf(3.79), 100, "https://images.unsplash.com/photo-1587735243615-c03f25aaff15", hortifruti, fazendinhaBrand);
		Product alho = new Product("Alho Nacional", "Alho 250g", BigDecimal.valueOf(4.99), 80, "https://images.unsplash.com/photo-1540148426945-6cf22a6b2383", hortifruti, colheitaBrand);
		Product limao = new Product("Limão Taiti", "Limão fresco", BigDecimal.valueOf(2.99), 150, "https://images.unsplash.com/photo-1582087677879-6c69b11fe18c", hortifruti, fazendinhaBrand);

		hortifruti.addProduct(banana); hortifruti.addProduct(maca); hortifruti.addProduct(laranja);
		hortifruti.addProduct(alface); hortifruti.addProduct(tomate); hortifruti.addProduct(cenoura);
		hortifruti.addProduct(batata); hortifruti.addProduct(cebola); hortifruti.addProduct(alho);
		hortifruti.addProduct(limao);

		fazendinhaBrand.addProduct(banana); fazendinhaBrand.addProduct(alface);
		fazendinhaBrand.addProduct(cenoura); fazendinhaBrand.addProduct(cebola); fazendinhaBrand.addProduct(limao);
		colheitaBrand.addProduct(maca); colheitaBrand.addProduct(laranja); colheitaBrand.addProduct(tomate);
		colheitaBrand.addProduct(batata); colheitaBrand.addProduct(alho);

		// =========================
		// PRODUCTS — BEBIDAS
		// =========================

		Product cocaCola = new Product("Coca-Cola 2L", "Refrigerante gelado", BigDecimal.valueOf(9.99), 50, "https://images.unsplash.com/photo-1622484212850-eb596d769edc", bebidas, cocaColaBrand);
		Product suco = new Product("Suco de Uva 1L", "Suco integral", BigDecimal.valueOf(7.99), 40, "https://images.unsplash.com/photo-1600271886742-f049cd451bba", bebidas, cocaColaBrand);
		Product agua = new Product("Água Mineral 1.5L", "Água sem gás", BigDecimal.valueOf(2.49), 150, "https://images.unsplash.com/photo-1564419320461-6870880221ad", bebidas, cocaColaBrand);
		Product aguaCom = new Product("Água com Gás 1L", "Água gaseificada", BigDecimal.valueOf(3.49), 100, "https://images.unsplash.com/photo-1523362628745-0c100150b504", bebidas, cocaColaBrand);
		Product sprite = new Product("Sprite 2L", "Refrigerante limão", BigDecimal.valueOf(8.99), 60, "https://images.unsplash.com/photo-1625772299848-391b6a87d7b3", bebidas, cocaColaBrand);
		Product nesquik = new Product("Achocolatado Nesquik 1L", "Achocolatado pronto", BigDecimal.valueOf(6.99), 70, "https://images.unsplash.com/photo-1542990253-0d0f5be5f0ed", bebidas, nestleBrand);
		Product sucoLaranja = new Product("Suco de Laranja 1L", "Suco integral", BigDecimal.valueOf(7.49), 55, "https://images.unsplash.com/photo-1600271886742-f049cd451bba", bebidas, nestleBrand);
		Product isotonico = new Product("Isotônico Limão 500ml", "Repositor energético", BigDecimal.valueOf(4.99), 80, "https://images.unsplash.com/photo-1553361371-9b22f78e8b1d", bebidas, cocaColaBrand);
		Product chaPronto = new Product("Chá Pronto Pêssego 1L", "Chá gelado", BigDecimal.valueOf(5.49), 65, "https://images.unsplash.com/photo-1556679343-c7306c1976bc", bebidas, nestleBrand);
		Product energetico = new Product("Energético 250ml", "Bebida energética", BigDecimal.valueOf(9.49), 45, "https://images.unsplash.com/photo-1621939514649-280e2ee25f60", bebidas, cocaColaBrand);

		bebidas.addProduct(cocaCola); bebidas.addProduct(suco); bebidas.addProduct(agua);
		bebidas.addProduct(aguaCom); bebidas.addProduct(sprite); bebidas.addProduct(nesquik);
		bebidas.addProduct(sucoLaranja); bebidas.addProduct(isotonico); bebidas.addProduct(chaPronto);
		bebidas.addProduct(energetico);

		cocaColaBrand.addProduct(cocaCola); cocaColaBrand.addProduct(suco); cocaColaBrand.addProduct(agua);
		cocaColaBrand.addProduct(aguaCom); cocaColaBrand.addProduct(sprite);
		cocaColaBrand.addProduct(isotonico); cocaColaBrand.addProduct(energetico);
		nestleBrand.addProduct(nesquik); nestleBrand.addProduct(sucoLaranja); nestleBrand.addProduct(chaPronto);

		// =========================
		// PRODUCTS — LIMPEZA
		// =========================

		Product detergente = new Product("Detergente Neutro 500ml", "Detergente neutro", BigDecimal.valueOf(2.99), 200, "https://images.unsplash.com/photo-1581578731548-c64695cc6952", limpeza, ypeBrand);
		Product sabao = new Product("Sabão em Pó 1kg", "Sabão em pó", BigDecimal.valueOf(12.99), 90, "https://images.unsplash.com/photo-1598032895397-b9472444bf93", limpeza, ypeBrand);
		Product desinfetante = new Product("Desinfetante Lavanda 2L", "Desinfetante perfumado", BigDecimal.valueOf(6.99), 110, "https://images.unsplash.com/photo-1583947581924-860bda6a26df", limpeza, ypeBrand);
		Product amaciante = new Product("Amaciante Floral 2L", "Amaciante floral", BigDecimal.valueOf(9.99), 80, "https://images.unsplash.com/photo-1607619056574-7b8d3ee536b2", limpeza, ypeBrand);
		Product limpMulti = new Product("Limpador Multiuso 500ml", "Limpeza geral", BigDecimal.valueOf(4.49), 120, "https://images.unsplash.com/photo-1585771724684-38269d6639fd", limpeza, ypeBrand);
		Product esponja = new Product("Esponja Dupla Face 3un", "Pack com 3 unidades", BigDecimal.valueOf(3.99), 150, "https://images.unsplash.com/photo-1563453392212-326f5e854473", limpeza, xeretaBrand);
		Product luva = new Product("Luva de Borracha M", "Par de luvas domésticas", BigDecimal.valueOf(5.99), 100, "https://images.unsplash.com/photo-1603484477859-abe6a73f9366", limpeza, xeretaBrand);
		Product vassoura = new Product("Vassoura Cerdas Macias", "Vassoura doméstica", BigDecimal.valueOf(14.99), 60, "https://images.unsplash.com/photo-1558618666-fcd25c85cd64", limpeza, xeretaBrand);
		Product rodo = new Product("Rodo 60cm", "Com cabo de madeira", BigDecimal.valueOf(12.99), 55, "https://images.unsplash.com/photo-1558618666-fcd25c85cd64", limpeza, xeretaBrand);
		Product sacoLixo = new Product("Saco de Lixo 100L c/10", "Reforçado", BigDecimal.valueOf(7.49), 200, "https://images.unsplash.com/photo-1605600659908-0ef719419d41", limpeza, xeretaBrand);

		limpeza.addProduct(detergente); limpeza.addProduct(sabao); limpeza.addProduct(desinfetante);
		limpeza.addProduct(amaciante); limpeza.addProduct(limpMulti); limpeza.addProduct(esponja);
		limpeza.addProduct(luva); limpeza.addProduct(vassoura); limpeza.addProduct(rodo);
		limpeza.addProduct(sacoLixo);

		ypeBrand.addProduct(detergente); ypeBrand.addProduct(sabao); ypeBrand.addProduct(desinfetante);
		ypeBrand.addProduct(amaciante); ypeBrand.addProduct(limpMulti);
		xeretaBrand.addProduct(esponja); xeretaBrand.addProduct(luva); xeretaBrand.addProduct(vassoura);
		xeretaBrand.addProduct(rodo); xeretaBrand.addProduct(sacoLixo);

		// =========================
		// PRODUCTS — GRÃOS E CEREAIS
		// =========================

		Product arroz = new Product("Arroz Branco 5kg", "Tipo 1 agulhinha", BigDecimal.valueOf(22.99), 100, "https://images.unsplash.com/photo-1586201375761-83865001e31c", graos, colheitaBrand);
		Product feijao = new Product("Feijão Carioca 1kg", "Feijão tipo 1", BigDecimal.valueOf(8.99), 120, "https://images.unsplash.com/photo-1619566636858-adf3ef46400b", graos, colheitaBrand);
		Product feijaoPreto = new Product("Feijão Preto 1kg", "Feijão tipo 1", BigDecimal.valueOf(9.49), 100, "https://images.unsplash.com/photo-1619566636858-adf3ef46400b", graos, colheitaBrand);
		Product aveia = new Product("Aveia em Flocos 500g", "Flocos finos", BigDecimal.valueOf(6.99), 80, "https://images.unsplash.com/photo-1614961233913-a5113a4a34ed", graos, quakerBrand);
		Product granola = new Product("Granola Tradicional 500g", "Com frutas secas", BigDecimal.valueOf(12.99), 60, "https://images.unsplash.com/photo-1517093728432-a0440f8d45af", graos, quakerBrand);
		Product milho = new Product("Milho para Pipoca 500g", "Grão selecionado", BigDecimal.valueOf(4.99), 90, "https://images.unsplash.com/photo-1578849278619-e73505e9610f", graos, colheitaBrand);
		Product lentilha = new Product("Lentilha 500g", "Lentilha tipo 1", BigDecimal.valueOf(7.49), 70, "https://images.unsplash.com/photo-1585664811087-47f65abbad64", graos, colheitaBrand);
		Product grao = new Product("Grão-de-Bico 500g", "Grão selecionado", BigDecimal.valueOf(8.49), 65, "https://images.unsplash.com/photo-1515543904379-3d757afe72e4", graos, colheitaBrand);
		Product farinha = new Product("Farinha de Trigo 1kg", "Tipo 1 especial", BigDecimal.valueOf(5.99), 110, "https://images.unsplash.com/photo-1574323347407-f5e1ad6d020b", graos, nestleBrand);
		Product macarrao = new Product("Macarrão Espaguete 500g", "Grano duro", BigDecimal.valueOf(4.49), 130, "https://images.unsplash.com/photo-1551462147-ff29053bfc14", graos, nestleBrand);

		graos.addProduct(arroz); graos.addProduct(feijao); graos.addProduct(feijaoPreto);
		graos.addProduct(aveia); graos.addProduct(granola); graos.addProduct(milho);
		graos.addProduct(lentilha); graos.addProduct(grao); graos.addProduct(farinha);
		graos.addProduct(macarrao);

		colheitaBrand.addProduct(arroz); colheitaBrand.addProduct(feijao); colheitaBrand.addProduct(feijaoPreto);
		colheitaBrand.addProduct(milho); colheitaBrand.addProduct(lentilha); colheitaBrand.addProduct(grao);
		quakerBrand.addProduct(aveia); quakerBrand.addProduct(granola);
		nestleBrand.addProduct(farinha); nestleBrand.addProduct(macarrao);

		// =========================
		// PRODUCTS — LATICÍNIOS
		// =========================

		Product leite = new Product("Leite Integral 1L", "Longa vida", BigDecimal.valueOf(4.99), 150, "https://images.unsplash.com/photo-1563636619-e9143da7973b", laticinios, itambeBrand);
		Product leiteDesn = new Product("Leite Desnatado 1L", "Zero gordura", BigDecimal.valueOf(5.29), 120, "https://images.unsplash.com/photo-1563636619-e9143da7973b", laticinios, vigorBrand);
		Product iogurte = new Product("Iogurte Natural 170g", "Integral", BigDecimal.valueOf(3.49), 100, "https://images.unsplash.com/photo-1488477181946-6428a0291777", laticinios, nestleBrand);
		Product iogurteFruta = new Product("Iogurte Morango 170g", "Com pedaços de fruta", BigDecimal.valueOf(3.99), 90, "https://images.unsplash.com/photo-1488477181946-6428a0291777", laticinios, nestleBrand);
		Product queijo = new Product("Queijo Mussarela 400g", "Fatiado", BigDecimal.valueOf(14.99), 70, "https://images.unsplash.com/photo-1486297678162-eb2a19b0a318", laticinios, vigorBrand);
		Product queijoMinas = new Product("Queijo Minas Frescal 400g", "Fresco", BigDecimal.valueOf(12.99), 65, "https://images.unsplash.com/photo-1452195100486-9cc805987862", laticinios, itambeBrand);
		Product manteiga = new Product("Manteiga com Sal 200g", "Cremosa", BigDecimal.valueOf(8.99), 80, "https://images.unsplash.com/photo-1589985270826-4b7bb135bc9d", laticinios, itambeBrand);
		Product creme = new Product("Creme de Leite 200g", "Tradicional", BigDecimal.valueOf(3.49), 110, "https://images.unsplash.com/photo-1615485290382-441e4d049cb5", laticinios, nestleBrand);
		Product requeijao = new Product("Requeijão Cremoso 200g", "Cremoso", BigDecimal.valueOf(7.49), 90, "https://images.unsplash.com/photo-1603833665858-e61d17a86224", laticinios, vigorBrand);
		Product nata = new Product("Nata Fresca 200g", "Nata fresca", BigDecimal.valueOf(6.99), 60, "https://images.unsplash.com/photo-1615485290382-441e4d049cb5", laticinios, itambeBrand);

		laticinios.addProduct(leite); laticinios.addProduct(leiteDesn); laticinios.addProduct(iogurte);
		laticinios.addProduct(iogurteFruta); laticinios.addProduct(queijo); laticinios.addProduct(queijoMinas);
		laticinios.addProduct(manteiga); laticinios.addProduct(creme); laticinios.addProduct(requeijao);
		laticinios.addProduct(nata);

		itambeBrand.addProduct(leite); itambeBrand.addProduct(queijoMinas);
		itambeBrand.addProduct(manteiga); itambeBrand.addProduct(nata);
		vigorBrand.addProduct(leiteDesn); vigorBrand.addProduct(queijo); vigorBrand.addProduct(requeijao);
		nestleBrand.addProduct(iogurte); nestleBrand.addProduct(iogurteFruta); nestleBrand.addProduct(creme);

		// =========================
		// PRODUCTS — CARNES E AVES
		// =========================

		Product frango = new Product("Frango Inteiro 1kg", "Resfriado", BigDecimal.valueOf(14.99), 60, "https://images.unsplash.com/photo-1587593810167-a84920ea0781", carnes, sadiaBrand);
		Product fileFrango = new Product("Filé de Frango 1kg", "Sem osso", BigDecimal.valueOf(18.99), 55, "https://images.unsplash.com/photo-1604503468506-a8da13d82791", carnes, searaBrand);
		Product coxinha = new Product("Coxinha da Asa 1kg", "Temperada", BigDecimal.valueOf(16.99), 50, "https://images.unsplash.com/photo-1527477396000-e27163b481c2", carnes, perdigaoBrand);
		Product patinho = new Product("Patinho Moído 500g", "Bovino fresco", BigDecimal.valueOf(19.99), 45, "https://images.unsplash.com/photo-1546833998-877b37c2e5c6", carnes, sadiaBrand);
		Product alcatra = new Product("Alcatra 500g", "Corte bovino", BigDecimal.valueOf(34.99), 30, "https://images.unsplash.com/photo-1546833998-877b37c2e5c6", carnes, searaBrand);
		Product linguicaToscana = new Product("Linguiça Toscana 500g", "Suína fresca", BigDecimal.valueOf(13.99), 55, "https://images.unsplash.com/photo-1558030137-a56c1bette0", carnes, perdigaoBrand);
		Product bacon = new Product("Bacon Fatiado 200g", "Defumado", BigDecimal.valueOf(9.99), 70, "https://images.unsplash.com/photo-1528607929212-2636ec44253e", carnes, sadiaBrand);
		Product costelaSuina = new Product("Costela Suína 1kg", "Fresca", BigDecimal.valueOf(22.99), 35, "https://images.unsplash.com/photo-1544025162-d76694265947", carnes, searaBrand);
		Product peixeTilapia = new Product("Filé de Tilápia 500g", "Congelado", BigDecimal.valueOf(21.99), 40, "https://images.unsplash.com/photo-1534482421-64566f976cfa", carnes, sadiaBrand);
		Product camarao = new Product("Camarão Limpo 300g", "Congelado", BigDecimal.valueOf(29.99), 25, "https://images.unsplash.com/photo-1565680018434-b513d5e5fd47", carnes, perdigaoBrand);

		carnes.addProduct(frango); carnes.addProduct(fileFrango); carnes.addProduct(coxinha);
		carnes.addProduct(patinho); carnes.addProduct(alcatra); carnes.addProduct(linguicaToscana);
		carnes.addProduct(bacon); carnes.addProduct(costelaSuina); carnes.addProduct(peixeTilapia);
		carnes.addProduct(camarao);

		sadiaBrand.addProduct(frango); sadiaBrand.addProduct(patinho); sadiaBrand.addProduct(bacon);
		sadiaBrand.addProduct(peixeTilapia);
		searaBrand.addProduct(fileFrango); searaBrand.addProduct(alcatra); searaBrand.addProduct(costelaSuina);
		perdigaoBrand.addProduct(coxinha); perdigaoBrand.addProduct(linguicaToscana); perdigaoBrand.addProduct(camarao);

		// =========================
		// PRODUCTS — PADARIA E CONFEITARIA
		// =========================

		Product paoDeFarma = new Product("Pão de Forma 500g", "Macio e fofinho", BigDecimal.valueOf(6.99), 80, "https://images.unsplash.com/photo-1509440159596-0249088772ff", padaria, pullmanBrand);
		Product paoIntegral = new Product("Pão Integral 500g", "Com grãos", BigDecimal.valueOf(8.49), 70, "https://images.unsplash.com/photo-1509440159596-0249088772ff", padaria, pullmanBrand);
		Product bisnaguinha = new Product("Bisnaguinha 200g", "Macia", BigDecimal.valueOf(4.99), 90, "https://images.unsplash.com/photo-1555507036-ab1f4038808a", padaria, pullmanBrand);
		Product bolo = new Product("Bolo de Chocolate 400g", "Fatiado", BigDecimal.valueOf(11.99), 50, "https://images.unsplash.com/photo-1578985545062-69928b1d9587", padaria, nestleBrand);
		Product boloLaranja = new Product("Bolo de Laranja 400g", "Fatiado", BigDecimal.valueOf(10.99), 50, "https://images.unsplash.com/photo-1565958011703-44f9829ba187", padaria, nestleBrand);
		Product cookie = new Product("Cookie Chocolate 100g", "Crocante", BigDecimal.valueOf(5.99), 100, "https://images.unsplash.com/photo-1558961363-fa8fdf82db35", padaria, nestleBrand);
		Product biscoitoSalgado = new Product("Biscoito Cream Cracker 200g", "Salgado crocante", BigDecimal.valueOf(3.99), 120, "https://images.unsplash.com/photo-1558961363-fa8fdf82db35", padaria, nestleBrand);
		Product wafer = new Product("Wafer Baunilha 160g", "Crocante", BigDecimal.valueOf(3.49), 110, "https://images.unsplash.com/photo-1558961363-fa8fdf82db35", padaria, nestleBrand);
		Product croissant = new Product("Croissant Manteiga 4un", "Folhado", BigDecimal.valueOf(9.99), 40, "https://images.unsplash.com/photo-1555507036-ab1f4038808a", padaria, pullmanBrand);
		Product tortinha = new Product("Tortinha de Limão 2un", "Recheada", BigDecimal.valueOf(7.99), 45, "https://images.unsplash.com/photo-1565958011703-44f9829ba187", padaria, nestleBrand);

		padaria.addProduct(paoDeFarma); padaria.addProduct(paoIntegral); padaria.addProduct(bisnaguinha);
		padaria.addProduct(bolo); padaria.addProduct(boloLaranja); padaria.addProduct(cookie);
		padaria.addProduct(biscoitoSalgado); padaria.addProduct(wafer); padaria.addProduct(croissant);
		padaria.addProduct(tortinha);

		pullmanBrand.addProduct(paoDeFarma); pullmanBrand.addProduct(paoIntegral);
		pullmanBrand.addProduct(bisnaguinha); pullmanBrand.addProduct(croissant);
		nestleBrand.addProduct(bolo); nestleBrand.addProduct(boloLaranja); nestleBrand.addProduct(cookie);
		nestleBrand.addProduct(biscoitoSalgado); nestleBrand.addProduct(wafer); nestleBrand.addProduct(tortinha);

		// =========================
		// PRODUCTS — HIGIENE PESSOAL
		// =========================

		Product shampoo = new Product("Shampoo Anticaspa 400ml", "Controle de oleosidade", BigDecimal.valueOf(14.99), 80, "https://images.unsplash.com/photo-1585751119414-ef2636f8aede", higiene, gilletteBrand);
		Product condicionador = new Product("Condicionador 400ml", "Hidratação profunda", BigDecimal.valueOf(13.99), 75, "https://images.unsplash.com/photo-1585751119414-ef2636f8aede", higiene, gilletteBrand);
		Product sabonete = new Product("Sabonete Antibacterial 90g", "Elimina germes", BigDecimal.valueOf(2.99), 200, "https://images.unsplash.com/photo-1556228578-dd539282b964", higiene, gilletteBrand);
		Product pastaDente = new Product("Pasta de Dente 90g", "Clareadora", BigDecimal.valueOf(4.99), 150, "https://images.unsplash.com/photo-1559591937-abc1dc4a38f4", higiene, gilletteBrand);
		Product desodorante = new Product("Desodorante Roll-on 50ml", "48h proteção", BigDecimal.valueOf(8.99), 100, "https://images.unsplash.com/photo-1625772299848-391b6a87d7b3", higiene, gilletteBrand);
		Product escovaDente = new Product("Escova de Dente Macia", "Cerdas macias", BigDecimal.valueOf(4.49), 120, "https://images.unsplash.com/photo-1559591937-abc1dc4a38f4", higiene, gilletteBrand);
		Product laminaDeBarbear = new Product("Lâmina de Barbear 2un", "Descartável", BigDecimal.valueOf(5.99), 90, "https://images.unsplash.com/photo-1503951914875-452162b0f3f1", higiene, gilletteBrand);
		Product absorvente = new Product("Absorvente com Abas 8un", "Proteção total", BigDecimal.valueOf(6.99), 80, "https://images.unsplash.com/photo-1584308666744-24d5c474f2ae", higiene, xeretaBrand);
		Product papelHigienico = new Product("Papel Higiênico 12 rolos", "Folha dupla", BigDecimal.valueOf(14.99), 100, "https://images.unsplash.com/photo-1584308666744-24d5c474f2ae", higiene, xeretaBrand);
		Product fioGengival = new Product("Fio Dental 50m", "Encerado", BigDecimal.valueOf(3.99), 110, "https://images.unsplash.com/photo-1559591937-abc1dc4a38f4", higiene, gilletteBrand);

		higiene.addProduct(shampoo); higiene.addProduct(condicionador); higiene.addProduct(sabonete);
		higiene.addProduct(pastaDente); higiene.addProduct(desodorante); higiene.addProduct(escovaDente);
		higiene.addProduct(laminaDeBarbear); higiene.addProduct(absorvente); higiene.addProduct(papelHigienico);
		higiene.addProduct(fioGengival);

		gilletteBrand.addProduct(shampoo); gilletteBrand.addProduct(condicionador); gilletteBrand.addProduct(sabonete);
		gilletteBrand.addProduct(pastaDente); gilletteBrand.addProduct(desodorante); gilletteBrand.addProduct(escovaDente);
		gilletteBrand.addProduct(laminaDeBarbear); gilletteBrand.addProduct(fioGengival);
		xeretaBrand.addProduct(absorvente); xeretaBrand.addProduct(papelHigienico);

		// =========================
		// PRODUCTS — FRIOS E EMBUTIDOS
		// =========================

		Product presunto = new Product("Presunto Fatiado 200g", "Defumado", BigDecimal.valueOf(8.99), 70, "https://images.unsplash.com/photo-1544025162-d76694265947", frios, sadiaBrand);
		Product peito = new Product("Peito de Peru 200g", "Light", BigDecimal.valueOf(11.99), 65, "https://images.unsplash.com/photo-1604503468506-a8da13d82791", frios, perdigaoBrand);
		Product salame = new Product("Salame Italiano 100g", "Fatiado", BigDecimal.valueOf(9.49), 60, "https://images.unsplash.com/photo-1544025162-d76694265947", frios, searaBrand);
		Product mortadela = new Product("Mortadela Fatiada 200g", "Tradicional", BigDecimal.valueOf(6.99), 80, "https://images.unsplash.com/photo-1544025162-d76694265947", frios, sadiaBrand);
		Product linguicaDefumada = new Product("Linguiça Defumada 300g", "Fatiada", BigDecimal.valueOf(10.99), 55, "https://images.unsplash.com/photo-1558030137-a56c1b004fa0", frios, perdigaoBrand);
		Product queijoProvolone = new Product("Queijo Provolone 200g", "Defumado", BigDecimal.valueOf(15.99), 45, "https://images.unsplash.com/photo-1486297678162-eb2a19b0a318", frios, vigorBrand);
		Product queijoGouda = new Product("Queijo Gouda 200g", "Fatiado", BigDecimal.valueOf(14.49), 50, "https://images.unsplash.com/photo-1452195100486-9cc805987862", frios, vigorBrand);
		Product blanquet = new Product("Blanquet de Peru 150g", "Zero gordura", BigDecimal.valueOf(10.49), 60, "https://images.unsplash.com/photo-1604503468506-a8da13d82791", frios, searaBrand);
		Product coppa = new Product("Coppa Italiana 100g", "Fatiada", BigDecimal.valueOf(12.99), 40, "https://images.unsplash.com/photo-1544025162-d76694265947", frios, sadiaBrand);
		Product pateFrango = new Product("Patê de Frango 150g", "Cremoso", BigDecimal.valueOf(7.99), 55, "https://images.unsplash.com/photo-1603833665858-e61d17a86224", frios, perdigaoBrand);

		frios.addProduct(presunto); frios.addProduct(peito); frios.addProduct(salame);
		frios.addProduct(mortadela); frios.addProduct(linguicaDefumada); frios.addProduct(queijoProvolone);
		frios.addProduct(queijoGouda); frios.addProduct(blanquet); frios.addProduct(coppa);
		frios.addProduct(pateFrango);

		sadiaBrand.addProduct(presunto); sadiaBrand.addProduct(mortadela); sadiaBrand.addProduct(coppa);
		perdigaoBrand.addProduct(peito); perdigaoBrand.addProduct(linguicaDefumada); perdigaoBrand.addProduct(pateFrango);
		searaBrand.addProduct(salame); searaBrand.addProduct(blanquet);
		vigorBrand.addProduct(queijoProvolone); vigorBrand.addProduct(queijoGouda);

		// =========================
		// PRODUCTS — CONGELADOS
		// =========================

		Product pizzaMargherita = new Product("Pizza Margherita 460g", "Congelada", BigDecimal.valueOf(19.99), 40, "https://images.unsplash.com/photo-1574071318508-1cdbab80d002", congelados, nestleBrand);
		Product pizzaCalabreza = new Product("Pizza Calabreza 460g", "Congelada", BigDecimal.valueOf(21.99), 38, "https://images.unsplash.com/photo-1574071318508-1cdbab80d002", congelados, nestleBrand);
		Product lasanha = new Product("Lasanha Bolonhesa 600g", "Congelada", BigDecimal.valueOf(24.99), 35, "https://images.unsplash.com/photo-1560781290-7dc94c0f8f4f", congelados, nestleBrand);
		Product nuggets = new Product("Nuggets de Frango 300g", "Congelado", BigDecimal.valueOf(13.99), 55, "https://images.unsplash.com/photo-1562967914-608f82629710", congelados, sadiaBrand);
		Product batataFrita = new Product("Batata Palito Congelada 400g", "Pré-frita", BigDecimal.valueOf(10.99), 60, "https://images.unsplash.com/photo-1518977676601-b53f82aba655", congelados, sadiaBrand);
		Product hamburger = new Product("Hambúrguer Bovino 4un", "Congelado", BigDecimal.valueOf(16.99), 50, "https://images.unsplash.com/photo-1568901346375-23c9450c58cd", congelados, sadiaBrand);
		Product esfiha = new Product("Esfiha de Carne 4un", "Congelada", BigDecimal.valueOf(11.99), 45, "https://images.unsplash.com/photo-1601924638867-3a6de6b7a500", congelados, searaBrand);
		Product panqueca = new Product("Panqueca Recheada 2un", "Congelada", BigDecimal.valueOf(9.99), 40, "https://images.unsplash.com/photo-1565299543923-37dd37887442", congelados, nestleBrand);
		Product sorvete = new Product("Sorvete Creme Flocos 1.5L", "Cremoso", BigDecimal.valueOf(22.99), 30, "https://images.unsplash.com/photo-1501443762994-82bd5dace89a", congelados, nestleBrand);
		Product picoleSorbet = new Product("Picolé Morango 3un", "Sorbet", BigDecimal.valueOf(7.99), 50, "https://images.unsplash.com/photo-1501443762994-82bd5dace89a", congelados, nestleBrand);

		congelados.addProduct(pizzaMargherita); congelados.addProduct(pizzaCalabreza); congelados.addProduct(lasanha);
		congelados.addProduct(nuggets); congelados.addProduct(batataFrita); congelados.addProduct(hamburger);
		congelados.addProduct(esfiha); congelados.addProduct(panqueca); congelados.addProduct(sorvete);
		congelados.addProduct(picoleSorbet);

		nestleBrand.addProduct(pizzaMargherita); nestleBrand.addProduct(pizzaCalabreza); nestleBrand.addProduct(lasanha);
		nestleBrand.addProduct(panqueca); nestleBrand.addProduct(sorvete); nestleBrand.addProduct(picoleSorbet);
		sadiaBrand.addProduct(nuggets); sadiaBrand.addProduct(batataFrita); sadiaBrand.addProduct(hamburger);
		searaBrand.addProduct(esfiha);

		// =========================
		// SAVE ALL PRODUCTS
		// =========================

		productRepository.saveAll(Arrays.asList(
				banana, maca, laranja, alface, tomate, cenoura, batata, cebola, alho, limao,
				cocaCola, suco, agua, aguaCom, sprite, nesquik, sucoLaranja, isotonico, chaPronto, energetico,
				detergente, sabao, desinfetante, amaciante, limpMulti, esponja, luva, vassoura, rodo, sacoLixo,
				arroz, feijao, feijaoPreto, aveia, granola, milho, lentilha, grao, farinha, macarrao,
				leite, leiteDesn, iogurte, iogurteFruta, queijo, queijoMinas, manteiga, creme, requeijao, nata,
				frango, fileFrango, coxinha, patinho, alcatra, linguicaToscana, bacon, costelaSuina, peixeTilapia, camarao,
				paoDeFarma, paoIntegral, bisnaguinha, bolo, boloLaranja, cookie, biscoitoSalgado, wafer, croissant, tortinha,
				shampoo, condicionador, sabonete, pastaDente, desodorante, escovaDente, laminaDeBarbear, absorvente, papelHigienico, fioGengival,
				presunto, peito, salame, mortadela, linguicaDefumada, queijoProvolone, queijoGouda, blanquet, coppa, pateFrango,
				pizzaMargherita, pizzaCalabreza, lasanha, nuggets, batataFrita, hamburger, esfiha, panqueca, sorvete, picoleSorbet
		));

		// =========================
		// ORDERS
		// =========================

		// user1 — 3 pedidos
		// =========================
		// ADDRESSES
		// =========================

		Address addr1 = new Address("Rua das Flores", 123, "Apto 45", "Jardim Primavera", "São Paulo", "SP", "01310100", user1);
		Address addr2 = new Address("Av. Paulista",456 , null, "Bela Vista", "São Paulo", "SP", "01311000", user2);
		Address addr3 = new Address("Rua das Acácias", 78, "Casa 2", "Vila Madalena", "São Paulo", "SP", "05435000", user3);
		Address addr4 = new Address("Rua do Comércio", 200, null, "Centro", "Campinas", "SP", "13010050", user4);
		Address addr5 = new Address( "Rua das Palmeiras", 55, "Bloco B", "Moema", "São Paulo", "SP", "04077000", user5);
		Address addr6 = new Address("Av. Brasil", 310, null, "Centro", "Rio de Janeiro", "RJ", "20040020", user6);
		Address addr7 = new Address("Rua das Orquídeas",88, "Sala 3", "Lourdes", "Belo Horizonte", "MG", "30170000", user7);
		Address addr8 = new Address("Rua das Margaridas", 15, null, "Boa Viagem", "Recife", "PE", "51020010", user8);
		Address addr9 = new Address("Rua das Violetas",402, "Apto 12", "Moinhos de Vento", "Porto Alegre", "RS", "90570020", user9);
		Address addr10 = new Address("Av. Goiás", 67, null, "Setor Central", "Goiânia", "GO", "74015010", user10);
		Address addr11 = new Address("Rua das Acácias", 456, "Apto 12", "Vila Nova", "São Paulo", "SP", "04567890", user1);
		Address addr12 = new Address("Av. Paulista", 1000, null, "Bela Vista", "São Paulo", "SP", "01310100", user4);
		Address addr13 = new Address("Rua Augusta", 789, "Casa 3", "Consolação", "São Paulo", "SP", "01305000", user3);

		addressRepository.saveAll(Arrays.asList(addr1, addr2, addr3, addr4, addr5, addr6, addr7, addr8, addr9, addr10,addr11, addr12, addr13 ));

		// =========================
		// ORDERS
		// =========================

		// user1 — 3 pedidos
		Order order1 = new Order(user1, PaymentMethod.CREDIT_CARD, DeliveryMethod.HOME_DELIVERY, addr1);
		order1.addItem(banana, 3);
		order1.addItem(leite, 2);
		order1.addItem(paoIntegral, 1);
		order1.addItem(iogurteFruta, 4);

		Order order2 = new Order(user1, PaymentMethod.PIX, DeliveryMethod.PICKUP, addr11);
		order2.addItem(arroz, 2);
		order2.addItem(feijao, 2);
		order2.addItem(detergente, 1);
		order2.addItem(sabao, 1);

		Order order3 = new Order(user1, PaymentMethod.BOLETO, DeliveryMethod.HOME_DELIVERY, addr1);
		order3.addItem(frango, 2);
		order3.addItem(batataFrita, 3);
		order3.addItem(cocaCola, 2);

		// user2 — 1 pedido
		Order order4 = new Order(user2, PaymentMethod.CREDIT_CARD, DeliveryMethod.HOME_DELIVERY, addr2);
		order4.addItem(queijo, 1);
		order4.addItem(presunto, 2);
		order4.addItem(paoDeFarma, 1);
		order4.addItem(manteiga, 1);

		// user3 — 2 pedidos
		Order order5 = new Order(user3, PaymentMethod.PIX, DeliveryMethod.HOME_DELIVERY, addr13);
		order5.addItem(laranja, 5);
		order5.addItem(suco, 3);
		order5.addItem(granola, 1);
		order5.addItem(aveia, 2);

		Order order6 = new Order(user3, PaymentMethod.CREDIT_CARD, DeliveryMethod.PICKUP, addr3);
		order6.addItem(shampoo, 1);
		order6.addItem(condicionador, 1);
		order6.addItem(sabonete, 3);
		order6.addItem(pastaDente, 2);

		// user4 — 4 pedidos
		Order order7 = new Order(user4, PaymentMethod.CREDIT_CARD, DeliveryMethod.PICKUP, addr4);
		order7.addItem(sabao, 1);
		order7.addItem(alface, 4);
		order7.addItem(tomate, 3);
		order7.addItem(cenoura, 2);

		Order order8 = new Order(user4, PaymentMethod.PIX, DeliveryMethod.HOME_DELIVERY, addr4);
		order8.addItem(agua, 6);
		order8.addItem(desinfetante, 2);
		order8.addItem(sacoLixo, 1);

		Order order9 = new Order(user4, PaymentMethod.BOLETO, DeliveryMethod.HOME_DELIVERY, addr4);
		order9.addItem(alcatra, 1);
		order9.addItem(costelaSuina, 1);
		order9.addItem(bacon, 2);
		order9.addItem(linguicaToscana, 1);

		Order order10 = new Order(user4, PaymentMethod.CREDIT_CARD, DeliveryMethod.PICKUP, addr12);
		order10.addItem(pizzaCalabreza, 2);
		order10.addItem(nuggets, 1);
		order10.addItem(hamburger, 2);
		order10.addItem(lasanha, 1);

		// user5 — 2 pedidos
		Order order11 = new Order(user5, PaymentMethod.PIX, DeliveryMethod.HOME_DELIVERY, addr5);
		order11.addItem(requeijao, 2);
		order11.addItem(queijoMinas, 1);
		order11.addItem(bisnaguinha, 3);
		order11.addItem(cookie, 2);

		Order order12 = new Order(user5, PaymentMethod.CREDIT_CARD, DeliveryMethod.PICKUP, addr5);
		order12.addItem(camarao, 1);
		order12.addItem(peixeTilapia, 2);
		order12.addItem(limao, 4);
		order12.addItem(alho, 2);

		// user6 — 2 pedidos
		Order order13 = new Order(user6, PaymentMethod.BOLETO, DeliveryMethod.HOME_DELIVERY, addr6);
		order13.addItem(mortadela, 2);
		order13.addItem(salame, 1);
		order13.addItem(queijoGouda, 1);
		order13.addItem(sprite, 3);

		Order order14 = new Order(user6, PaymentMethod.PIX, DeliveryMethod.PICKUP, addr6);
		order14.addItem(macarrao, 3);
		order14.addItem(farinha, 2);
		order14.addItem(creme, 4);
		order14.addItem(sorvete, 1);

		// user7 — 3 pedidos
		Order order15 = new Order(user7, PaymentMethod.CREDIT_CARD, DeliveryMethod.HOME_DELIVERY, addr7);
		order15.addItem(bolo, 1);
		order15.addItem(tortinha, 2);
		order15.addItem(wafer, 3);
		order15.addItem(nesquik, 2);

		Order order16 = new Order(user7, PaymentMethod.PIX, DeliveryMethod.HOME_DELIVERY, addr7);
		order16.addItem(fileFrango, 2);
		order16.addItem(coxinha, 3);
		order16.addItem(patinho, 1);

		Order order17 = new Order(user7, PaymentMethod.CREDIT_CARD, DeliveryMethod.PICKUP, addr7);
		order17.addItem(papelHigienico, 1);
		order17.addItem(absorvente, 2);
		order17.addItem(desodorante, 1);
		order17.addItem(laminaDeBarbear, 2);

		// user8 — 1 pedido
		Order order18 = new Order(user8, PaymentMethod.BOLETO, DeliveryMethod.HOME_DELIVERY, addr8);
		order18.addItem(leiteDesn, 4);
		order18.addItem(iogurte, 3);
		order18.addItem(nata, 2);
		order18.addItem(chaPronto, 2);

		// user9 — 2 pedidos
		Order order19 = new Order(user9, PaymentMethod.PIX, DeliveryMethod.HOME_DELIVERY, addr9);
		order19.addItem(batata, 3);
		order19.addItem(cebola, 2);
		order19.addItem(maca, 4);
		order19.addItem(aguaCom, 3);

		Order order20 = new Order(user9, PaymentMethod.CREDIT_CARD, DeliveryMethod.PICKUP, addr9);
		order20.addItem(queijoProvolone, 1);
		order20.addItem(coppa, 2);
		order20.addItem(blanquet, 1);
		order20.addItem(peito, 2);

		// user10 — 3 pedidos
		Order order21 = new Order(user10, PaymentMethod.CREDIT_CARD, DeliveryMethod.HOME_DELIVERY, addr10);
		order21.addItem(pizzaMargherita, 2);
		order21.addItem(esfiha, 3);
		order21.addItem(panqueca, 2);
		order21.addItem(picoleSorbet, 4);

		Order order22 = new Order(user10, PaymentMethod.PIX, DeliveryMethod.HOME_DELIVERY, addr10);
		order22.addItem(feijaoPreto, 2);
		order22.addItem(lentilha, 1);
		order22.addItem(grao, 1);
		order22.addItem(milho, 2);

		Order order23 = new Order(user10, PaymentMethod.BOLETO, DeliveryMethod.PICKUP, addr10);
		order23.addItem(luva, 1);
		order23.addItem(vassoura, 1);
		order23.addItem(rodo, 1);
		order23.addItem(esponja, 2);

		orderRepository.saveAll(Arrays.asList(
				order1, order2, order3, order4, order5, order6, order7, order8,
				order9, order10, order11, order12, order13, order14, order15,
				order16, order17, order18, order19, order20, order21, order22, order23
		));



		// =========================
		// PROMOTIONS
		// =========================

		// Promoção 1 — Nestlé: leve 3+ produtos Nestlé e pague menos por unidade (QUANTITY_DISCOUNT)
		// Produtos: nesquik (6.99), sucoLaranja (7.49), chaPronto (5.49) — todos acima do promotionalPrice (4.99)
		Promotion promoNestle = new Promotion(
				"Semana Nestlé",
				Instant.parse("2026-03-17T00:00:00Z"),
				Instant.parse("2026-03-31T00:00:00Z"),
				PromotionType.QUANTITY_DISCOUNT,
				new BigDecimal("15"),
				3
		);
		promoNestle.addProduct(nesquik);
		promoNestle.addProduct(sucoLaranja);
		promoNestle.addProduct(chaPronto);

		// Promoção 2 — Bebidas: preço direto reduzido (DIRECT_PRICE)
		// Produtos: cocaCola (9.99), sprite (8.99), energetico (9.49) — todos acima do promotionalPrice (6.99)
		Promotion promoBebidas = new Promotion(
				"Promoção Bebidas da Semana",
				Instant.parse("2026-03-16T00:00:00Z"),
				Instant.parse("2026-03-20T00:00:00Z"),
				PromotionType.DIRECT_PRICE,
				new BigDecimal("5"),
				null
		);
		promoBebidas.addProduct(cocaCola);
		promoBebidas.addProduct(sprite);
		promoBebidas.addProduct(energetico);

		// Promoção 3 — Carnes: 2 produtos com preço direto reduzido (DIRECT_PRICE)
		// Produtos: frango (14.99), coxinha (16.99) — ambos acima do promotionalPrice (11.99)
		Promotion promoCarnes = new Promotion(
				"Oferta de Carnes",
				Instant.parse("2026-03-15T00:00:00Z"),
				Instant.parse("2026-03-25T00:00:00Z"),
				PromotionType.DIRECT_PRICE,
				new BigDecimal("10"),
				null
		);
		promoCarnes.addProduct(frango);
		promoCarnes.addProduct(coxinha);

		promotionRepository.saveAll(Arrays.asList(promoNestle, promoBebidas, promoCarnes));
	}
}