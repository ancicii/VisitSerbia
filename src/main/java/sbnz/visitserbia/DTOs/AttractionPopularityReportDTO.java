package sbnz.visitserbia.DTOs;

import sbnz.visitserbia.model.Attraction;

import java.util.List;

public class AttractionPopularityReportDTO {

    private List<Attraction> attractions;

    public AttractionPopularityReportDTO(List<Attraction> attractions) {
        this.attractions = attractions;
    }

    public AttractionPopularityReportDTO() {
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }
}
