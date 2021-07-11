package ru.zmath.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.zmath.rest.RunApp;
import ru.zmath.rest.service.dto.MethodDTO;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = RunApp.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class MethodControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenFindAll() throws Exception {
        List<MethodDTO> methodDTOList = List.of(
            new MethodDTO(1, "TestMethod1", "Desc1"),
            new MethodDTO(2, "TestMethod2", null)
        );
        this.mockMvc.perform(get("/api/method"))
                .andExpect(status().isOk())
                .andExpect(content()
                    .string(new ObjectMapper().writeValueAsString(methodDTOList))
                );
    }
}
