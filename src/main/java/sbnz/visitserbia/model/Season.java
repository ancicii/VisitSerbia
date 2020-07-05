package sbnz.visitserbia.model;

public enum Season {
    HOT("Summer"), COLD ("Winter"), ANY ("Any Season");

    private String description;
    Season(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
