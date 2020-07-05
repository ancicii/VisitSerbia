package sbnz.visitserbia.services;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.stereotype.Service;
import sbnz.visitserbia.exceptions.EntityNotFoundException;
import sbnz.visitserbia.model.Attraction;

import java.util.List;

@Service
public class ReportService {

    private final KieContainer kieContainer;

    public ReportService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public List<Attraction> getLeastPopularAttractions() throws EntityNotFoundException {
        KieSession kieSession = kieContainer.newKieSession("visit-serbia-rule");
        final QueryResults results = kieSession.getQueryResults("Least popular attractions");
        if (results == null) {
            throw new EntityNotFoundException("No attractions found!");
        }

        for (QueryResultsRow row : results) {
            System.out.println(row.get("$leastPopular"));
            List<Attraction> ats = (List<Attraction>) row.get("$leastPopular");
            return ats;
        }
        return null;

    }

    public List<Attraction> getMediumPopularAttractions() throws EntityNotFoundException {
        KieSession kieSession = kieContainer.newKieSession("visit-serbia-rule");
        final QueryResults results = kieSession.getQueryResults("Medium popular attractions");
        if (results == null) {
            throw new EntityNotFoundException("No attractions found!");
        }

        for (QueryResultsRow row : results) {
            System.out.println(row.get("$leastPopular"));
            List<Attraction> ats = (List<Attraction>) row.get("$leastPopular");
            return ats;
        }
        return null;
    }

    public List<Attraction> getMostPopularAttractions() throws EntityNotFoundException {
        KieSession kieSession = kieContainer.newKieSession("visit-serbia-rule");
        final QueryResults results = kieSession.getQueryResults("Most popular attractions");
        if (results == null) {
            throw new EntityNotFoundException("No attractions found!");
        }
        for (QueryResultsRow row : results) {
            System.out.println(row.get("$leastPopular"));
            List<Attraction> ats = (List<Attraction>) row.get("$leastPopular");
            return ats;
        }
        return null;
    }
}
