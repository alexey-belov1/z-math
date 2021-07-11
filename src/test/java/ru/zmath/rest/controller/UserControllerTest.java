package ru.zmath.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.zmath.rest.RunApp;
import ru.zmath.rest.service.UserService;
import ru.zmath.rest.service.dto.UserDTO;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = RunApp.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WithMockUser(roles = "ADMIN")
public class UserControllerTest {

    @Autowired
    UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenFindAll() throws Exception {
        this.mockMvc.perform(get("/api/user"))
                .andExpect(status().isOk())
                .andExpect(content()
                    .json(
                        "[{\"id\":1,\"login\":\"admin\",\"email\":\"email\",\"password\":\"password\",\"role\":{\"id\":3,\"name\":\"ROLE_ADMIN\"},\"created\":\"2021-01-01T00:00:00.000+00:00\"}," +
                            "{\"id\":2,\"login\":\"user\",\"email\":\"email\",\"password\":\"password\",\"role\":{\"id\":1,\"name\":\"ROLE_USER\"},\"created\":\"2021-01-01T00:00:00.000+00:00\"}]"
                    )
                );
    }

    @Test
    public void whenFindByIdAndFound() throws Exception {
        this.mockMvc.perform(get("/api/user/1"))
            .andExpect(status().isOk())
            .andExpect(content()
                .json(
                    "{\"id\":1,\"login\":\"admin\",\"email\":\"email\",\"password\":\"password\",\"role\":{\"id\":3,\"name\":\"ROLE_ADMIN\"},\"created\":\"2021-01-01T00:00:00.000+00:00\"}"
                )
            );
    }

    @Test
    public void whenFindByIdAndNotFound() throws Exception {
        this.mockMvc.perform(get("/api/user/10"))
            .andExpect(status().isNotFound());
    }

    // Тоже с id подумать
    @Test
    public void whenCreate() throws Exception {
        UserDTO userDTO = new UserDTO(3, "login", "emailTest", "password", null, null);

        this.mockMvc.perform(
            post("/api/user/sign-up")
                .content(new ObjectMapper().writeValueAsString(userDTO))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());

        List<UserDTO> users = userService.findAll();

        UserDTO result = userService
            .findAll()
            .stream()
            .filter(u -> u.getLogin().equals(userDTO.getLogin()))
            .findFirst()
            .orElse(new UserDTO());

        assertThat(result.getEmail(), is(userDTO.getEmail()));
        assertThat(users.size(), is(3));

        userService.delete(result.getId());
    }

    @Test
    @Transactional
    public void whenUpdateAndOk() throws Exception {
        UserDTO userDTO = userService.findById(2).orElse(new UserDTO());
        userDTO.setLogin("updatedLogin");

        this.mockMvc.perform(
            put("/api/user")
                .content(new ObjectMapper().writeValueAsString(userDTO))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        assertThat(userService.findById(2).get().getLogin(), is(userDTO.getLogin()));
    }

    @Test
    public void whenUpdateAndNotFound() throws Exception {
        UserDTO userDTO = userService.findById(2).orElse(new UserDTO());
        userDTO.setId(10);

        this.mockMvc.perform(
            put("/api/user")
                .content(new ObjectMapper().writeValueAsString(userDTO))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void whenDeleteAndOk() throws Exception {
        this.mockMvc.perform(delete("/api/user/1"))
            .andExpect(status().isOk());
        assertThat(userService.findAll().size(), is(1));
        assertThat(userService.findAll().get(0).getId(), is(2));
    }

    @Test
    @Transactional
    public void whenDeleteAndNotFound() throws Exception {
        this.mockMvc.perform(delete("/api/user/10"))
            .andExpect(status().isNotFound());
    }
}
