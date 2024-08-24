package com.badAfeez.code.data.repository;

import com.badAfeez.code.data.models.ArtWorks;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ArtWorkRepository extends MongoRepository <ArtWorks, String> {
    List<ArtWorks> findByArtistId(String artistId);
}
