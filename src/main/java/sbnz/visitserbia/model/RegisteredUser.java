package sbnz.visitserbia.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class RegisteredUser extends User {
    private Integer years;
    private MaritalStatus maritalStatus;
    private Integer numberOfKids;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_preferences",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "preference_id", referencedColumnName = "id"))
    private Set<UserPreference> preferences;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Result> results;

    @ElementCollection
    @CollectionTable(name = "user_answers", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "answer")
    private List<Integer> myAnswers;

    public RegisteredUser(Long id, String firstName, String lastName, String password, String email, Boolean isVerified,
                          Integer years, MaritalStatus maritalStatus, Integer numberOfKids, List<Integer> myAnswers) {
        super(id, firstName, lastName, password, email, isVerified);
        Authority a = new Authority();
        a.setType(UserType.ROLE_USER);
        super.getAuthorities().add(a);
        this.years = years;
        this.maritalStatus = maritalStatus;
        this.numberOfKids = numberOfKids;
        this.preferences =  new HashSet<>();
        this.results = new ArrayList<>();
        this.myAnswers = new ArrayList<>();
    }

    public RegisteredUser() {
        this.preferences = new HashSet<>();
        this.results = new ArrayList<>();
        this.myAnswers = new ArrayList<>();
    }

    public List<Integer> getMyAnswers() {
        return myAnswers;
    }

    public void setMyAnswers(List<Integer> myAnswers) {
        this.myAnswers = myAnswers;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Integer getNumberOfKids() {
        return numberOfKids;
    }

    public void setNumberOfKids(Integer numberOfKids) {
        this.numberOfKids = numberOfKids;
    }

    public Set<UserPreference> getPreferences() {
        return preferences;
    }

    public void setPreferences(Set<UserPreference> preferences) {
        this.preferences = preferences;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof RegisteredUser)) {
            return false;
        }
        RegisteredUser user = (RegisteredUser) o;
        if (this.getId()!=null ? !this.getId().equals(user.getId()): user.getId()!=null) return false;
        if (this.getFirstName()!=null ? !this.getFirstName().equals(user.getFirstName()): user.getFirstName()!=null) return false;
        if (this.getLastName()!=null ? !this.getLastName().equals(user.getLastName()): user.getLastName()!=null) return false;
        if (this.getEmail()!=null ? !this.getEmail().equals(user.getEmail()): user.getEmail()!=null) return false;
        if (this.getVerified()!=null ? !this.getVerified().equals(user.getVerified()): user.getVerified()!=null) return false;
        if (this.getPassword()!=null ? !this.getPassword().equals(user.getPassword()): user.getPassword()!=null) return false;
        if (this.getUsername()!=null ? !this.getUsername().equals(user.getUsername()): user.getUsername()!=null) return false;
        if (this.getYears()!=null ? !this.getYears().equals(user.getYears()): user.getYears()!=null) return false;
        if (this.getMaritalStatus()!=null ? !this.getMaritalStatus().equals(user.getMaritalStatus()): user.getMaritalStatus()!=null) return false;
        if (this.getNumberOfKids()!=null ? !this.getNumberOfKids().equals(user.getNumberOfKids()): user.getNumberOfKids()!=null) return false;
        return (this.getPreferences()!=null ? this.getPreferences().equals(user.getPreferences()): user.getPreferences()==null);

    }

}
