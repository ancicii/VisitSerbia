package sbnz.visitserbia.DTOs;

import sbnz.visitserbia.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResponsesDTO {

    private List<AttractionType> attractionTypes;
    private Double tripBudget;
    private PartOfSerbia partOfSerbia;
    private Integer numberOfDays;
    private Integer numberOfTravelers;
    private Date startDate;
    private Date endDate;

    public ResponsesDTO(List<AttractionType> attractionTypes, Double tripBudget, PartOfSerbia partOfSerbia, Integer numberOfDays, Integer numberOfTravelers, Date startDate, Date endDate) {
        this.attractionTypes = attractionTypes;
        this.tripBudget = tripBudget;
        this.partOfSerbia = partOfSerbia;
        this.numberOfDays = numberOfDays;
        this.numberOfTravelers = numberOfTravelers;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ResponsesDTO() {
        this.attractionTypes = new ArrayList<>();
    }

    public void addAttractionType(AttractionType attractionType){
        if(!this.getAttractionTypes().contains(attractionType)){
            this.getAttractionTypes().add(attractionType);        }

    }

    public void removeAttractionType(AttractionType attractionType){
        this.getAttractionTypes().remove(attractionType);
    }

    public List<AttractionType> getAttractionTypes() {
        return attractionTypes;
    }

    public void setAttractionTypes(List<AttractionType> attractionTypes) {
        this.attractionTypes = attractionTypes;
    }

    public Double getTripBudget() {
        return tripBudget;
    }

    public void setTripBudget(Double tripBudget) {
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

    public Integer getNumberOfTravelers() {
        return numberOfTravelers;
    }

    public void setNumberOfTravelers(Integer numberOfTravelers) {
        this.numberOfTravelers = numberOfTravelers;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
