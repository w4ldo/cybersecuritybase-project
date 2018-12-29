package sec.project.controller;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sec.project.domain.Note;
import sec.project.repository.AccountRepository;
import sec.project.repository.NoteRepository;

@Controller
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/notes";
    }

    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    public String loadForm(Authentication authentication, Model model) {
        model.addAttribute("notes", accountRepository.findByUsername(authentication.getName()).getNotes());
        return "form";
    }
    
    @ResponseBody
    @RequestMapping(value = "/notes/{id}", method = RequestMethod.GET)
    public String loadNote(@PathVariable Long id) {
        return noteRepository.getOne(id).toString();
    }

    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    public String submitForm(Authentication authentication, @RequestParam String title, @RequestParam String content) {
        Note note = new Note(title, content);
        note.setAccount(accountRepository.findByUsername(authentication.getName()));
        noteRepository.save(note);
        return "redirect:/form";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteNotes() {
        noteRepository.deleteAll();
        return "redirect:/notes";
    }

}
