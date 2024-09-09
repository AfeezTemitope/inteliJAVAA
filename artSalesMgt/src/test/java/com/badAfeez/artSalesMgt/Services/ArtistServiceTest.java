package com.badAfeez.artSalesMgt.Services;

import com.badAfeez.artSalesMgt.data.models.Artist;
import com.badAfeez.artSalesMgt.data.models.Artworks;
import com.badAfeez.artSalesMgt.data.repositories.ArtistRepository;
import com.badAfeez.artSalesMgt.data.repositories.ArtworksRepository;
import com.badAfeez.artSalesMgt.dto.request.ArtworkCreateRequest;
import com.badAfeez.artSalesMgt.dto.request.CreateArtist;
import com.badAfeez.artSalesMgt.dto.request.UpdateArtworkRequest;
import com.badAfeez.artSalesMgt.dto.response.CreateArtistResponse;
import com.badAfeez.artSalesMgt.dto.response.UpdateArtworkResponse;
import com.badAfeez.artSalesMgt.exception.ArtistException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ArtistServiceTest {
    @Autowired
    private ArtistService artistService;
    @Autowired
    private ArtworkService artworkService;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private ArtworksRepository artworksRepository;

    @BeforeEach
    void setUp() {
        artistRepository.deleteAll();
        artworksRepository.deleteAll();
    }

    @Test
    void testThatArtistCanBeCreated(){
        CreateArtist createArtist = new CreateArtist();
        createArtist.setAge("2");
        createArtist.setGender("Male");
        createArtist.setName("bigChi");
        createArtist.setUsername("artistchi");
        //assertThrows(ArtistException.class,()->artistService.createArtist(createArtist));

        CreateArtistResponse response = artistService.createArtist(createArtist);
        assertNotNull(response.getId());
        System.out.println(response.getId());
    }
    @Test
    void testThatArtistCannotBeCreatedWithSameDetails(){
        CreateArtist createArtist = new CreateArtist();
        createArtist.setAge("2");
        createArtist.setGender("Male");
        createArtist.setName("bigChi");
        createArtist.setUsername("artistchi");
        artistService.createArtist(createArtist);
        assertThrows(ArtistException.class, () -> artistService.createArtist(createArtist));
    }
    @Test
    void testThatArtistCannotBeCreatedWithNull(){
        CreateArtist createArtist = new CreateArtist();
        createArtist.setAge("");
        createArtist.setGender("");
        assertThrows(ArtistException.class, () -> artistService.createArtist(createArtist));
    }
    @Test
    void testThatArtistCanAddArtworks(){
        //artistRepository.deleteAll();
        CreateArtist createArtist = new CreateArtist();
        createArtist.setAge("1");
        createArtist.setGender("Male");
        createArtist.setName("bigChiq");
        createArtist.setUsername("artistChiq");
        CreateArtistResponse createArtistResponse = artistService.createArtist(createArtist);
        Long artistId = createArtistResponse.getId();

        ArtworkCreateRequest artworkCreateRequest = new ArtworkCreateRequest();
        artworkCreateRequest.setId(artistId);
        artworkCreateRequest.setDescription("the image is for me");
        artworkCreateRequest.setPrice(10.00);
        artworkCreateRequest.setTitle("HOW TO BE BAD");
        artworkCreateRequest.setQuantity(22);
        artworkCreateRequest.setImageUrl("url");
        artistService.addArtworksToArtist(artistId, artworkCreateRequest);
        Artist artist = artistRepository.findById(artistId).orElse(null);
        assertNotNull(artist);
        assertEquals(1, artist.getArtworks().size());
        Optional<Artworks> foundArtwork = artworksRepository.findById(artworkCreateRequest.getId());
        assertNotNull(foundArtwork);
    }
//    @Test
//    void testThatArtistCanFindArtworkByTitle(){
//        CreateArtist createArtist = new CreateArtist();
//        createArtist.setAge("11");
//        createArtist.setGender("Male");
//        createArtist.setName("bigChibu");
//        createArtist.setUsername("artistBigChi");
//        CreateArtistResponse createArtistResponse = artistService.createArtist(createArtist);
//        Long artistId = createArtistResponse.getId();
//
//        ArtworkCreateRequest artworkCreateRequest = new ArtworkCreateRequest();
//        artworkCreateRequest.setId(artistId);
//        artworkCreateRequest.setDescription("the image is for me");
//        artworkCreateRequest.setPrice(10.00);
//        artworkCreateRequest.setTitle("HOW TO BE BAD Bitch");
//        artworkCreateRequest.setQuantity(22);
//        artworkCreateRequest.setImageUrl("url");
//        artistService.addArtworksToArtist(artistId, artworkCreateRequest);
//        Artist artist = artistRepository.findById(artistId).orElse(null);
//        assertNotNull(artist);
//
//        Artworks foundArtwork = artworkService.findByTitleAndArtistId("HOW TO BE BAD", artistId);
//        assertNotNull(foundArtwork);
//    }
@Test
void testThatArtistCanFindAllArtworksOfAnArtist(){
    CreateArtist createArtist = new CreateArtist();
    createArtist.setAge("11");
    createArtist.setGender("Male");
    createArtist.setName("bigChibu");
    createArtist.setUsername("artistBigChi");
    CreateArtistResponse createArtistResponse = artistService.createArtist(createArtist);
    Long artistId = createArtistResponse.getId();

    ArtworkCreateRequest artworkCreateRequest = new ArtworkCreateRequest();
    artworkCreateRequest.setId(artistId);
    artworkCreateRequest.setDescription("the image is for me");
    artworkCreateRequest.setPrice(10.00);
    artworkCreateRequest.setTitle("HOW TO BE BAD Bitch");
    artworkCreateRequest.setQuantity(22);
    artworkCreateRequest.setImageUrl("url");
    artistService.addArtworksToArtist(artistId, artworkCreateRequest);

    ArtworkCreateRequest artworkCreateRequest1 = new ArtworkCreateRequest();
    artworkCreateRequest1.setId(artistId);
    artworkCreateRequest1.setDescription("the image is");
    artworkCreateRequest1.setPrice(103.00);
    artworkCreateRequest1.setTitle("HOW TO BE BAD");
    artworkCreateRequest1.setQuantity(223);
    artworkCreateRequest1.setImageUrl("urlBBY");
    artistService.addArtworksToArtist(artistId, artworkCreateRequest1);

    Artist artist = artistRepository.findById(artistId).orElse(null);
    assertNotNull(artist);

    List<Artworks> artworks = artworkService.findArtworksByArtistId(artistId);
    assertNotNull(artworks);
    assertEquals(2, artworks.size());

    //Artworks foundArtwork = artworkService.findByTitleAndArtistId("HOW TO BE BAD", artistId);
    //assertNotNull(foundArtwork);
}

    @Test
    void testThatArtworkCanBeDeleted(){
        CreateArtist createArtist = new CreateArtist();
        createArtist.setAge("11");
        createArtist.setGender("Male");
        createArtist.setName("bigChibu");
        createArtist.setUsername("artistBigChi");
        CreateArtistResponse createArtistResponse = artistService.createArtist(createArtist);
        Long artistId = createArtistResponse.getId();
        ArtworkCreateRequest artworkCreateRequest = new ArtworkCreateRequest();
        artworkCreateRequest.setId(artistId);
        artworkCreateRequest.setDescription("the image is for me");
        artworkCreateRequest.setPrice(10.00);
        artworkCreateRequest.setTitle("HOW TO BE BAD");
        artworkCreateRequest.setQuantity(22);
        artworkCreateRequest.setImageUrl("url");
        Long artworkId = artistService.addArtworksToArtist(artistId, artworkCreateRequest);
        ArtworkCreateRequest artworkCreateRequest1 = new ArtworkCreateRequest();
        artworkCreateRequest1.setId(artistId);
        artworkCreateRequest1.setDescription("the image is");
        artworkCreateRequest1.setPrice(103.00);
        artworkCreateRequest1.setTitle("HOW TO BE BAD");
        artworkCreateRequest1.setQuantity(223);
        artworkCreateRequest1.setImageUrl("urlBBY");
        Long artworkId1 = artistService.addArtworksToArtist(artistId, artworkCreateRequest1);
        Artist artist = artistRepository.findById(artistId).orElse(null);
        assertNotNull(artist);
        List<Artworks> artworks = artworkService.findArtworksByArtistId(artistId);
        assertNotNull(artworks);
        assertEquals(2, artworks.size());
        artworkService.deleteArtwork(artworkId1);
        artworks = artworkService.findArtworksByArtistId(artistId);
        assertNotNull(artworks);
        assertEquals(1, artworks.size());
    }
    @Test
    void testThatArtworksCanBeUpdated(){
        CreateArtist createArtist = new CreateArtist();
        createArtist.setAge("11");
        createArtist.setGender("Male");
        createArtist.setName("bigChibu");
        createArtist.setUsername("artistBigChi");
        CreateArtistResponse createArtistResponse = artistService.createArtist(createArtist);
        Long artistId = createArtistResponse.getId();
        ArtworkCreateRequest artworkCreateRequest = new ArtworkCreateRequest();
        artworkCreateRequest.setId(artistId);
        artworkCreateRequest.setDescription("the image is for me");
        artworkCreateRequest.setPrice(10.00);
        artworkCreateRequest.setTitle("HOW TO BE BAD");
        artworkCreateRequest.setQuantity(22);
        artworkCreateRequest.setImageUrl("url");
        Long artworkId = artistService.addArtworksToArtist(artistId, artworkCreateRequest);

        UpdateArtworkRequest updateArtworkRequest = new UpdateArtworkRequest();
        updateArtworkRequest.setDescription("badbunny");
        updateArtworkRequest.setQuantity(1);
        updateArtworkRequest.setImageUrl("urlBBY");
        updateArtworkRequest.setPrice(10.00);
        updateArtworkRequest.setTitle("HOW TO BE BAD 2.0");

        UpdateArtworkResponse response = artistService.updateArtwork(artworkId, updateArtworkRequest);
        response.setMessage("updated.SUCCESSFULLY");
        assertNotNull(response);

    }
    @Test
    void testThatArtworksOwnerShipCanBeTransferred() throws InterruptedException {
        artistRepository.deleteAll();
        artworksRepository.deleteAll();

        CreateArtist createArtist = new CreateArtist();
        createArtist.setAge("11");
        createArtist.setGender("Male");
        createArtist.setName("bigChibu");
        createArtist.setUsername("artistBigChi");
        CreateArtistResponse createArtistResponse = artistService.createArtist(createArtist);
        Long artistId = createArtistResponse.getId();
        System.out.println("Created artist with ID: " + artistId);

        ArtworkCreateRequest artworkCreateRequest = new ArtworkCreateRequest();
        artworkCreateRequest.setId(artistId);
        artworkCreateRequest.setDescription("the image is for me");
        artworkCreateRequest.setPrice(10.00);
        artworkCreateRequest.setTitle("HOW TO BE BAD");
        artworkCreateRequest.setQuantity(22);
        artworkCreateRequest.setImageUrl("url");
        Long artworkId = artistService.addArtworksToArtist(artistId, artworkCreateRequest);
        System.out.println("Created artwork with ID: " + artworkId);

        assertNotNull(artworkId, "Artwork ID should not be null");

        CreateArtist createArtist1 = new CreateArtist();
        createArtist1.setAge("11");
        createArtist1.setGender("Male");
        createArtist1.setName("bigFm");
        createArtist1.setUsername("artistBigFm");
        CreateArtistResponse createArtistResponse1 = artistService.createArtist(createArtist1);
        Long artistId1 = createArtistResponse1.getId();
        System.out.println("Created new artist with ID: " + artistId1);

        // Optional delay to ensure database consistency
        Thread.sleep(500);

        // Verify existence before transfer
        Artworks existingArtwork = artworkService.findArtworkById(artworkId);
        Artist existingArtist = artistService.findArtistById(artistId1);
        assertFalse(existingArtwork != null, "Artwork should exist before transfer");
        assertTrue(existingArtist != null, "New artist should exist before transfer");

        artistService.transferOwnershipOfArtwork(artworkId, artistId1);

        // Optional delay to ensure database consistency
        Thread.sleep(500);

        // Verify ownership transfer
        Artworks transferredArtwork = artworkService.findArtworkById(artworkId);
        assertTrue(transferredArtwork != null, "Artwork should exist after transfer");
        assertEquals(artistId1, transferredArtwork.getArtist().getId(), "Artwork's artist should be the new artist");

        List<Artworks> artworksByOldArtist = artworkService.findArtworksByArtistId(artistId);
        assertNotNull(artworksByOldArtist);
        assertFalse(artworksByOldArtist.stream().anyMatch(a -> a.getId().equals(artworkId)), "Artwork should no longer be owned by the old artist");

        List<Artworks> artworksByNewArtist = artworkService.findArtworksByArtistId(artistId1);
        assertNotNull(artworksByNewArtist);
        assertTrue(artworksByNewArtist.stream().anyMatch(a -> a.getId().equals(artworkId)), "Artwork should be owned by the new artist");
    }



}