package net.redciscso.javapmproject.controllers;


import lombok.RequiredArgsConstructor;
import net.redciscso.javapmproject.dto.HeaderDto;
import net.redciscso.javapmproject.form.HeaderForm;
import net.redciscso.javapmproject.form.HeaderUpdateForm;
import net.redciscso.javapmproject.service.HeaderService;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;


@RestController
@RequestMapping(HeaderController.HEADER_URI)
@RequiredArgsConstructor
public class HeaderController {
    public static final String HEADER_URI = "/header";
    public static final String ALL_HEADER_URI = "/all";
    public static final String ALL_CONTAINS_HEADER_URI = "/all/{request}";
    public static final String ADD_HEADER_URI = "/add";
    public static final String UPDATE_HEADER_URI = "/update";
    public static final String DELETE_HEADER_URI = "/delete/{id}";

    private final HeaderService headerService;

    @CrossOrigin
    @GetMapping(ALL_HEADER_URI)
    public List<HeaderDto> getAll(){
        return headerService.getAllHeaders();
    }

    @CrossOrigin
    @GetMapping(ALL_CONTAINS_HEADER_URI)
    public List<HeaderDto> getAllContains(@PathVariable String request){
        return headerService.getAllContains(request);
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
