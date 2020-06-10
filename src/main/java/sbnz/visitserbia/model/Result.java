package sbnz.visitserbia.model;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attraction> attractions;
    private Integer numberOfCities;
    private Integer numberOfDays;
    private double timeByDay;
    private TripGrade tripGrade;
    private Budget tripBudget;

    public Result() {
    }

    public Result(List<Attraction> attractions, Integer numberOfCities, Integer numberOfDays, double timeByDay,
                  TripGrade tripGrade, Budget tripBudget) {
        this.attractions = attractions;
        this.numberOfCities = numberOfCities;
        this.numberOfDays = numberOfDays;
        this.timeByDay = timeByDay;
        this.tripGrade = tripGrade;
        this.tripBudget = tripBudget;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }

    public Integer getNumberOfCities() {
        return numberOfCities;
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
        return timeByDay;
    }

    public void setTimeByDay(double timeByDay) {
        this.timeByDay = timeByDay;
    }

    public TripGrade getTripGrade() {
        return tripGrade;
    }

    public void setTripGrade(TripGrade tripGrade) {
        this.tripGrade = tripGrade;
    }

    public Budget getTripBudget() {
        return tripBudget;
    }

    public void setTripBudget(Budget tripBudget) {
        this.tripBudget = tripBudget;
    }
}
