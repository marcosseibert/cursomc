package com.seibert.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.seibert.cursomc.domain.Address;
import com.seibert.cursomc.domain.Category;
import com.seibert.cursomc.domain.City;
import com.seibert.cursomc.domain.Client;
import com.seibert.cursomc.domain.Order;
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
import com.seibert.cursomc.repositories.OrderRepository;
import com.seibert.cursomc.repositories.PaymentRepository;
import com.seibert.cursomc.repositories.ProductRepository;
import com.seibert.cursomc.repositories.StateRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

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
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category ca1 = new Category(null, "Informática");
		Category ca2 = new Category(null, "Escritório");
		
		Product p1 = new Product(null, "Computer", 2500.00);
		Product p2 = new Product(null, "Printer", 500.00);
		Product p3 = new Product(null, "Mouse", 75.00);
		
		ca1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		ca2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(ca1));
		p2.getCategories().addAll(Arrays.asList(ca1, ca2));
		p3.getCategories().addAll(Arrays.asList(ca1));
		
		categoryRepository.saveAll(Arrays.asList(ca1, ca2));
		productRepository.saveAll(Arrays.asList(p1,p2,p3));
		
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
		
		Client cli1 = new Client(null,"Maria Silva", "maria@gmail", "36378912", ClientType.PESSOA_FISICA);
		
		cli1.getPhones().addAll(Arrays.asList("3211354698","321654987"));
		
		Address address1 = new Address(null,"Rua Flores","300","apto 303","Jardim Alice","13224566", cli1,city1);
		Address address2 = new Address(null,"Avenida Matos","105","sala 800","Centro","12455656", cli1,city2);
		
		cli1.getAddresses().addAll(Arrays.asList(address1, address2));
		
		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(address1,address2));
		
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
		
	}
}
