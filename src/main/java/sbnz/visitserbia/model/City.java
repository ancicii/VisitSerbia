package sbnz.visitserbia.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String name;
    private PartOfSerbia partOfSerbia;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "city")
    private List<Attraction> attractions;

    public City() {
        this.attractions = new ArrayList<>();
    }

    public City(Long id, String name, PartOfSerbia partOfSerbia, List<Attraction> attractions) {
        this.id = id;
        this.name = name;
        this.partOfSerbia = partOfSerbia;
        this.attractions = attractions;
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

    public PartOfSerbia getPartOfSerbia() {
        return partOfSerbia;
    }

    public void setPartOfSerbia(PartOfSerbia partOfSerbia) {
        this.partOfSerbia = partOfSerbia;
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }
}
