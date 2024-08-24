package com.badAfeez.code.services.artist;

import com.badAfeez.code.Exception.ArtistNotFound;
import com.badAfeez.code.data.models.ArtWorks;
import com.badAfeez.code.data.models.Artist;
import com.badAfeez.code.data.repository.ArtistRepository;
import com.badAfeez.code.dtoBby.request.CreateArtistRequest;
import com.badAfeez.code.dtoBby.request.CreateArtworkRequest;
import com.badAfeez.code.dtoBby.request.DeleteArtworkRequest;
import com.badAfeez.code.dtoBby.response.CreateArtWorkResponse;
import com.badAfeez.code.dtoBby.response.CreateArtistResponse;
import com.badAfeez.code.services.artworks.ArtWorkService;
import com.mongodb.internal.bulk.DeleteRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ArtistServicesTest {

    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private ArtistServices artistServices;
    @Autowired
    private ArtWorkService artWorkService;

    @BeforeEach
    void cleanUP(){
        artistRepository.deleteAll();
    }

    @Test
    void testThatArtistNeedsToBeCreated(){
        CreateArtistRequest createArtistRequest = new CreateArtistRequest();
        createArtistRequest.setName("artistKolo");
        createArtistRequest.setAge("12");
        createArtistRequest.setGender("M");
        createArtistRequest.setPhoneNumber("1234");
        createArtistRequest.setUserName("bigKala");

        CreateArtistResponse createArtistResponse = artistServices.createArtist(createArtistRequest);
        assertThat(createArtistResponse).isNotNull();
        assertThat(createArtistResponse.getArtistId()).isNotNull();
    }
    @Test
    void testThatArtistCanAddArtworksAndFindThem(){
        CreateArtistRequest createArtistRequest = new CreateArtistRequest();
        createArtistRequest.setName("artistKolo");
        createArtistRequest.setAge("12");
        createArtistRequest.setGender("M");
        createArtistRequest.setPhoneNumber("1234");
        createArtistRequest.setUserName("bigKala");
        CreateArtistResponse createArtistResponse = artistServices.createArtist(createArtistRequest);

        CreateArtworkRequest createArtworkRequest = new CreateArtworkRequest();
        createArtworkRequest.setArtistId(createArtistResponse.getArtistId());
        createArtworkRequest.setDescription("underworldImg");
        createArtworkRequest.setTitle("underworldTitle");
        createArtworkRequest.setPrice(200.00);
        createArtworkRequest.setImageUrl("bad.afeez");

        CreateArtworkRequest createArtworkRequests = new CreateArtworkRequest();
        createArtworkRequests.setArtistId(createArtistResponse.getArtistId());
        createArtworkRequests.setDescription("underworldImg");
        createArtworkRequests.setTitle("underworldTitle");
        createArtworkRequests.setPrice(200.00);
        createArtworkRequests.setImageUrl("bad.afeez");

        artWorkService.createOrUpdateArtworks(createArtworkRequest);
        artWorkService.createOrUpdateArtworks(createArtworkRequests);

        Artist artist = artistRepository.findById(createArtistResponse.getArtistId()).orElseThrow(()->new ArtistNotFound());
        List<ArtWorks> artWorks = artist.getArtWorks();
        assertThat(artWorks.size()).isEqualTo(2);

        for (ArtWorks artwork : artWorks) {
            System.out.println("Artwork ID: " + artwork.getId());
            System.out.println("Title: " + artwork.getTitle());
            System.out.println("Description: " + artwork.getDescription());
            System.out.println("Price: " + artwork.getPrice());
            System.out.println("Image URL: " + artwork.getImageUrl());
            System.out.println("Artist ID: " + artwork.getArtist().getId());
            System.out.println("------");
        }
    }
    @Test
    void testThatArtistCanDeleteAnArtwork(){
        CreateArtistRequest createArtistRequest = new CreateArtistRequest();
        createArtistRequest.setName("artistKolo");
        createArtistRequest.setAge("12");
        createArtistRequest.setGender("M");
        createArtistRequest.setPhoneNumber("1234");
        createArtistRequest.setUserName("bigKala");
        CreateArtistResponse createArtistResponse = artistServices.createArtist(createArtistRequest);

        CreateArtworkRequest createArtworkRequest = new CreateArtworkRequest();
        createArtworkRequest.setArtistId(createArtistResponse.getArtistId());
        createArtworkRequest.setDescription("underworldImg");
        createArtworkRequest.setTitle("underworldTitle");
        createArtworkRequest.setPrice(200.00);
        createArtworkRequest.setImageUrl("bad.afeez");

        CreateArtworkRequest createArtworkRequests = new CreateArtworkRequest();
        createArtworkRequests.setArtistId(createArtistResponse.getArtistId());
        createArtworkRequests.setDescription("underworldImg");
        createArtworkRequests.setTitle("underworldTitle");
        createArtworkRequests.setPrice(200.00);
        createArtworkRequests.setImageUrl("bad.afeez");

        artWorkService.createOrUpdateArtworks(createArtworkRequest);
        artWorkService.createOrUpdateArtworks(createArtworkRequests);

        Artist artist = artistRepository.findById(createArtistResponse.getArtistId()).orElseThrow(()->new ArtistNotFound());
        List<ArtWorks> artWorks = artist.getArtWorks();
        assertThat(artWorks.size()).isEqualTo(2);

        DeleteArtworkRequest deleteArtworkRequest = new DeleteArtworkRequest();
        deleteArtworkRequest.setArtworkId(artWorks.get(0).getId());
        artWorkService.deleteArtWork(deleteArtworkRequest.getArtworkId());

        Artist newListOfArtworks = artistRepository.findById(createArtistResponse.getArtistId()).orElseThrow(()-> new ArtistNotFound());
        List<ArtWorks> lisOfArtworks = newListOfArtworks.getArtWorks();
        assertEquals(1,lisOfArtworks.size());
    }

}