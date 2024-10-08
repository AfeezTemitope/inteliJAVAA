package com.badAfeez.code.controller;

import com.badAfeez.code.data.models.ArtWorks;
import com.badAfeez.code.dtoBby.request.CreateArtworkRequest;
import com.badAfeez.code.dtoBby.request.CreatePurchaseRequest;
import com.badAfeez.code.dtoBby.response.CreatePurchaseResponse;
import com.badAfeez.code.services.Order.OrderService;
import com.badAfeez.code.services.artworks.ArtWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artworks")
public class ArtWorkController {
    @Autowired
    private ArtWorkService artWorkService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/createOrUpdate")
    public ResponseEntity<?> createOrUpdateArtWork(@RequestBody CreateArtworkRequest createArtworkRequest) {
        try {
            ArtWorks artWorks = artWorkService.createOrUpdateArtworks(createArtworkRequest);
            return new ResponseEntity<>(artWorks, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/browseArtworks/{artistId}")
    public ResponseEntity<?> getArtWoksByArtist(@PathVariable String artistId) {
        try {
            List<ArtWorks> artWorksList = artWorkService.getArtworksByArtist(artistId);
            if (artWorksList.size() > 0) {
                return new ResponseEntity<>(artWorksList, HttpStatus.OK);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteArtwork/{artworkId}")
    public ResponseEntity<?> deleteArtwork(@PathVariable String artworkId) {
        try {
            artWorkService.deleteArtWork(artworkId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
//    @PostMapping("/purchase")
//    public ResponseEntity<?> purchaseArtwork(@RequestBody CreatePurchaseRequest createPurchaseRequest){
//        try {
//            CreatePurchaseResponse createPurchaseResponse = orderService.processedOrder(createPurchaseRequest);
//            if("Order purchase successful".equals(createPurchaseResponse.getMessage())){
//                ArtWorks updateArtworks = artWorkService.createOrUpdateArtworks(createPurchaseResponse);
//                return new ResponseEntity<>(updateArtworks, HttpStatus.CREATED);
//            }
//            return new ResponseEntity<>(createPurchaseResponse.getMessage(), HttpStatus.BAD_REQUEST);
//        } catch (Exception e){
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
//}
//@PostMapping("/purchase")
//public ResponseEntity<?> purchaseArtwork(@RequestBody CreatePurchaseRequest createPurchaseRequest) {
//    try {
//           CreatePurchaseResponse createPurchaseResponse = orderService.processedOrder(createPurchaseRequest);
//        if ("Order purchase successful".equals(createPurchaseResponse.getMessage())) {
//            List<ArtWorks> artwork = artWorkService.getArtworksByArtist(createPurchaseRequest.getArtworkId());
//            if (artwork == null) {
//                return new ResponseEntity<>("Artwork not found", HttpStatus.NOT_FOUND);
//            }
//            int newQuantity = artwork.getLast().getAvailableQuantity() - createPurchaseRequest.getQuantity();
//            if (newQuantity < 0) {
//                return new ResponseEntity<>("Insufficient quantity available", HttpStatus.BAD_REQUEST);
//            }
//
////            artwork.set(newQuantity,);
//            ArtWorks updatedArtWork = artWorkService.createOrUpdateArtworks(
//                    new CreateArtworkRequest(
//                            artwork.getId(),
//                            artwork.getTitle(),
//                            artwork.getDescription(),
//                            artwork.getPrice(),
//                            artwork.getImageUrl(),
//                            newQuantity,
//                            artwork.getArtistId()
//                    )
//            );
//            return new ResponseEntity<>(updatedArtWork, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(createPurchaseResponse.getMessage(), HttpStatus.BAD_REQUEST);
//    } catch (Exception e) {
//        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//    }
