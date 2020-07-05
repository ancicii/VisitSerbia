package sbnz.visitserbia.services;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sbnz.visitserbia.DTOs.AnswerDTO;
import sbnz.visitserbia.DTOs.ResponsesDTO;
import sbnz.visitserbia.exceptions.EntityNotFoundException;
import sbnz.visitserbia.model.*;
import sbnz.visitserbia.repositories.AnswerRepository;
import sbnz.visitserbia.repositories.QuestionnaireRepository;
import sbnz.visitserbia.repositories.UserRepository;

import javax.xml.transform.sax.SAXSource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class QuestionnaireService {
    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnswerRepository answerRepository;

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

    public void sendAnswers(List<Integer> answers) {
        User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        RegisteredUser registeredUser = userRepository.findRegUserByEmail(u.getEmail());
        registeredUser.setMyAnswers(answers);
        Set<UserPreference> userPreferences = new HashSet<>();

        for(Integer in: answers){
            UserPreference userPreference = new UserPreference();
            if(in != -1){
                Answer answer = answerRepository.getOne(Long.valueOf(in));
                userPreference.setPreference(answer.getPreference());
                userPreferences.add(userPreference);

            }
            registeredUser.setPreferences(userPreferences);
        }

        userRepository.save(registeredUser);
    }
}
