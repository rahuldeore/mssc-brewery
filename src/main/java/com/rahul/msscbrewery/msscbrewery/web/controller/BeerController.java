package com.rahul.msscbrewery.msscbrewery.web.controller;

import com.rahul.msscbrewery.msscbrewery.services.BeerService;
import com.rahul.msscbrewery.msscbrewery.web.model.BeerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Created by Rahul on 12/14/19
 */
@RequestMapping("/beer/api/v1")
@RestController
public class BeerController {

    private BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/beers/{beerId}")
    public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId){
        return new ResponseEntity(beerService.getBeerById(beerId), HttpStatus.OK);
    }
    @PostMapping("/beer")
    public ResponseEntity handlePost(@RequestBody BeerDto beerDto){
        // TODO add hostname in the header's location key
        BeerDto newBeer = beerService.saveNewBeer(beerDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/beer/api/v1/beer/" + newBeer.getId().toString());
        return new ResponseEntity(newBeer, httpHeaders, HttpStatus.CREATED);
    }
    //Test after GPG setup
}
