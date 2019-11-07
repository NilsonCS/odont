package com.odont.odont.controllers;

import com.odont.odont.models.dao.ITreatmentDao;
import com.odont.odont.models.entity.TreatmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/treatment/")
public class TreatmentRestController {

    private final ITreatmentDao iTreatmentDao;

    @Autowired
    public TreatmentRestController(ITreatmentDao iTreatmentDao) {
        this.iTreatmentDao = iTreatmentDao;
    }

    @GetMapping("creat")
    public String showCreatTreatment(TreatmentEntity treatmentEntity) {
        return "add-treatment";
    }

    @GetMapping("list")
    public String showUpdateTreatment(Model model) {
        model.addAttribute("treatments", iTreatmentDao.findAll());
        return "index";
    }

    @PostMapping("add")
    public String addTreatment(@Valid TreatmentEntity treatmentEntity, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-Materials";
        }
        iTreatmentDao.save(treatmentEntity);
        return "List:";
    }

    @GetMapping("edit/{treatmentId}")
    public String showUpdateTreatment(@PathVariable("treatmentId") long id, Model model) {

        TreatmentEntity treatmentEntity = iTreatmentDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Materials: " + id));
        model.addAttribute("tratments", treatmentEntity);
        return "update-treatment";
    }

    @PostMapping("update/{treatmentId}")
    public String updateTreatment(@PathVariable("treatmentId") long id, @Valid TreatmentEntity treatmentEntity, BindingResult result, Model model) {
        if (result.hasErrors()) {
            treatmentEntity.setTreatmentId((int) id);
            return "update-treatment";
        }
        iTreatmentDao.save(treatmentEntity);
        model.addAttribute("treatments", iTreatmentDao.findAll());
        return "index";
    }

    @GetMapping("delete/{treatmentId}")
    public String deleteTreatment(@PathVariable("treatmentId") long id, Model model) {
        TreatmentEntity treatmentEntity = iTreatmentDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid treatment: " + id));
        iTreatmentDao.delete(treatmentEntity);
        model.addAttribute("treatments", iTreatmentDao.findAll());
        return "index";
    }
}
/*
@CrossOrigin(origins = {"http://localhost:8080"})
@RestController
@RequestMapping("/api")
public class TreatmentRestController {
    @Autowired
    private ITreatmentService iTreatmentService;
    @GetMapping("/treatment")
    public List<TreatmentEntity> index(){
        return iTreatmentService.findAll();
    }
    @GetMapping("/treatment/{treatmentId}")
    public TreatmentEntity show(@PathVariable Long treatmentId){return iTreatmentService.findById(treatmentId);}
    @PostMapping("/treatment")
    @ResponseStatus(HttpStatus.CREATED)
    public TreatmentEntity create(@RequestBody TreatmentEntity treatmentEntity){
        return iTreatmentService.save(treatmentEntity);
    }
    @PutMapping("/treatment/{treatmentId}")
    public TreatmentEntity update(@RequestBody TreatmentEntity treatmentEntity, @PathVariable Long treatmentId){
        TreatmentEntity treatmentEntity1 = iTreatmentService.findById(treatmentId);
        treatmentEntity1.setNameTreatment(treatmentEntity.getNameTreatment());
        treatmentEntity1.setCostTreatment(treatmentEntity.getCostTreatment());
        treatmentEntity1.setDuration(treatmentEntity.getDuration());
        return iTreatmentService.save(treatmentEntity1);

    }
    @DeleteMapping("/treatment/{treatmentId}")
    public void delete (@PathVariable Long treatmentId){iTreatmentService.delete(treatmentId);}

}*/
