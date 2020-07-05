package sbnz.visitserbia.services;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sbnz.visitserbia.DTOs.ParamsDTO;
import sbnz.visitserbia.DTOs.ResponsesDTO;
import sbnz.visitserbia.exceptions.EntityNotValidException;
import sbnz.visitserbia.model.*;
import sbnz.visitserbia.repositories.AttractionRepository;
import sbnz.visitserbia.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;

@Service
public class TripService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AttractionRepository attractionRepository;

    private final KieContainer kieContainer;

    @Autowired
    public TripService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public Result planTrip(ParamsDTO paramsDTO) throws EntityNotValidException {
        ResponsesDTO responsesDTO = new ResponsesDTO();
        responsesDTO.setNumberOfTravelers(paramsDTO.getNoOfPassengers());
        responsesDTO.setPartOfSerbia(PartOfSerbia.valueOf(paramsDTO.getPartOfSerbia()));
        responsesDTO.setStartDate(paramsDTO.getStartDate());
        responsesDTO.setEndDate(paramsDTO.getEndDate());
        int oneDay = 24*60*60*1000;
        long noOfDays = Math.abs((paramsDTO.getEndDate().getTime() - paramsDTO.getStartDate().getTime())/(oneDay)) + 1;
        responsesDTO.setNumberOfDays(Math.toIntExact(noOfDays));
        responsesDTO.setTripBudget(Double.valueOf(paramsDTO.getBudget()));
        User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        RegisteredUser registeredUser = userRepository.findRegUserByEmail(u.getEmail());
        ArrayList<PreferenceEnum> preferences = new ArrayList<>();
        for(UserPreference userPreference: registeredUser.getPreferences()){
            if(!preferences.contains(userPreference.getPreference())){
                preferences.add(userPreference.getPreference());
            }
        }
        if(preferences.isEmpty()){
            throw new EntityNotValidException("You didn't set any preferences in your profile!");
        }
        Result result = new Result();
        ArrayList<Attraction> allAttractions = (ArrayList<Attraction>) attractionRepository.findAll();


        KieSession kieSession = kieContainer.newKieSession("visit-serbia-rule");
        kieSession.getAgenda().getAgendaGroup("questions").setFocus();
        kieSession.insert(responsesDTO);
        kieSession.insert(registeredUser);
        kieSession.insert(result);
        kieSession.setGlobal("allAttractions", allAttractions);
        kieSession.fireAllRules();

        for(Attraction a: result.getAttractions()){
            System.out.println(a.getName());
        }

        return null;

    }
}
