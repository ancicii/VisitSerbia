package sbnz.visitserbia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbnz.visitserbia.DTOs.ParamsDTO;
import sbnz.visitserbia.exceptions.EntityNotValidException;
import sbnz.visitserbia.services.TripService;

import javax.validation.Valid;

@RestController
@RequestMapping("/trip")
@CrossOrigin
public class TripController {

    @Autowired
    private TripService tripService;

    @PostMapping()
    public ResponseEntity<Object> planTrip(@Valid @RequestBody ParamsDTO paramsDTO){
        try {
            return new ResponseEntity<>(tripService.planTrip(paramsDTO), HttpStatus.OK);
        } catch (EntityNotValidException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
