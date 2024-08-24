package com.badAfeez.code.services.customer;

import com.badAfeez.code.data.models.ArtWorks;
import com.badAfeez.code.data.models.UserRole;
import com.badAfeez.code.data.repository.CustomerRepository;
import com.badAfeez.code.dtoBby.request.CreateArtistRequest;
import com.badAfeez.code.dtoBby.request.CreateArtworkRequest;
import com.badAfeez.code.dtoBby.request.LoginRequest;
import com.badAfeez.code.dtoBby.request.RegisterCustomerRequest;
import com.badAfeez.code.dtoBby.response.CreateArtistResponse;
import com.badAfeez.code.dtoBby.response.LoginResponse;
import com.badAfeez.code.dtoBby.response.RegisterCustomerResponse;
import com.badAfeez.code.services.artist.ArtistServices;
import com.badAfeez.code.services.artworks.ArtWorkService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ArtistServices artistServices;
    @Autowired
    private ArtWorkService artWorkService;
    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerRepository.deleteAll();
    }

    @Test
    void testThatCustomerCanRegisterAndLogin(){
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
        assertThat(loginResponse.getCustomerId()).isEqualTo(registerResponse.getCustomerId());
        assertThat(loginResponse).isNotNull();
        assertThat(loginResponse.getRole()).isEqualTo(UserRole.BUYER);
    }
    @Test
    void testThatCustomerCanViewArtistArtworks(){
        RegisterCustomerRequest register = new RegisterCustomerRequest();
        register.setCustomerAddress("one way to leave semicolon street");
        register.setCustomerName("Leave semicolon");
        register.setCustomerPhone("123");
        register.setCustomerEmail("LeaveSemicolon@.com");
        register.setPassword("myBby");
        register.setUserRole(UserRole.BUYER);

        RegisterCustomerResponse registerResponse = customerService.registerCustomer(register);
        assertNotNull(registerResponse);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setCustomerEmail(register.getCustomerEmail());
        loginRequest.setPassword(register.getPassword());

        LoginResponse loginResponse = customerService.loginCustomer(loginRequest);
        assertThat(loginResponse.getCustomerId()).isEqualTo(registerResponse.getCustomerId());
        assertThat(loginResponse).isNotNull();
        assertThat(loginResponse.getRole()).isEqualTo(UserRole.BUYER);

        CreateArtistRequest createArtistRequest = new CreateArtistRequest();
        createArtistRequest.setPhoneNumber("1");
        createArtistRequest.setAge("26");
        createArtistRequest.setGender("inBtwn");
        createArtistRequest.setName("badChi");
        createArtistRequest.setUserName("myChi");
        CreateArtistResponse createArtistResponse = artistServices.createArtist(createArtistRequest);

        CreateArtworkRequest artworkRequest = new CreateArtworkRequest();
        artworkRequest.setDescription("image of the underworld");
        artworkRequest.setTitle("how dark is it");
        artworkRequest.setPrice(300.00);
        artworkRequest.setImageUrl("qwerty");
        artworkRequest.setArtistId(createArtistResponse.getArtistId());

        artWorkService.createOrUpdateArtworks(artworkRequest);

        List<ArtWorks> artWorks = artistServices.getArtwoksByArtist(createArtistResponse.getArtistId(), loginResponse.getCustomerId());
        assertThat(artWorks.size()).isEqualTo(1);
        assertThat(artWorks).isNotNull();

    }
}