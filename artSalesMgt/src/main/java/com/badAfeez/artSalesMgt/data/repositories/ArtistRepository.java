package com.badAfeez.artSalesMgt.data.repositories;

import com.badAfeez.artSalesMgt.data.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Optional<Artist> findByUsername(String username);
}
