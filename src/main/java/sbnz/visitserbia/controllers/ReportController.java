package sbnz.visitserbia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sbnz.visitserbia.exceptions.EntityNotFoundException;
import sbnz.visitserbia.services.ReportService;

@RestController
@RequestMapping("/reports")
@CrossOrigin
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping(value = "/leastPopular")
    public ResponseEntity<Object> getLeastPopularAttractions() {
        try {
            return new ResponseEntity<>(reportService.getLeastPopularAttractions(), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/mediumPopular")
    public ResponseEntity<Object> getMediumPopularAttractions() {
        try {
            return new ResponseEntity<>(reportService.getMediumPopularAttractions(), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/mostPopular")
    public ResponseEntity<Object> getMostPopularAttractions() {
        try {
            return new ResponseEntity<>(reportService.getMostPopularAttractions(), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
