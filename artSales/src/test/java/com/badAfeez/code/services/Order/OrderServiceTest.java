package com.badAfeez.code.services.Order;

import com.badAfeez.code.Exception.ArtWorkNotFound;
import com.badAfeez.code.data.models.ArtWorks;
import com.badAfeez.code.data.models.UserRole;
import com.badAfeez.code.data.repository.ArtWorkRepository;
import com.badAfeez.code.data.repository.ArtistRepository;
import com.badAfeez.code.data.repository.CustomerRepository;
import com.badAfeez.code.data.repository.OrderRepository;
import com.badAfeez.code.dtoBby.request.*;
import com.badAfeez.code.dtoBby.response.CreateArtistResponse;
import com.badAfeez.code.dtoBby.response.CreatePurchaseResponse;
import com.badAfeez.code.dtoBby.response.LoginResponse;
import com.badAfeez.code.dtoBby.response.RegisterCustomerResponse;
import com.badAfeez.code.services.artist.ArtistServices;
import com.badAfeez.code.services.artworks.ArtWorkService;
import com.badAfeez.code.services.customer.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ArtWorkRepository artWorkRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ArtistServices artistServices;
    @Autowired
    private ArtWorkService artWorkService;
    @Autowired
    private CustomerService customerService;

    @BeforeEach
    void cleanUp() {
        artistRepository.deleteAll();
        customerRepository.deleteAll();
        artWorkRepository.deleteAll();
        orderRepository.deleteAll();
    }
    @Test
    void testThatArtworkCanBePurchased() {

        RegisterCustomerRequest registerCustomerRequest = new RegisterCustomerRequest();
        registerCustomerRequest.setUserRole(UserRole.BUYER);
        registerCustomerRequest.setCustomerEmail("bigDee@gmail.com");
        registerCustomerRequest.setPassword("bigdee");
        registerCustomerRequest.setCustomerPhone("12345");
        registerCustomerRequest.setCustomerAddress("123 yahoo chi");
        registerCustomerRequest.setCustomerName("chiSexy");
        RegisterCustomerResponse registerCustomerResponse = customerService.registerCustomer(registerCustomerRequest);

        CreateArtistRequest createArtistRequest = new CreateArtistRequest();
        createArtistRequest.setUserName("artistKolo");
        createArtistRequest.setPhoneNumber("123");
        createArtistRequest.setAge("12");
        createArtistRequest.setGender("guyMan");
        createArtistRequest.setName("sexyChi");
        CreateArtistResponse artistResponse = artistServices.createArtist(createArtistRequest);

        CreateArtworkRequest createArtworkRequest = new CreateArtworkRequest();
        createArtworkRequest.setImageUrl("chi.jpg");
        createArtworkRequest.setTitle("sexyChi");
        createArtworkRequest.setDescription("how to maintain steeze in semicolon");
        createArtworkRequest.setPrice(1.00);
        createArtworkRequest.setArtistId(artistResponse.getArtistId());
        createArtworkRequest.setAvailableQuantity(5);


        ArtWorks createdArtWork = artWorkService.createOrUpdateArtworks(createArtworkRequest);
        String artWorkId = createdArtWork.getId();

        CreatePurchaseRequest createPurchaseRequest = new CreatePurchaseRequest();
        createPurchaseRequest.setArtworkId(artWorkId);
        createPurchaseRequest.setOrderDate(LocalDateTime.now());
        createPurchaseRequest.setStatus("completed");
        createPurchaseRequest.setQuantity(2);
        createPurchaseRequest.setCustomerId(registerCustomerResponse.getCustomerId());

        CreatePurchaseResponse createPurchaseResponse = orderService.processedOrder(createPurchaseRequest);
        assertNotNull(createPurchaseResponse);
        assertEquals("Order purchase successful", createPurchaseResponse.getMessage());
        ArtWorks updatedArtWork = artWorkRepository.findById(artWorkId).orElseThrow(() -> new ArtWorkNotFound());
        assertEquals(3, updatedArtWork.getAvailableQuantity());
    }
    @Test
    void testThatBuyerCanPurchaseArtworksAfterLogin() {
        RegisterCustomerRequest register = new RegisterCustomerRequest();
        register.setCustomerAddress("one way to leave semicolon street");
        register.setCustomerName("Leave semicolon");
        register.setCustomerPhone("123");
        register.setCustomerEmail("uMustLeaveSemicolon@leaveSemicolon.com");
        register.setPassword("myBby");
        register.setUserRole(UserRole.BUYER);

        RegisterCustomerResponse registerResponse = customerService.registerCustomer(register);
        assertNotNull(registerResponse);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setCustomerEmail(register.getCustomerEmail());
        loginRequest.setPassword(register.getPassword());

        LoginResponse loginResponse = customerService.loginCustomer(loginRequest);
        assertNotNull(loginResponse);
        assertThat(loginResponse.getCustomerId()).isEqualTo(registerResponse.getCustomerId());
        assertThat(loginResponse.getRole()).isEqualTo(UserRole.BUYER);

        CreateArtistRequest createArtistRequest = new CreateArtistRequest();
        createArtistRequest.setUserName("artistKolo");
        createArtistRequest.setPhoneNumber("123");
        createArtistRequest.setAge("12");
        createArtistRequest.setGender("M");
        createArtistRequest.setName("sexyChi");
        CreateArtistResponse artistResponse = artistServices.createArtist(createArtistRequest);
        assertNotNull(artistResponse);

        String artistId = artistResponse.getArtistId();

        CreateArtworkRequest createArtworkRequest = new CreateArtworkRequest();
        createArtworkRequest.setDescription("image of the underworld");
        createArtworkRequest.setTitle("how dark is it");
        createArtworkRequest.setPrice(300.00);
        createArtworkRequest.setImageUrl("qwerty");
        createArtworkRequest.setAvailableQuantity(5);
        createArtworkRequest.setArtistId(artistId);

        ArtWorks createdArtWork = artWorkService.createOrUpdateArtworks(createArtworkRequest);
        assertNotNull(createdArtWork.getId(), "Artwork ID should not be null");
        String artWorkId = createdArtWork.getId();

        CreatePurchaseRequest createPurchaseRequest = new CreatePurchaseRequest();
        createPurchaseRequest.setArtworkId(artWorkId);
        createPurchaseRequest.setOrderDate(LocalDateTime.now());
        createPurchaseRequest.setStatus("completed");
        createPurchaseRequest.setQuantity(2);
        createPurchaseRequest.setCustomerId(registerResponse.getCustomerId());

        CreatePurchaseResponse createPurchaseResponse = orderService.processedOrder(createPurchaseRequest);
        assertNotNull(createPurchaseResponse);
        assertEquals("Order purchase successful", createPurchaseResponse.getMessage());

        ArtWorks updatedArtWork = artWorkRepository.findById(artWorkId).orElseThrow(() -> new ArtWorkNotFound());
        assertEquals(3, updatedArtWork.getAvailableQuantity());
    }


    @Test
    void testThatBuyerCanLoginAndBrowseArtworks() {
        RegisterCustomerRequest register = new RegisterCustomerRequest();
        register.setCustomerAddress("one way to leave semicolon street");
        register.setCustomerName("Leave semicolon");
        register.setCustomerPhone("123");
        register.setCustomerEmail("uMustLeaveSemicolon@leaveSemicolon.com");
        register.setPassword("myBby");
        register.setUserRole(UserRole.BUYER);

        RegisterCustomerResponse registerResponse = customerService.registerCustomer(register);
        assertNotNull(registerResponse);
        assertThat(registerResponse.getCustomerId()).isNotNull();


        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setCustomerEmail(register.getCustomerEmail());
        loginRequest.setPassword(register.getPassword());

        LoginResponse loginResponse = customerService.loginCustomer(loginRequest);
        assertNotNull(loginResponse);
        assertThat(loginResponse.getCustomerId()).isEqualTo(registerResponse.getCustomerId());
        assertThat(loginResponse.getRole()).isEqualTo(UserRole.BUYER);

        CreateArtistRequest createArtistRequest = new CreateArtistRequest();
        createArtistRequest.setUserName("artistKolo");
        createArtistRequest.setPhoneNumber("123");
        createArtistRequest.setAge("12");
        createArtistRequest.setGender("M");
        createArtistRequest.setName("sexyChi");
        CreateArtistResponse artistResponse = artistServices.createArtist(createArtistRequest);
        assertNotNull(artistResponse);

        String artistId = artistResponse.getArtistId();

        CreateArtworkRequest artworkRequest = new CreateArtworkRequest();
        artworkRequest.setDescription("image of the underworld");
        artworkRequest.setTitle("how dark is it");
        artworkRequest.setPrice(300.00);
        artworkRequest.setImageUrl("qwerty");
        artworkRequest.setAvailableQuantity(5);
        artworkRequest.setArtistId(artistId);

        CreateArtworkRequest artworkRequest1 = new CreateArtworkRequest();
        artworkRequest1.setDescription("image of the underworld");
        artworkRequest1.setTitle("how dark is it");
        artworkRequest1.setPrice(300.00);
        artworkRequest1.setImageUrl("qwerty");
        artworkRequest1.setAvailableQuantity(3);
        artworkRequest1.setArtistId(artistId);

        artWorkService.createOrUpdateArtworks(artworkRequest);
        artWorkService.createOrUpdateArtworks(artworkRequest1);

        List<ArtWorks> artworks = artWorkService.getArtworksByArtist(artistId);

        assertNotNull(artworks);
        assertEquals(2, artworks.size());

        System.out.println("Number of artworks by artist " + artistId + ": " + artworks.size());
        for (ArtWorks artwork : artworks) {
            System.out.println("Artwork Title: " + artwork.getTitle() + ", Price: " + artwork.getPrice());
        }
    }
    @Test
    void testThatArtistCanCreateArtworks() {
        RegisterCustomerRequest registerArtist = new RegisterCustomerRequest();
        registerArtist.setCustomerAddress("one way to leave semicolon street");
        registerArtist.setCustomerName("Leave semicolon");
        registerArtist.setCustomerPhone("123");
        registerArtist.setCustomerEmail("uMustLeaveSemicolon@leaveSemicolon.com");
        registerArtist.setPassword("myBby");
        registerArtist.setUserRole(UserRole.ARTIST);

        RegisterCustomerResponse registerResponse = customerService.registerCustomer(registerArtist);
        assertNotNull(registerResponse);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setCustomerEmail(registerArtist.getCustomerEmail());
        loginRequest.setPassword(registerArtist.getPassword());

        LoginResponse loginResponse = customerService.loginCustomer(loginRequest);
        assertNotNull(loginResponse);
        assertThat(loginResponse.getCustomerId()).isEqualTo(registerResponse.getCustomerId());
        assertThat(loginResponse.getRole()).isEqualTo(UserRole.ARTIST);

        CreateArtistRequest createArtistRequest = new CreateArtistRequest();
        createArtistRequest.setUserName("artisKolo");
        createArtistRequest.setPhoneNumber("12345");
        createArtistRequest.setAge("30");
        createArtistRequest.setGender("M");
        createArtistRequest.setName("LactoseBender");
        CreateArtistResponse artistResponse = artistServices.createArtist(createArtistRequest);
        assertNotNull(artistResponse);

        String artistId = artistResponse.getArtistId();

        CreateArtworkRequest createArtworkRequest = new CreateArtworkRequest();
        createArtworkRequest.setDescription("NonseseArtwork");
        createArtworkRequest.setTitle("SunsetBby");
        createArtworkRequest.setPrice(500.00);
        createArtworkRequest.setImageUrl("sunset.jpg");
        createArtworkRequest.setAvailableQuantity(10);
        createArtworkRequest.setArtistId(artistId);

        ArtWorks createdArtWork = artWorkService.createOrUpdateArtworks(createArtworkRequest);
        assertNotNull(createdArtWork);
        assertThat(createdArtWork.getArtist()).isEqualTo(artistId);
    }


