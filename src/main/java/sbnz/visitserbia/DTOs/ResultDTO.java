package sbnz.visitserbia.DTOs;

import org.w3c.dom.Attr;
import sbnz.visitserbia.model.Attraction;
import sbnz.visitserbia.model.Budget;
import sbnz.visitserbia.model.TripGrade;

import java.util.ArrayList;
import java.util.List;

public class ResultDTO {

    private List<Attraction> attractionList;
    private Integer numberOfCities;
    private Integer numberOfDays;
    private double timeByDay;
    private TripGrade tripGrade;
    private Budget tripBudget;

    public ResultDTO(List<Attraction> attractionList, Integer numberOfCities, Integer numberOfDays,
                     double timeByDay, TripGrade tripGrade, Budget tripBudget) {
        this.attractionList = attractionList;
        this.numberOfCities = numberOfCities;
        this.numberOfDays = numberOfDays;
        this.timeByDay = timeByDay;
        this.tripGrade = tripGrade;
        this.tripBudget = tripBudget;
    }

    public ResultDTO() {
    }

    public Budget getTripBudget() {
        return tripBudget;
    }

    public void setTripBudget(Budget tripBudget) {
        this.tripBudget = tripBudget;
    }

    public TripGrade getTripGrade() {
        return tripGrade;
    }

    public void setTripGrade(TripGrade tripGrade) {
        this.tripGrade = tripGrade;
    }

    public List<Attraction> getAttractionList() {
        return attractionList;
    }

    public void setAttractionList(List<Attraction> attractionList) {
        this.attractionList = attractionList;
    }

    public Integer getNumberOfCities() {
        ArrayList<String> foundCities = new ArrayList<>();
        for(Attraction a: this.attractionList){
            if (!foundCities.contains(a.getCityName())){
                foundCities.add(a.getCityName());
            }
        }
        return foundCities.size();
    }

    public void setNumberOfCities(Integer numberOfCities) {
        this.numberOfCities = numberOfCities;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public double getTimeByDay() {
        double time = 0;
        for(Attraction a: this.getAttractionList()){
            time += a.getTimeToSpend();
        }
        double timeByDay = time / this.numberOfDays;
        return timeByDay;
    }

    public void setTimeByDay(double timeByDay) {
        this.timeByDay = timeByDay;
    }
}
