package sbnz.visitserbia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import sbnz.visitserbia.DTOs.AnswerDTO;
import sbnz.visitserbia.DTOs.ResponsesDTO;
import sbnz.visitserbia.exceptions.EntityNotFoundException;
import sbnz.visitserbia.model.Question;
import sbnz.visitserbia.services.QuestionnaireService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/questions")
@CrossOrigin
public class QuestionnaireController {

    @Autowired
    private QuestionnaireService questionnaireService;

    private ResponsesDTO responsesDTO = new ResponsesDTO();

    @GetMapping()
    public ResponseEntity<List<Question>> getAllQuestions() {
        return new ResponseEntity<>(questionnaireService.getAllQuestions(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getQuestion(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(questionnaireService.getQuestion(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Secured("ROLE_USER")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void answer(@Valid @RequestBody AnswerDTO answer) {
        questionnaireService.answer(answer, responsesDTO);
    }

}
