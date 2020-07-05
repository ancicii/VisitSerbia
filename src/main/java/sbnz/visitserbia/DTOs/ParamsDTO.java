package sbnz.visitserbia.DTOs;

import java.util.Date;

public class ParamsDTO {
    private String partOfSerbia;
    private Integer noOfPassengers;
    private Integer budget;
    private Date startDate;
    private Date endDate;

    public String getPartOfSerbia() {
        return partOfSerbia;
    }

    public void setPartOfSerbia(String partOfSerbia) {
        this.partOfSerbia = partOfSerbia;
    }

    public Integer getNoOfPassengers() {
        return noOfPassengers;
    }

    public void setNoOfPassengers(Integer noOfPassengers) {
        this.noOfPassengers = noOfPassengers;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
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

    public ParamsDTO(String partOfSerbia, Integer noOfPassengers, Integer budget, Date startDate, Date endDate) {
        this.partOfSerbia = partOfSerbia;
        this.noOfPassengers = noOfPassengers;
        this.budget = budget;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ParamsDTO() {
    }
}
