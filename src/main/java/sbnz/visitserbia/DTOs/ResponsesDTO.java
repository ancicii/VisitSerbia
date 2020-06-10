package sbnz.visitserbia.DTOs;

import sbnz.visitserbia.model.Attraction;
import sbnz.visitserbia.model.AttractionType;
import sbnz.visitserbia.model.Budget;
import sbnz.visitserbia.model.PartOfSerbia;

import java.util.ArrayList;
import java.util.List;

public class ResponsesDTO {

    private List<AttractionType> attractionTypes;
    private double tripBudget;
    private PartOfSerbia partOfSerbia;
    private Integer numberOfDays;
    private Integer numberOfTravelers;

    public ResponsesDTO(List<AttractionType> attractionTypes, double tripBudget, PartOfSerbia partOfSerbia,
                        Integer numberOfDays, Integer numberOfTravelers) {
        this.attractionTypes = attractionTypes;
        this.tripBudget = tripBudget;
        this.partOfSerbia = partOfSerbia;
        this.numberOfDays = numberOfDays;
        this.numberOfTravelers = numberOfTravelers;
    }

    public ResponsesDTO() {
        this.attractionTypes = new ArrayList<>();
    }

    public List<AttractionType> getAttractionTypes() {
        return attractionTypes;
    }

    public Integer getNumberOfTravelers() {
        return numberOfTravelers;
    }

    public void setNumberOfTravelers(Integer numberOfTravelers) {
        this.numberOfTravelers = numberOfTravelers;
    }

    public void setAttractionTypes(List<AttractionType> attractionTypes) {
        this.attractionTypes = attractionTypes;
    }

    public double getTripBudget() {
        return tripBudget;
    }

    public void setTripBudget(double tripBudget) {
        this.tripBudget = tripBudget;
    }

    public PartOfSerbia getPartOfSerbia() {
        return partOfSerbia;
    }

    public void setPartOfSerbia(PartOfSerbia partOfSerbia) {
        this.partOfSerbia = partOfSerbia;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public void addAttractionType(AttractionType attractionType){
        if(!this.getAttractionTypes().contains(attractionType)) {
            this.getAttractionTypes().add(attractionType);
        }
    }

    public void removeAttractionType(AttractionType attractionType){
        this.getAttractionTypes().remove(attractionType);
    }
}
