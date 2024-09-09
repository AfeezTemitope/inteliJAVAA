package com.badAfeez.artSalesMgt.Services;

import com.badAfeez.artSalesMgt.data.models.Artworks;

import java.util.List;

public interface ArtworkService {
    Artworks findByTitleAndArtistId(String title, Long artistId);

    List<Artworks> findArtworksByArtistId(Long artistId);


    void deleteArtwork(Long id);

    Artworks findArtworkById(Long artworkId);
}
