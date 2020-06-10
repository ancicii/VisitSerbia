package sbnz.visitserbia.services;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.visitserbia.DTOs.AnswerDTO;
import sbnz.visitserbia.DTOs.ResponsesDTO;
import sbnz.visitserbia.exceptions.EntityNotFoundException;
import sbnz.visitserbia.model.AttractionType;
import sbnz.visitserbia.model.Question;
import sbnz.visitserbia.repositories.QuestionnaireRepository;

import java.util.List;

@Service
public class QuestionnaireService {
    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    private final KieContainer kieContainer;

    @Autowired
    public QuestionnaireService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public List<Question> getAllQuestions() {
        return questionnaireRepository.findAll();
    }

    public Question getQuestion(Long id) throws EntityNotFoundException {
        return questionnaireRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Question not found"));
    }

    public void answer(AnswerDTO answer, ResponsesDTO responsesDTO) {
        KieSession kieSession = kieContainer.newKieSession("visit-serbia-rule");
        kieSession.getAgenda().getAgendaGroup("questions").setFocus();
        kieSession.insert(answer);
        kieSession.insert(responsesDTO);
        kieSession.fireAllRules();
//        for(AttractionType a: responsesDTO.getAttractionTypes()){
//            System.out.println(a.name());
//        }

    }
}
