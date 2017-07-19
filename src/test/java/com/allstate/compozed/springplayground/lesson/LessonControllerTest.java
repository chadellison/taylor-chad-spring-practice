package com.allstate.compozed.springplayground.lesson;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Arrays;
import java.util.Random;

import static org.hamcrest.Matchers.is;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(LessonController.class)
public class LessonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LessonRepository lessonRepository;

    @Test
    public void createDelegatesToRepository() throws Exception {

        when(lessonRepository.save(any(LessonModel.class))).then(returnsFirstArg());

        final MockHttpServletRequestBuilder request = post("/lessons")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Mock me another one!\"}");

        final ResultActions resultActions = mockMvc.perform(request);

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Mock me another one!")));

        verify(lessonRepository).save(any(LessonModel.class));
    }

    @Test
    public void listDelegatesToRepository() throws Exception {
        LessonModel lesson = new LessonModel();
        Long id = new Random().nextLong();
        lesson.setId(id);
        String title = "New Mock lesson";
        lesson.setTitle(title);

        LessonModel lesson2 = new LessonModel();
        Long id2 = new Random().nextLong();
        String title2 = "Second Mock lesson";
        lesson2.setId(id2);
        lesson2.setTitle(title2);

        when(lessonRepository.findAll()).thenReturn(Arrays.asList(lesson, lesson2));

        final MockHttpServletRequestBuilder request = get("/lessons");

        final ResultActions resultActions = mockMvc.perform(request);

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(id)))
                .andExpect(jsonPath("$[0].title", is(title)))
                .andExpect(jsonPath("$[1].id", is(id2)))
                .andExpect(jsonPath("$[1].title", is(title2)));

        verify(lessonRepository).findAll();
    }

    @Test
    public void showDelegatesToTheRepository() throws Exception {
        LessonModel lesson = new LessonModel();
        Long id = new Random().nextLong();
        lesson.setId(id);
        String title = "First Lesson";
        lesson.setTitle(title);

        when(lessonRepository.findOne(id)).thenReturn(lesson);

        final MockHttpServletRequestBuilder request = get("/lessons/" + id);

        final ResultActions resultActions = mockMvc.perform(request);

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(title)))
                .andExpect(jsonPath("$.id", is(id)));

        verify(lessonRepository).findOne(id);

    }

    @Test
    public void updateDelegatesToTheRepositoryWhenThePathIdMatchesTheBodyId() throws Exception {
        LessonModel lesson = new LessonModel();
        lesson.setId(5L);
        String title = "Updated Lesson";
        lesson.setTitle(title);

        when(lessonRepository.save(refEq(lesson))).thenReturn(lesson);

        final MockHttpServletRequestBuilder request = patch("/lessons/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":5, \"title\":\"Updated Lesson\"}");

        final ResultActions resultActions = mockMvc.perform(request);

        verify(lessonRepository).save(refEq(lesson));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(5)))
                .andExpect(jsonPath("$.title", is(title)));

    }

    @Test
    public void updateReturnsA404WhenThePathIdDoesNotMatchTheBodyId() throws Exception {
        LessonModel lesson = new LessonModel();
        lesson.setId(5L);
        String title = "Updated Lesson";
        lesson.setTitle(title);

        when(lessonRepository.save(any(LessonModel.class))).then(returnsFirstArg());

        final MockHttpServletRequestBuilder request = patch("/lessons/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":6, \"title\":\"Updated Lesson\"}");

        final ResultActions resultActions = mockMvc.perform(request);

        resultActions.andExpect(status().isNotFound());
        verify(lessonRepository, never()).save(refEq(lesson));
    }

    @Test
    public void deleteMethodDelegatesToTheRepositoryWhenRecordExists() throws Exception {
        Long id = new Random().nextLong();

        when(lessonRepository.exists(id)).thenReturn(true);


        final MockHttpServletRequestBuilder request = delete("/lessons/" + id);

        final ResultActions resultActions = mockMvc.perform(request)
                .andExpect(status().isOk());

        verify(lessonRepository).delete(id);
    }

    @Test
    public void deleteMethodReturns404WhenRecordDoesNotExist() throws Exception {

        Long id = new Random().nextLong();
        verify(lessonRepository, never()).delete(id);
        final MockHttpServletRequestBuilder request = delete("/lessons/" + id);

        final ResultActions resultActions = mockMvc.perform(request)
                .andExpect(status().isNotFound());




    }
}