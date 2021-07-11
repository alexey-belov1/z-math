package ru.zmath.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import ru.zmath.rest.RunApp;
import ru.zmath.rest.service.TaskService;
import ru.zmath.rest.service.dto.*;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = RunApp.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WithMockUser(roles = "ADMIN")
public class TaskControllerTest {

    @Autowired
    TaskService taskService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenFindAll() throws Exception {
        this.mockMvc.perform(get("/api/task"))
                .andExpect(status().isOk())
                .andExpect(content()
                    .json(
                        "[{\"id\":1,\"userId\":2,\"userLogin\":\"user\",\"subject\":{\"id\":1,\"name\":\"TestSubject1\"},\"comment\":\"comment\",\"deadline\":\"2021-01-01T00:00:00.000+00:00\",\"status\":{\"id\":1,\"name\":\"TestStatus1\"},\"cost\":null,\"preparedCost\":100.0,\"payment\":null,\"created\":\"2021-01-01T00:00:00.000+00:00\",\"contact\":\"contact\",\"cause\":\"cause\",\"archived\":false,\"attachedFiles\":[]}," +
                            "{\"id\":2,\"userId\":2,\"userLogin\":\"user\",\"subject\":{\"id\":1,\"name\":\"TestSubject1\"},\"comment\":\"comment\",\"deadline\":\"2021-01-01T00:00:00.000+00:00\",\"status\":{\"id\":1,\"name\":\"TestStatus1\"},\"cost\":null,\"preparedCost\":100.0,\"payment\":null,\"created\":\"2021-01-01T00:00:00.000+00:00\",\"contact\":\"contact\",\"cause\":\"cause\",\"archived\":false,\"attachedFiles\":[]}]"
                    )
                );
    }

    @Test
    public void whenFindByIdAndFound() throws Exception {
        this.mockMvc.perform(get("/api/task/1"))
            .andExpect(status().isOk())
            .andExpect(content()
                .json(
                    "{\"id\":1,\"userId\":2,\"userLogin\":\"user\",\"subject\":{\"id\":1,\"name\":\"TestSubject1\"},\"comment\":\"comment\",\"deadline\":\"2021-01-01T00:00:00.000+00:00\",\"status\":{\"id\":1,\"name\":\"TestStatus1\"},\"cost\":null,\"preparedCost\":100.0,\"payment\":null,\"created\":\"2021-01-01T00:00:00.000+00:00\",\"contact\":\"contact\",\"cause\":\"cause\",\"archived\":false,\"attachedFiles\":[]}"
                )
            );
    }

    @Test
    public void whenFindByIdAndNotFound() throws Exception {
        this.mockMvc.perform(get("/api/task/10"))
            .andExpect(status().isNotFound());
    }

    @Test
    public void whenCreate() throws Exception {
        TaskDTO taskDTO = new TaskDTO();

        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setId(1);

        taskDTO.setId(0);
        taskDTO.setUserLogin("user");
        taskDTO.setSubject(subjectDTO);
        taskDTO.setDeadline(Calendar.getInstance());
        taskDTO.setPreparedCost(111.0);

        MockMultipartFile jsonFile = new MockMultipartFile(
            "taskDTO",
            "",
            "application/json",
            new ObjectMapper().writeValueAsBytes(taskDTO)
        );

        this.mockMvc.perform(MockMvcRequestBuilders.multipart("/api/task").file(jsonFile))
            .andExpect(status().isCreated());

        List<TaskDTO> taskDTOList = taskService.findAll();

        TaskDTO result = taskDTOList
            .stream()
            .filter(r -> r.getPreparedCost().equals(taskDTO.getPreparedCost()))
            .findFirst()
            .orElse(new TaskDTO());

        assertThat(taskDTOList.size(), is(3));
        assertThat(result.getPreparedCost(), is(result.getPreparedCost()));

        taskService.delete(result.getId());
    }

    @Test
    @Transactional
    public void whenUpdateAndOk() throws Exception {
        TaskDTO taskDTO = taskService.findById(1).orElse(new TaskDTO());
        taskDTO.setComment("testComment");

        MockMultipartFile jsonFile = new MockMultipartFile(
            "taskDTO",
            "",
            "application/json",
            new ObjectMapper().writeValueAsBytes(taskDTO)
        );

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
            .multipart("/api/task")
            .file(jsonFile)
            .with(request -> {
                request.setMethod("PUT");
                return request;
            });

        this.mockMvc.perform(builder)
            .andExpect(status().isOk());

        assertThat(taskService.findById(1).get().getComment(), is(taskDTO.getComment()));
    }

    @Test
    public void whenUpdateAndNotFound() throws Exception {
        TaskDTO taskDTO = taskService.findById(2).orElse(new TaskDTO());
        taskDTO.setId(10);

        this.mockMvc.perform(
            put("/api/user")
                .content(new ObjectMapper().writeValueAsString(taskDTO))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void whenDeleteAndOk() throws Exception {
        this.mockMvc.perform(delete("/api/task/1"))
            .andExpect(status().isOk());
        assertThat(taskService.findAll().size(), is(1));
        assertThat(taskService.findAll().get(0).getId(), is(2));
    }

    @Test
    @Transactional
    public void whenDeleteAndNotFound() throws Exception {
        this.mockMvc.perform(delete("/api/task/10"))
            .andExpect(status().isNotFound());
    }

}
