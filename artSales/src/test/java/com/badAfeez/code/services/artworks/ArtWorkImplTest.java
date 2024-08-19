package com.badAfeez.code.services.artworks;

import com.badAfeez.code.Exception.ArtistNotFound;
import com.badAfeez.code.data.models.ArtWorks;
import com.badAfeez.code.data.models.Artist;
import com.badAfeez.code.data.repository.ArtWorkRepository;
import com.badAfeez.code.data.repository.ArtistRepository;
import com.badAfeez.code.dtoBby.request.CreateArtistRequest;
import com.badAfeez.code.dtoBby.request.CreateArtworkRequest;
import com.badAfeez.code.dtoBby.request.DeleteArtworkRequest;
import com.badAfeez.code.dtoBby.response.CreateArtistResponse;
import com.badAfeez.code.services.artist.ArtistServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ArtWorkImplTest {

    @Autowired
    private ArtWorkRepository artWorkRepository;
    @Autowired
    private ArtWorkService artWorkService;
    @Autowired
    private ArtistServices artistServices;
    @Autowired
    private ArtistRepository artistRepository;

    @BeforeEach
    void clearDatabase() {
        artWorkRepository.deleteAll();
    }


    @Test
    void testThatArtistCanAddArtworksAndSeeAllArtworks() {
        CreateArtistRequest createArtistRequest = new CreateArtistRequest();
        createArtistRequest.setName("artistKolo");
        createArtistRequest.setAge("12");
        createArtistRequest.setGender("M");
        createArtistRequest.setPhoneNumber("1234");
        createArtistRequest.setUserName("bigKala");

        CreateArtistResponse createArtistResponse = artistServices.createArtist(createArtistRequest);
        assertThat(createArtistResponse).isNotNull();
        String artistId = createArtistResponse.getArtistId();

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
        artworkRequest1.setAvailableQuantity(5);
        artworkRequest1.setArtistId(artistId);

        artWorkService.createOrUpdateArtworks(artworkRequest);
        artWorkService.createOrUpdateArtworks(artworkRequest1);


        Artist artist = artistRepository.findById(artistId).orElseThrow(() -> new ArtistNotFound());
        List<ArtWorks> artWorks = artist.getArtWorks();


        assertThat(artist).isNotNull();
        assertThat(artWorks).isNotNull();
        assertEquals(2, artWorks.size());

        System.out.println("Number of artworks: " + artWorks.size());
    }
    @Test
    void testThatArtistCanFindAndDeleteArtworks() {

        CreateArtistRequest createArtistRequest = new CreateArtistRequest();
        createArtistRequest.setName("artistKolo");
        createArtistRequest.setAge("12");
        createArtistRequest.setGender("M");
        createArtistRequest.setPhoneNumber("1234");
        createArtistRequest.setUserName("bigKala");

        CreateArtistResponse createArtistResponse = artistServices.createArtist(createArtistRequest);
        assertThat(createArtistResponse).isNotNull();
        String artistId = createArtistResponse.getArtistId();

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
        artworkRequest1.setAvailableQuantity(5);
        artworkRequest1.setArtistId(artistId);

        ArtWorks createdArtwork1 = artWorkService.createOrUpdateArtworks(artworkRequest);
        ArtWorks createdArtwork2 = artWorkService.createOrUpdateArtworks(artworkRequest1);

        assertThat(createdArtwork1).isNotNull();
        assertThat(createdArtwork2).isNotNull();

        artWorkService.deleteArtWork(createdArtwork1.getId());
        artWorkService.deleteArtWork(createdArtwork2.getId());

        ArtWorks deletedArtwork1 = artWorkRepository.findById(createdArtwork1.getId()).orElse(null);
        ArtWorks deletedArtwork2 = artWorkRepository.findById(createdArtwork2.getId()).orElse(null);

        assertThat(deletedArtwork1).isNull();
        assertThat(deletedArtwork2).isNull();

        Artist artist = artistRepository.findById(artistId).orElseThrow(() -> new ArtistNotFound());

        List<ArtWorks> artWorks = artist.getArtWorks();
        assertThat(artWorks).isNotNull();
        assertTrue(artWorks.isEmpty());

        System.out.println("Number of artworks after deletion: " + artWorks.size());
    }


//    void testThatArtWorkCanBeCreated() {
//        CreateArtistRequest createArtistRequest = new CreateArtistRequest();
//        createArtistRequest.setUserName("artistKolo");
//        createArtistRequest.setGender("M");
//        createArtistRequest.setName("badAfeez");
//        createArtistRequest.setAge("26");
//        createArtistRequest.setPhoneNumber("5678");
//        CreateArtistResponse createArtistResponse = artistServices.createArtist(createArtistRequest);
//
//        CreateArtworkRequest request = new CreateArtworkRequest();
//        request.setTitle("Bad Afeez");
//        request.setDescription("Bad Afeez");
//        request.setPrice(200.00);
//        request.setImageUrl("www.badAfeez.com");
//        request.setArtWorkId(createArtistResponse.getArtistId());
//
//        ArtWorks artWorks = artWorkService.createOrUpdateArtworks(request);
//        assertNotNull(artWorks);
//        assertNotNull(artWorks.getId());


//        CreateArtworkRequest request = new CreateArtworkRequest();
//        request.setTitle("Bad Afeez");
//        request.setDescription("Bad Afeez");
//        request.setPrice(200.00);
//        request.setImageUrl("www.badAfeez.com");
//        request.setArtWorkId(createArtistResponse.getArtistId());
//
//        ArtWorks artWorks = artWorkService.createOrUpdateArtworks(request);
//        assertNotNull(artWorks);
//        assertNotNull(artWorks.getId());


    }
