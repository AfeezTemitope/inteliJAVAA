package com.badAfeez.code.services.artist;

import com.badAfeez.code.data.models.ArtWorks;
import com.badAfeez.code.data.models.Artist;
import com.badAfeez.code.data.models.Customer;
import com.badAfeez.code.data.repository.ArtWorkRepository;
import com.badAfeez.code.data.repository.ArtistRepository;
import com.badAfeez.code.data.repository.CustomerRepository;
import com.badAfeez.code.dtoBby.request.CreateArtistRequest;
import com.badAfeez.code.dtoBby.response.CreateArtistResponse;
import com.badAfeez.code.Exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.badAfeez.code.Exception.ArtistNotLoggedIn;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistServices{

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private ArtWorkRepository artWorkRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Long countNumberOfArtworks() {
        return artistRepository.count();
    }

    @Override
    public CreateArtistResponse createArtist(CreateArtistRequest createArtistRequest) {
        artistRepository.existsByUserName(createArtistRequest.getUserName()).orElseThrow(()-> new ArtistNotFound());
        Artist artist = new Artist();
        artist.setName(createArtistRequest.getName());
        artist.setAge(createArtistRequest.getAge());
        artist.setGender(createArtistRequest.getGender());
        artist.setPhoneNumber(createArtistRequest.getPhoneNumber());
        artist.setUserName(createArtistRequest.getUserName());
        Artist savedArtist = artistRepository.save(artist);

        CreateArtistResponse createArtistResponse = new CreateArtistResponse();
        createArtistResponse.setMessage("created.SUCCESSFUL and your username is: " + artist.getUserName());
        createArtistResponse.setArtistId(savedArtist.getId());
        return createArtistResponse;
    }

    @Override
    public Artist findArtistById(String id) {
        Artist artist = artistRepository.findById(id).orElseThrow(()-> new ArtistNotFound());
        return artist;
    }

    @Override
    public List<ArtWorks> getArtwoksByArtist(String artistId, String customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new CustomerNotFound());
        Artist artist = artistRepository.findById(artistId).orElseThrow(()-> new ArtistNotFound());
        return artist.getArtWorks();
    }


}
