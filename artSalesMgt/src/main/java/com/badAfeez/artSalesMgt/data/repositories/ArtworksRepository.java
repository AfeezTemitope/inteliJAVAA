package com.badAfeez.artSalesMgt.data.repositories;

import com.badAfeez.artSalesMgt.data.models.Artworks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArtworksRepository extends JpaRepository<Artworks, Long> {
    Optional<Artworks> findByTitleAndArtistId(String title, Long artistId);
    List<Artworks> findByArtistId(Long artistId);
//    Optional<Artworks> findById(Long artworkId);
}
