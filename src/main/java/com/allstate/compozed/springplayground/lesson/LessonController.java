package com.allstate.compozed.springplayground.lesson;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    private final LessonRepository repository;

    LessonController(final LessonRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    LessonModel create(@RequestBody final LessonModel lesson) {

        return repository.save(lesson);
    }

    @GetMapping
    Iterable<LessonModel> list() {

        return repository.findAll();
    }

    @GetMapping("/{id}")
    LessonModel show(@PathVariable Long id) {
        return repository.findOne(id);
    }

    @PatchMapping("/{id}")
    LessonModel update(@PathVariable Long id, @RequestBody final LessonModel lessonModel) {
//        if(id.equals(lessonModel.getId())) {
        return repository.save(lessonModel);
//        }
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        repository.delete(id);
    }

}
