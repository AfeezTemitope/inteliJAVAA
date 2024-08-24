package com.badAfeez.code.services.artworks;

import com.badAfeez.code.Exception.ArtWorkNotFound;
import com.badAfeez.code.Exception.ArtistNotFound;
import com.badAfeez.code.data.models.ArtWorks;
import com.badAfeez.code.data.models.Artist;
import com.badAfeez.code.data.repository.ArtWorkRepository;
import com.badAfeez.code.data.repository.ArtistRepository;
import com.badAfeez.code.dtoBby.request.CreateArtworkRequest;
import com.badAfeez.code.dtoBby.request.DeleteArtworkRequest;
import com.badAfeez.code.services.artist.ArtistServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtWorkImpl implements ArtWorkService {
    @Autowired
    private ArtWorkRepository artWorkRepository;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private ArtistServices artistServices;

    @Override
    public ArtWorks createOrUpdateArtworks(CreateArtworkRequest artworkRequest) {
        Artist artist = artistRepository.findById(artworkRequest.getArtistId())
                .orElseThrow(() -> new ArtistNotFound());

        ArtWorks artWork;

        if (artworkRequest.getArtWorkId() != null) {
            artWork = artWorkRepository.findById(artworkRequest.getArtWorkId())
                    .orElseThrow(() -> new ArtWorkNotFound());

            artWork.setTitle(artworkRequest.getTitle());
            artWork.setDescription(artworkRequest.getDescription());
            artWork.setPrice(artworkRequest.getPrice());
            artWork.setImageUrl(artworkRequest.getImageUrl());
        } else {

            artWork = new ArtWorks();
            artWork.setTitle(artworkRequest.getTitle());
            artWork.setDescription(artworkRequest.getDescription());
            artWork.setPrice(artworkRequest.getPrice());
            artWork.setImageUrl(artworkRequest.getImageUrl());
            artWork.setArtist(artist);
            artWork.setAvailableQuantity(artworkRequest.getAvailableQuantity());
        }

        artWork = artWorkRepository.save(artWork);

        if (artworkRequest.getArtWorkId() == null) {
            if (artist.getArtWorks() == null) {
                artist.setArtWorks(new ArrayList<>());
            }
            artist.getArtWorks().add(artWork);
            artistRepository.save(artist);
        }

        return artWork;
    }

    @Override
    public void deleteArtWork(String artworkId) {
        ArtWorks artWork = artWorkRepository.findById(artworkId).orElseThrow(() -> new ArtWorkNotFound());
        Artist artist = artWork.getArtist();
        if (artist != null) {
            artist.getArtWorks().remove(artWork);
            artistRepository.save(artist);
        }

        artWorkRepository.deleteById(artworkId);

    }
    @Override
    public List<ArtWorks> getArtworksByArtist(String artistId) {
        return artWorkRepository.findByArtistId(artistId);
    }



//    @Override
//    public ArtWorks createOrUpdateArtworks(CreateArtworkRequest artworkRequest) {
//        Artist artist = artistRepository.findById(artworkRequest.getArtistId()).orElseThrow(() -> new ArtistNotFound());
//        ArtWorks artWork;
//        if (artworkRequest.getArtistId() != null) {
//            artWork = artWorkRepository.findById(artworkRequest.getArtWorkId()).orElseThrow(() -> new ArtistNotFound());
//            artWork.setImageUrl(artWork.getImageUrl());
//            artWork.setTitle(artworkRequest.getTitle());
//            artWork.setDescription(artworkRequest.getDescription());
//            artWork.setPrice(artworkRequest.getPrice());
//        }else {
//            artWork = new ArtWorks();
//            artWork.setImageUrl(artWork.getImageUrl());
//            artWork.setTitle(artworkRequest.getTitle());
//            artWork.setDescription(artworkRequest.getDescription());
//            artWork.setPrice(artworkRequest.getPrice());
//            artWork.setArtist(artist);
//        }
//            artWorkRepository.save(artWork);
//        if (artworkRequest.getArtistId() == null) {
//            if (artist.getArtWorks() == null) {
//                artist.setArtWorks(new ArrayList<>());
//            }
//            artist.getArtWorks().add(artWork);
//            artistRepository.save(artist);
//        }
//            return artWork;
//    }



}