package com.skypro.SQl_HW33.controller;

import com.skypro.SQl_HW33.model.Faculty;
import com.skypro.SQl_HW33.service.FacultyService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public Collection<Faculty> getAll() {
        return facultyService.getAll();
    }

    @GetMapping("/{id}")
    public Faculty getById(@PathVariable("id") Long id){
        return facultyService.getById(id);
    }
    @GetMapping("/filtered")
    public Collection<Faculty> filteredByColor(@RequestParam("color") String color) {
        return facultyService.getByColor(color);
    }
    @PostMapping
    public Faculty create(@RequestBody Faculty faculty){
        return facultyService.create(faculty);
    }
    @PutMapping("/{id}")
    public Faculty update(@PathVariable("id") Long id, @RequestBody Faculty faculty){
        return facultyService.update(id,faculty);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        facultyService.delete(id);
    }
    @GetMapping("/by-search-or-name")
    public Collection<Faculty> filteredByColorOrName(@RequestParam String search ) {
        return facultyService.getByColorOrName(search,search);
    }
    @GetMapping("/by-student")
    public Faculty getByStudent(@RequestParam Long studentId){
        return facultyService.getByStudentId(studentId);
    }
}
