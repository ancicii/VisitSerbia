package sbnz.visitserbia.model;

public enum PartOfSerbia {
    EASTERN("East Serbia"),
    SOUTHERN("South Serbia"),
    NORTHERN("Vojvodina and Belgrade"),
    WESTERN("West Serbia and Sumadija");

    private String detailed;

    // getter method
    public String getDetailed()
    {
        return this.detailed;
    }

    // enum constructor - cannot be public or protected
    private PartOfSerbia(String detailed)
    {
        this.detailed = detailed;
    }
    }