//
//    @Test
//    void testThatArtworkCanBePurchased(){
//        RegisterCustomerRequest registerCustomerRequest = new RegisterCustomerRequest();
//        registerCustomerRequest.setUserRole(UserRole.BUYER);
//        registerCustomerRequest.setCustomerEmail("bigDee@gmail.com");
//        registerCustomerRequest.setPassword("bigdee");
//        registerCustomerRequest.setCustomerPhone("12345");
//        registerCustomerRequest.setCustomerAddress("123 yahoo chi");
//        registerCustomerRequest.setCustomerName("chiSexy");
//        RegisterCustomerResponse registerCustomerResponse = customerService.registerCustomer(registerCustomerRequest);
//
//        CreateArtistRequest createArtistRequest = new CreateArtistRequest();
//        createArtistRequest.setUserName("artistKolo");
//        createArtistRequest.setPhoneNumber("123");
//        createArtistRequest.setAge("12");
//        createArtistRequest.setGender("guyMan");
//        createArtistRequest.setName("sexyChi");
//        CreateArtistResponse response = artistServices.createArtist(createArtistRequest);
//
//        CreateArtworkRequest createArtworkRequest = new CreateArtworkRequest();
//        createArtworkRequest.setImageUrl("chi.jpg");
//        createArtworkRequest.setTitle("sexyChi");
//        createArtworkRequest.setDescription("how to maintain steeze in semicolon");
//        createArtworkRequest.setPrice(1.00);
//        createArtworkRequest.setArtistId(response.getArtistId());
//        createArtworkRequest.setAvailableQuantity(5L);
//
//        artWorkService.createOrUpdateArtworks(createArtworkRequest);
//
//        CreatePurchaseRequest createPurchaseRequest = new CreatePurchaseRequest();
//        createPurchaseRequest.setArtworkId(createArtworkRequest.getArtWorkId());
//        createPurchaseRequest.setOrderDate(LocalDateTime.now());
//        createPurchaseRequest.setStatus("completed");
//        createPurchaseRequest.setQuantity(2);
//        createPurchaseRequest.setQuantity(2);
//        createPurchaseRequest.setCustomerId(registerCustomerResponse.getCustomerId());
//
//        CreatePurchaseResponse createPurchaseResponse = orderService.processedOrder(createPurchaseRequest);
//        createPurchaseResponse.setMessage("order purchase successful");
//
//    }

    @Test
    void testThatArtistCanCreateArtworks_0() {
        // Register as an ARTIST
        RegisterCustomerRequest registerArtist = new RegisterCustomerRequest();
        registerArtist.setCustomerAddress("one way to leave semicolon street");
        registerArtist.setCustomerName("Leave semicolon");
        registerArtist.setCustomerPhone("123");
        registerArtist.setCustomerEmail("uMustLeaveSemicolon@leaveSemicolon.com");
        registerArtist.setPassword("myBby");
        registerArtist.setUserRole(UserRole.ARTIST);

        RegisterCustomerResponse registerResponse = customerService.registerCustomer(registerArtist);
        assertNotNull(registerResponse);


        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setCustomerEmail(registerArtist.getCustomerEmail());
        loginRequest.setPassword(registerArtist.getPassword());

        LoginResponse loginResponse = customerService.loginCustomer(loginRequest);
        assertNotNull(loginResponse);
        assertThat(loginResponse.getCustomerId()).isEqualTo(registerResponse.getCustomerId());
        assertThat(loginResponse.getRole()).isEqualTo(UserRole.ARTIST);


        CreateArtistRequest createArtistRequest = new CreateArtistRequest();
        createArtistRequest.setUserName("artisKolo");
        createArtistRequest.setPhoneNumber("12345");
        createArtistRequest.setAge("30");
        createArtistRequest.setGender("M");
        createArtistRequest.setName("LactoseBender");
        CreateArtistResponse artistResponse = artistServices.createArtist(createArtistRequest);
        assertNotNull(artistResponse);

        String artistId = artistResponse.getArtistId();

        CreateArtworkRequest createArtworkRequest = new CreateArtworkRequest();
        createArtworkRequest.setDescription("NonseseArtwork");
        createArtworkRequest.setTitle("SunsetBby");
        createArtworkRequest.setPrice(500.00);
        createArtworkRequest.setImageUrl("sunset.jpg");
        createArtworkRequest.setAvailableQuantity(10);
        createArtworkRequest.setArtistId(artistId);

        ArtWorks createdArtWork = artWorkService.createOrUpdateArtworks(createArtworkRequest);
        assertNotNull(createdArtWork);
        assertThat(createdArtWork.getArtist()).isEqualTo(artistId);
    }


}