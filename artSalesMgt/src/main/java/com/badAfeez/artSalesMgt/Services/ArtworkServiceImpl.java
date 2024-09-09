package com.badAfeez.artSalesMgt.Services;

import com.badAfeez.artSalesMgt.data.models.Artworks;
import com.badAfeez.artSalesMgt.data.repositories.ArtworksRepository;
import com.badAfeez.artSalesMgt.exception.ArtistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.badAfeez.artSalesMgt.exception.ArtworkException;

import java.util.List;

@Service
public class ArtworkServiceImpl implements ArtworkService{
    @Autowired
    private ArtworksRepository artworksRepository;


    @Override
    public Artworks findByTitleAndArtistId(String title, Long artistId) {
        if (title == null || title.trim().isEmpty()) throw new IllegalArgumentException("title must not be null or empty");
        if (artistId == null) throw new ArtistException("artistId must not be null");
        return artworksRepository.findByTitleAndArtistId(title, artistId).orElseThrow(()->new ArtworkException("Artwork not found"));
    }
    @Override
    public List<Artworks> findArtworksByArtistId(Long artistId) {
        if (artistId == null ) throw new ArtistException("artistId must not be null");
        return artworksRepository.findByArtistId(artistId);
    }

    @Override
    public void deleteArtwork(Long id) {
        artworksRepository.deleteById(id);
    }

    @Override
    public Artworks findArtworkById(Long artworkId) {
        if (artworkId == null ) throw new ArtworkException("artworkId must not be null");
        return artworksRepository.findById(artworkId).orElseThrow(()->new ArtworkException("Artwork not found"));
    }


}
