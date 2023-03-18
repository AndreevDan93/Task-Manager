package hexlet.code.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import hexlet.code.config.SpringConfigForIT;
import hexlet.code.dto.LabelDto;
import hexlet.code.model.Label;
import hexlet.code.repository.LabelRepository;
import hexlet.code.utils.TestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static hexlet.code.config.SpringConfigForIT.TEST_PROFILE;
import static hexlet.code.controller.LabelController.ID;
import static hexlet.code.controller.LabelController.LABEL_CONTROLLER_PATH;
import static hexlet.code.utils.TestUtils.BASE_URL;
import static hexlet.code.utils.TestUtils.TEST_USERNAME;
import static hexlet.code.utils.TestUtils.asJson;
import static hexlet.code.utils.TestUtils.fromJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles(TEST_PROFILE)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = SpringConfigForIT.class)
public class LabelControllerIT {
    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private TestUtils utils;

    @AfterEach
    public void clear() {
        utils.tearDown();
    }

    @Test
    public void testGetAll() throws Exception {
        utils.createNewLabel();
        final var response = utils.perform(get(BASE_URL + LABEL_CONTROLLER_PATH), TEST_USERNAME)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        final List<Label> labels = fromJson(response.getContentAsString(), new TypeReference<>() {
        });

        assertThat(labels).hasSize(1);
    }

    @Test
    public void testGetLabelById() throws Exception {
        final var response = utils.createNewLabel().andReturn().getResponse();
        Label label = fromJson(response.getContentAsString(), new TypeReference<Label>() {
        });
        final var responseGet = utils.perform(get(BASE_URL + LABEL_CONTROLLER_PATH + ID, label.getId()), TEST_USERNAME)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(responseGet.getContentAsString()).contains("new label");
    }

    @Test
    public void testUpdateLabel() throws Exception {
        final var response = utils.createNewLabel().andReturn().getResponse();
        Label label = fromJson(response.getContentAsString(), new TypeReference<Label>() {
        });
        LabelDto newLabelDto = new LabelDto("new new label");
        final var responsePut = utils.perform(put(BASE_URL + LABEL_CONTROLLER_PATH + ID, label.getId())
                                .content(asJson(newLabelDto))
                                .contentType(APPLICATION_JSON),
                        TEST_USERNAME)
                .andReturn().getResponse();

        Label expected = fromJson(responsePut.getContentAsString(), new TypeReference<Label>() {
        });

        assertTrue(labelRepository.existsById(expected.getId()));
        assertThat(expected.getName()).isEqualTo("new new label");
    }

    @Test
    public void deleteLabelById() throws Exception {
        assertThat(labelRepository.count()).isEqualTo(0);
        utils.createNewLabel();
        assertThat(labelRepository.count()).isEqualTo(1);
        Label label = labelRepository.findAll().get(0);
        utils.perform(delete(BASE_URL + LABEL_CONTROLLER_PATH + ID, label.getId()), TEST_USERNAME)
                .andExpect(status().isOk());
        assertThat(labelRepository.count()).isEqualTo(0);
    }

}
