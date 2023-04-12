package net.redciscso.javapmproject.controllers;


import lombok.RequiredArgsConstructor;
import net.redciscso.javapmproject.domain.Subject;
import net.redciscso.javapmproject.dto.HeaderDto;
import net.redciscso.javapmproject.dto.SubjectDto;
import net.redciscso.javapmproject.form.HeaderForm;
import net.redciscso.javapmproject.form.HeaderUpdateForm;
import net.redciscso.javapmproject.service.HeaderService;
import net.redciscso.javapmproject.service.SubjectService;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(HeaderController.HEADER_URI)
@RequiredArgsConstructor
public class HeaderController {
    public static final String HEADER_URI = "/header";
    public static final String ALL_HEADER_URI = "/all";
    public static final String ALL_HEADER_BY_SUBJECT_URI = "/all/subject/{subjectId}";
    public static final String ALL_CONTAINS_HEADER_URI = "/all/{subjectId}/{request}";
    public static final String ADD_HEADER_URI = "/add";
    public static final String UPDATE_HEADER_URI = "/update";
    public static final String DELETE_HEADER_URI = "/delete/{id}";

    private final HeaderService headerService;
    private final SubjectService subjectService;

    @CrossOrigin
    @GetMapping(ALL_HEADER_URI)
    public List<HeaderDto> getAll(){
        return headerService.getAllHeaders();
    }

    @CrossOrigin
    @GetMapping(ALL_HEADER_BY_SUBJECT_URI)
    public Map<String, Object> getAllBySubjectId(@PathVariable Long subjectId){
        SubjectDto subjectDto = subjectService.getById(subjectId);
        Map<String, Object> response = new HashMap<>();
        response.put("subjectName", subjectDto.getName());
        response.put("headerList", headerService.getAllBySubjectId(subjectId));
        return response;
    }

    @CrossOrigin
    @GetMapping(ALL_CONTAINS_HEADER_URI)
    public List<HeaderDto> getAllContains(@PathVariable Long subjectId, @PathVariable String request){
        return headerService.getAllContains(subjectId, request);
    }

    @CrossOrigin
    @PostMapping(ADD_HEADER_URI)
    public HeaderDto add(@RequestBody HeaderForm headerForm){
        return headerService.add(headerForm);
    }

    @CrossOrigin
    @PostMapping(UPDATE_HEADER_URI)
    public HeaderDto update(@RequestBody HeaderUpdateForm headerForm){
        return headerService.update(headerForm);
    }

    @CrossOrigin
    @GetMapping(DELETE_HEADER_URI)
    public void delete(@PathVariable Long id){
        headerService.delete(id);
    }
}
