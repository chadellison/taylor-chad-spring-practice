package com.allstate.compozed.springplayground.math;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(MathController.class)
public class MathControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void sqaureShouldReturn16() throws Exception {
        mockMvc.perform(get("/math/square/4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.square", Matchers.is(16)));
    }

    @Test
    public void squareShouldReturn25() throws Exception {
        mockMvc.perform(get("/math/square/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.square", Matchers.is(25)));
    }

    @Test
    public void factorialShouldReturn6Given3() throws Exception {
        mockMvc.perform(get("/math/factorial/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.factorial", Matchers.is(6)));
    }

    @Test
    public void factorialShouldReturn120() throws Exception {
        mockMvc.perform(get("/math/factorial/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.factorial", Matchers.is(120)));
    }

    @Test
    public void fibonacciShouldReturn2Given3() throws Exception {
        mockMvc.perform(get("/math/fibonacci/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fibonacci", Matchers.is(2)));

    }
}