package com.seibert.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.seibert.cursomc.domain.enums.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.seibert.cursomc.domain.Address;
import com.seibert.cursomc.domain.Category;
import com.seibert.cursomc.domain.City;
import com.seibert.cursomc.domain.Client;
import com.seibert.cursomc.domain.Order;
import com.seibert.cursomc.domain.OrderItem;
import com.seibert.cursomc.domain.Payment;
import com.seibert.cursomc.domain.PaymentWithCard;
import com.seibert.cursomc.domain.PaymentWithTicket;
import com.seibert.cursomc.domain.Product;
import com.seibert.cursomc.domain.State;
import com.seibert.cursomc.domain.enums.ClientType;
import com.seibert.cursomc.domain.enums.PaymentStatus;
import com.seibert.cursomc.repositories.AddressRepository;
import com.seibert.cursomc.repositories.CategoryRepository;
import com.seibert.cursomc.repositories.CityRepository;
import com.seibert.cursomc.repositories.ClientRepository;
import com.seibert.cursomc.repositories.OrderItemRepository;
import com.seibert.cursomc.repositories.OrderRepository;
import com.seibert.cursomc.repositories.PaymentRepository;
import com.seibert.cursomc.repositories.ProductRepository;
import com.seibert.cursomc.repositories.StateRepository;

@Service
public class DBservices {


	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	public void instantiateTestDataBase() throws ParseException {
		
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		Category cat3 = new Category(null, "Cama mesa e banho");
		Category cat4 = new Category(null, "Eletrônicos");
		Category cat5 = new Category(null, "Jardinagem");
		Category cat6 = new Category(null, "Decoração");
		Category cat7 = new Category(null, "Perfumaria");
		Category cat8 = new Category(null, "teste");
		
		Product p1 = new Product(null, "Computador", 2500.00);
		Product p2 = new Product(null, "Impressora", 500.00);
		Product p3 = new Product(null, "Mouse", 75.00);
		Product p4 = new Product(null, "Mesa de Escritório", 300.00);
		Product p5 = new Product(null, "Toalha", 50.00);
		Product p6 = new Product(null, "Colcha", 200.00);
		Product p7 = new Product(null, "TV true color", 1200.00);
		Product p8 = new Product(null, "Roçadeira", 800.00);
		Product p9 = new Product(null, "Abajour", 100.00);
		Product p10 = new Product(null, "Pendente", 180.00);
		Product p11= new Product(null, "Shampoo", 90.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2, p4));
		cat3.getProducts().addAll(Arrays.asList(p5, p6));
		cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProducts().addAll(Arrays.asList(p8));
		cat6.getProducts().addAll(Arrays.asList(p9, p10));
		cat7.getProducts().addAll(Arrays.asList(p11));
		
		
		p1.getCategories().addAll(Arrays.asList(cat1, cat4));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategories().addAll(Arrays.asList(cat1, cat4));
		p4.getCategories().addAll(Arrays.asList(cat2));
		p5.getCategories().addAll(Arrays.asList(cat3));
		p6.getCategories().addAll(Arrays.asList(cat3));
		p7.getCategories().addAll(Arrays.asList(cat4));
		p8.getCategories().addAll(Arrays.asList(cat5));
		p9.getCategories().addAll(Arrays.asList(cat6));
		p10.getCategories().addAll(Arrays.asList(cat6));
		p11.getCategories().addAll(Arrays.asList(cat7));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		State stt1 = new State(null, "Minas Gerais");
		State stt2 = new State(null, "São Paulo");
		State stt3 = new State(null, "Paraná");
		
		City city1 = new City(null, "Uberlândia", stt1);
		City city2 = new City(null, "São Paulo", stt2);
		City city3 = new City(null, "Campinas", stt2);
		City city4 = new City(null, "Maringá", stt3);
		City city5 = new City(null, "Indaiatuba", stt2);
		
		stt1.getCities().addAll(Arrays.asList(city1));
		stt2.getCities().addAll(Arrays.asList(city2,city3,city5));
		stt3.getCities().addAll(Arrays.asList(city4));
		
		stateRepository.saveAll(Arrays.asList(stt1,stt2,stt3));
		cityRepository.saveAll(Arrays.asList(city1,city2,city3,city4,city5));
		
		Client cli1 = new Client(null,"Maria Silva", "maria@gmail", "76659921028", ClientType.PESSOA_FISICA, passwordEncoder.encode("123"));
		cli1.getPhones().addAll(Arrays.asList("3211354698","321654987"));

		Client cli2 = new Client(null,"Ana Costa", "ana_costa@gmail", "25998424093", ClientType.PESSOA_FISICA, passwordEncoder.encode("123"));
		cli2.getPhones().addAll(Arrays.asList("3211354698","321654987"));
		cli2.addProfile(Profile.ADMIN);

		Address address1 = new Address(null,"Rua Flores","300","apto 303","Jardim Alice","13224566", cli1,city1);
		Address address2 = new Address(null,"Avenida Matos","105","sala 800","Centro","12455656", cli1,city2);
		Address address3 = new Address(null,"Avenida Floriano","1233","casa 3","morada do sol","32323232", cli2,city2);
		
		cli1.getAddresses().addAll(Arrays.asList(address1, address2));
		cli2.getAddresses().addAll(Arrays.asList(address3));

		clientRepository.saveAll(Arrays.asList(cli1,cli2));
		addressRepository.saveAll(Arrays.asList(address1,address2,address3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		
		Order order1 = new Order(null, sdf.parse("30/09/2017 10:32"), cli1, address1);
		Order order2 = new Order(null, sdf.parse("10/10/2017 19:35"), cli1, address2);
		
		cli1.getOrders().addAll(Arrays.asList(order1,order2));
		
		Payment pay1 = new PaymentWithCard(null, PaymentStatus.PAID, order1, 6);
		order1.setPayment(pay1);
		
		Payment pay2 = new PaymentWithTicket(null, PaymentStatus.PENDING, order2, sdf.parse("10/10/2017 19:35"), null);
		order2.setPayment(pay2);
		
		orderRepository.saveAll(Arrays.asList(order1,order2));
		paymentRepository.saveAll(Arrays.asList(pay1,pay2));
		
		OrderItem orderItem1 = new OrderItem(order1, p1, 0.00, 1, 2000.00);
		OrderItem orderItem2 = new OrderItem(order1, p3, 0.00, 2, 80.00);
		OrderItem orderItem3 = new OrderItem(order2, p2, 100.00, 1, 800.00);
		
		order1.getItems().addAll(Arrays.asList(orderItem1, orderItem2));
		order2.getItems().addAll(Arrays.asList(orderItem3));
		
		p1.getItems().addAll(Arrays.asList());
		p2.getItems().addAll(Arrays.asList());
		p3.getItems().addAll(Arrays.asList());
		
		orderItemRepository.saveAll(Arrays.asList(orderItem1,orderItem2,orderItem3));
	}
}
