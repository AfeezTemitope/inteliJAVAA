package com.badAfeez.code.services.artworks;

import com.badAfeez.code.data.models.ArtWorks;
import com.badAfeez.code.dtoBby.request.CreateArtworkRequest;
import com.badAfeez.code.dtoBby.request.DeleteArtworkRequest;

import java.util.List;


public interface ArtWorkService {
    ArtWorks createOrUpdateArtworks(CreateArtworkRequest artworkRequest);

    List<ArtWorks> getArtworksByArtist(String artistId);

    void deleteArtWork(String artworkId);
}
