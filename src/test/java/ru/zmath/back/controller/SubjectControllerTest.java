package ru.zmath.back.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.zmath.back.RunApp;
import ru.zmath.back.service.dto.SubjectDTO;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = RunApp.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class SubjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenFindAll() throws Exception {
        List<SubjectDTO> subjectDTOList = List.of(
            new SubjectDTO(1, "TestSubject1"),
            new SubjectDTO(2, "TestSubject2"),
            new SubjectDTO(3, "TestSubject3")
        );
        this.mockMvc.perform(get("/api/subject"))
                .andExpect(status().isOk())
                .andExpect(content()
                    .string(new ObjectMapper().writeValueAsString(subjectDTOList))
                );
    }
}
