package net.redciscso.javapmproject.controllers;


import lombok.RequiredArgsConstructor;
import net.redciscso.javapmproject.dto.CommentaryDto;
import net.redciscso.javapmproject.form.CommentaryForm;
import net.redciscso.javapmproject.service.CommentaryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(CommentaryController.COMMENTARY_URI)
public class CommentaryController {
    public static final String COMMENTARY_URI = "/commentary";
    public static final String COMMENTARY_ADD_URI = "/add";
    public static final String COMMENTARY_DELETE_URI = "/delete/{id}";
    private final CommentaryService commentaryService;


    @CrossOrigin
    @PostMapping(COMMENTARY_ADD_URI)
    public CommentaryDto add(@RequestBody CommentaryForm commentaryForm){
        return commentaryService.add(commentaryForm);
    }

    @CrossOrigin
    @GetMapping(COMMENTARY_DELETE_URI)
    public void delete(@PathVariable Long id){
        commentaryService.delete(id);
    }


}
