package sbnz.visitserbia.DTOs;

public class AnswerDTO {

    private String preference;
    private Long questionId;

    public AnswerDTO(String preference, Long questionId) {
        this.preference = preference;
        this.questionId = questionId;
    }

    public AnswerDTO() {
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
