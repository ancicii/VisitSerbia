package sbnz.visitserbia.model;

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
    private PartOfSerbia partOfSerbia;
    private Season seasonToVisit;
    private String cityName;
    private Integer timesRecommended;

    public Attraction() {
        this.timesRecommended = 0;
    }
    public Attraction(String name, AttractionType attractionType, Double timeToSpend
            , Budget moneyToSpend, PartOfSerbia partOfSerbia, Season seasonToVisit, String cityName, Integer timesRecommended) {
        this.name = name;
        this.attractionType = attractionType;
        this.timeToSpend = timeToSpend;
        this.attractionBudget = moneyToSpend;
        this.partOfSerbia = partOfSerbia;
        this.seasonToVisit = seasonToVisit;
        this.cityName = cityName;
        this.timesRecommended = timesRecommended;

    }

    public Integer getTimesRecommended() {
        return timesRecommended;
    }

    public void setTimesRecommended(Integer timesRecommended) {
        this.timesRecommended = timesRecommended;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    public PartOfSerbia getPartOfSerbia() {
        return partOfSerbia;
    }

    public void setPartOfSerbia(PartOfSerbia partOfSerbia) {
        this.partOfSerbia = partOfSerbia;
    }
}
