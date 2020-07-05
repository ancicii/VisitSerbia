package sbnz.visitserbia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Attraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String name;
    private AttractionType attractionType;
    private Double timeToSpend;
    private Budget attractionBudget;
    private Season seasonToVisit;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    @JsonIgnore
    private City city;
    private Integer timesRecommended;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    public Attraction() {
        this.timesRecommended = 0;
    }

    public Attraction(String name, AttractionType attractionType, Double timeToSpend
            , Budget moneyToSpend, Season seasonToVisit, City city, Integer timesRecommended, byte[] image) {
        this.name = name;
        this.attractionType = attractionType;
        this.timeToSpend = timeToSpend;
        this.attractionBudget = moneyToSpend;
        this.seasonToVisit = seasonToVisit;
        this.city = city;
        this.timesRecommended = timesRecommended;
        this.image = image;

    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Integer getTimesRecommended() {
        return timesRecommended;
    }

    public void setTimesRecommended(Integer timesRecommended) {
        this.timesRecommended = timesRecommended;
    }

    public AttractionType getAttractionType() {
        return attractionType;
    }

    public void setAttractionType(AttractionType attractionType) {
        this.attractionType = attractionType;
    }

    public Budget getAttractionBudget() {
        return attractionBudget;
    }

    public void setAttractionBudget(Budget attractionBudget) {
        this.attractionBudget = attractionBudget;
    }

    public Season getSeasonToVisit() {
        return seasonToVisit;
    }

    public void setSeasonToVisit(Season seasonToVisit) {
        this.seasonToVisit = seasonToVisit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTimeToSpend() {
        return timeToSpend;
    }

    public void setTimeToSpend(Double timeToSpend) {
        this.timeToSpend = timeToSpend;
    }

    public Budget getMoneyToSpend() {
        return attractionBudget;
    }

    public void setMoneyToSpend(Budget moneyToSpend) {
        this.attractionBudget = moneyToSpend;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
