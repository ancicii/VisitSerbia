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
    private TripGrade tripGrade;
    private Budget tripBudget;

    public Result() {
    }

    public Result(List<Attraction> attractions, TripGrade tripGrade, Budget tripBudget) {
        this.attractions = attractions;
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
