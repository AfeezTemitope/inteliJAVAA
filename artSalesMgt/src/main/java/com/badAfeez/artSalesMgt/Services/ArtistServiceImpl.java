package com.badAfeez.artSalesMgt.Services;

import com.badAfeez.artSalesMgt.data.models.Artist;
import com.badAfeez.artSalesMgt.data.models.Artworks;
import com.badAfeez.artSalesMgt.data.repositories.ArtistRepository;
import com.badAfeez.artSalesMgt.data.repositories.ArtworksRepository;
import com.badAfeez.artSalesMgt.dto.request.ArtworkCreateRequest;
import com.badAfeez.artSalesMgt.dto.request.CreateArtist;
import com.badAfeez.artSalesMgt.dto.request.UpdateArtworkRequest;
import com.badAfeez.artSalesMgt.dto.response.UpdateArtworkResponse;
import com.badAfeez.artSalesMgt.exception.ArtistException;
import com.badAfeez.artSalesMgt.dto.response.CreateArtistResponse;
import com.badAfeez.artSalesMgt.exception.ArtworkException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService{
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private ArtworksRepository artworksRepository;



    @Override
    public CreateArtistResponse createArtist(CreateArtist createArtist) {
        if(isNullOrEmpty(createArtist.getUsername())) throw new ArtistException("username cannot be empty");
        if(isNullOrEmpty(createArtist.getName())) throw new ArtistException("name cannot be empty");
        if(isNullOrEmpty(createArtist.getGender())) throw new ArtistException("gender cannot be empty");
        if(isNullOrEmpty(createArtist.getAge())) throw new ArtistException("age cannot be empty");
        Optional<Artist> artist = artistRepository.findByUsername(createArtist.getUsername().toLowerCase());
        if (artist.isPresent()) throw new ArtistException("Artist already exists");
            Artist newArtist = new Artist();
            newArtist.setName(createArtist.getName().toLowerCase());
            newArtist.setAge(createArtist.getAge().toLowerCase());
            newArtist.setGender(createArtist.getGender().toLowerCase());
            newArtist.setUsername(createArtist.getUsername().toLowerCase());
            newArtist.setArtworks(newArtist.getArtworks());
            newArtist = artistRepository.save(newArtist);

            CreateArtistResponse createArtistResponse = new CreateArtistResponse();
            createArtistResponse.setId(newArtist.getId());
            createArtistResponse.setMessage("created.OK");
            return createArtistResponse;
    }

    @Override
    public Long addArtworksToArtist(Long artistId, ArtworkCreateRequest artworkCreateRequest) {
        Artist artist = artistRepository.findById(artistId).orElseThrow(() -> new ArtistException("Artist does not exist"));
        Artworks artworks = new Artworks();
        artworks.setArtist(artist);
        artworks.setTitle(artworkCreateRequest.getTitle());
        artworks.setDescription(artworkCreateRequest.getDescription());
        artworks.setPrice(artworkCreateRequest.getPrice());
        artworks.setQuantity(artworkCreateRequest.getQuantity());
        artworks.setImageUrl(artworkCreateRequest.getImageUrl());
        artworks = artworksRepository.save(artworks);
        artist.getArtworks().add(artworks);
        artistRepository.save(artist);
        return artistId;
    }

    @Override
    public UpdateArtworkResponse updateArtwork(Long artworkId, UpdateArtworkRequest updateArtworkRequest) {
        Artworks artworks = artworksRepository.findById(artworkId).orElseThrow(()-> new ArtworkException("Artwork does not exist"));
        artworks.setTitle(updateArtworkRequest.getTitle());
        artworks.setDescription(updateArtworkRequest.getDescription());
        artworks.setPrice(updateArtworkRequest.getPrice());
        artworks.setQuantity(updateArtworkRequest.getQuantity());
        artworks.setImageUrl(updateArtworkRequest.getImageUrl());
        artworks.setId(updateArtworkRequest.getId());
        artworks = artworksRepository.save(artworks);
        UpdateArtworkResponse updateArtworkResponse = new UpdateArtworkResponse();
        updateArtworkResponse.setMessage("updated.SUCCESSFULLY");
        return updateArtworkResponse;
    }

    @Override
    public void transferOwnershipOfArtwork(Long artworkId, Long artistId1) {
        Artworks artworks = artworksRepository.findById(artworkId).orElseThrow(() -> new ArtworkException("Artwork does not exist"));
        Artist newArtist = artistRepository.findById(artistId1).orElseThrow(() -> new ArtistException("Artist does not exist"));
        artworks.setArtist(newArtist);
        artworksRepository.save(artworks);
    }

    @Override
    public Artist findArtistById(Long artistId1) {
        Artist artist = artistRepository.findById(artistId1).orElseThrow(()-> new ArtistException("Artist does not exist"));
        return artist;

    }


//    @Override
//public void transferOwnershipOfArtwork(Long artworkId, Long artistId1) {
//    // Log the incoming parameters
//    System.out.println("Attempting to transfer artwork with ID: " + artworkId + " to artist with ID: " + artistId1);
//
//    // Retrieve the artwork
//    Artworks artworks = artworksRepository.findById(artworkId)
//            .orElseThrow(() -> {
//                System.err.println("Artwork with ID " + artworkId + " does not exist");
//                return new ArtworkException("Artwork does not exist");
//            });
//
//    // Retrieve the new artist
//    Artist newArtist = artistRepository.findById(artistId1)
//            .orElseThrow(() -> {
//                System.err.println("Artist with ID " + artistId1 + " does not exist");
//                return new ArtistException("Artist does not exist");
//            });
//
//    // Log the current state of the artwork and artist
//    System.out.println("Current artwork artist ID: " + (artworks.getArtist() != null ? artworks.getArtist().getId() : "none"));
//    System.out.println("New artist ID: " + newArtist.getId());
//
//    // Transfer the ownership
//    artworks.setArtist(newArtist);
//
//    // Save the updated artwork
//    artworks = artworksRepository.save(artworks);
//
//    // Log the result of the save operation
//    System.out.println("Artwork with ID " + artworkId + " successfully updated to new artist with ID " + newArtist.getId());
//}


    private static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
