package com.allstate.compozed.springplayground.lesson;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
    ResponseEntity<LessonModel> update(@PathVariable Long id, @RequestBody final LessonModel lessonModel) throws IOException {
        if (id.equals(lessonModel.getId())) {
            repository.save(lessonModel);
            return new ResponseEntity<>(lessonModel, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repository.exists(id)) {
            repository.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
