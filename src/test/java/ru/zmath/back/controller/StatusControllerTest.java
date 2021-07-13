package ru.zmath.back.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.zmath.back.service.dto.StatusDTO;
import ru.zmath.back.RunApp;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = RunApp.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class StatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenFindAll() throws Exception {
        List<StatusDTO> subjectDTOList = List.of(
            new StatusDTO(1, "TestStatus1"),
            new StatusDTO(2, "TestStatus2"),
            new StatusDTO(3, "TestStatus3")
        );
        this.mockMvc.perform(get("/api/status"))
                .andExpect(status().isOk())
                .andExpect(content()
                    .string(new ObjectMapper().writeValueAsString(subjectDTOList))
                );
    }
}
