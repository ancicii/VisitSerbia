package sbnz.visitserbia.model;

import javax.persistence.*;

@Entity
public class UserPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private PreferenceEnum preference;

    public UserPreference() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PreferenceEnum getPreference() {
        return preference;
    }

    public void setPreference(PreferenceEnum preference) {
        this.preference = preference;
    }
}
