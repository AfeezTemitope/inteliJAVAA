package com.badAfeez.artSalesMgt.Services;

import com.badAfeez.artSalesMgt.data.models.Artist;
import com.badAfeez.artSalesMgt.dto.request.ArtworkCreateRequest;
import com.badAfeez.artSalesMgt.dto.request.CreateArtist;
import com.badAfeez.artSalesMgt.dto.request.UpdateArtworkRequest;
import com.badAfeez.artSalesMgt.dto.response.CreateArtistResponse;
import com.badAfeez.artSalesMgt.dto.response.UpdateArtworkResponse;

import java.util.Optional;

public interface ArtistService {
    CreateArtistResponse createArtist(CreateArtist createArtist);

    Long addArtworksToArtist(Long artistId, ArtworkCreateRequest artworkCreateRequest);

    UpdateArtworkResponse updateArtwork(Long artworkId, UpdateArtworkRequest updateArtworkRequest);

    void transferOwnershipOfArtwork(Long artworkId, Long artistId1);

    Artist findArtistById(Long artistId1);
}
