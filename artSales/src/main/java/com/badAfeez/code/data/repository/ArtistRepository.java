package com.badAfeez.code.data.repository;

import com.badAfeez.code.data.models.Artist;
import com.badAfeez.code.dtoBby.request.CreateArtistRequest;
import com.badAfeez.code.dtoBby.response.CreateArtistResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ArtistRepository extends MongoRepository<Artist, String> {

    Optional <Artist> existsByUserName(String userName);
}
