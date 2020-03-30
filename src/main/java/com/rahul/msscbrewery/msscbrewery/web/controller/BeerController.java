package com.rahul.msscbrewery.msscbrewery.web.controller;

import com.rahul.msscbrewery.msscbrewery.services.BeerService;
import com.rahul.msscbrewery.msscbrewery.web.model.BeerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
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
    public ResponseEntity handlePost(@Valid @RequestBody BeerDto beerDto){
        // TODO add hostname in the header's location key
        BeerDto newBeer = beerService.saveNewBeer(beerDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/beer/api/v1/beer/" + newBeer.getId().toString());
        return new ResponseEntity(newBeer, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/beers/{beerId}")
    public ResponseEntity handlePut(@PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDto beerDto) {
        beerService.updateBeer(beerId, beerDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping ("/beers/{beerId}")
    public ResponseEntity handleDelete(@PathVariable("beerId") UUID beerId) {
        beerService.deleteById(beerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> validationErrorHandler(ConstraintViolationException e) {
        List<String> errors = new ArrayList<>(e.getConstraintViolations().size());
        e.getConstraintViolations().forEach( constraintViolation -> {
            errors.add(constraintViolation.getPropertyPath() + " : " + constraintViolation.getMessage() + " : "
                    + constraintViolation.getInvalidValue());
        });
        return new ResponseEntity<List>(errors, HttpStatus.BAD_REQUEST);
    }
}
