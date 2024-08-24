package com.badAfeez.code.services.artist;


import com.badAfeez.code.data.models.ArtWorks;
import com.badAfeez.code.data.models.Artist;
import com.badAfeez.code.dtoBby.request.CreateArtistRequest;
import com.badAfeez.code.dtoBby.response.CreateArtistResponse;

import java.util.List;

public interface ArtistServices {
    Long countNumberOfArtworks();

    CreateArtistResponse createArtist(CreateArtistRequest createArtistRequest);

    Artist findArtistById(String id);
    List<ArtWorks> getArtwoksByArtist(String artistId, String customerId);

}
