package ru.zmath.back.controller;

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
import ru.zmath.back.RunApp;
import ru.zmath.back.service.ReviewService;
import ru.zmath.back.service.dto.ReviewDTO;

import java.util.Calendar;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = RunApp.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WithMockUser(username="user", roles = "USER")
public class ReviewControllerTest {

    @Autowired
    ReviewService reviewService;

    @Autowired
    private MockMvc mockMvc;

    // Подумать, может можно переписать с id.
    @Test
    public void whenCreate() throws Exception {
        ReviewDTO reviewDTO = new ReviewDTO(0,0, null, Calendar.getInstance(), "testText");

        this.mockMvc.perform(
            post("/api/review")
                .content(new ObjectMapper().writeValueAsString(reviewDTO))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());

        reviewDTO.setUserId(2);

        List<ReviewDTO> reviewDTOList = reviewService.findAll();

        ReviewDTO result = reviewDTOList
            .stream()
            .filter(r -> r.getText().equals(reviewDTO.getText()))
            .findFirst()
            .orElse(new ReviewDTO());

        assertThat(reviewDTOList.size(), is(3));
        assertThat(result.getUserId(), is(reviewDTO.getUserId()));
        assertThat(result.getText(), is(reviewDTO.getText()));

        reviewService.delete(result.getId());
    }

    @Test
    public void whenFindAll() throws Exception {
        this.mockMvc.perform(get("/api/review"))
            .andExpect(status().isOk())
            .andExpect(content()
                .json(
                    "[{\"id\":1,\"userId\":2,\"userLogin\":\"user\",\"created\":\"2021-01-01T00:00:00.000+00:00\",\"text\":\"text1\"}," +
                    "{\"id\":2,\"userId\":2,\"userLogin\":\"user\",\"created\":\"2021-01-01T00:00:00.000+00:00\",\"text\":\"text2\"}]")
            );
    }
}
