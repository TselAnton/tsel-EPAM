package controller;

import config.AppConfig;
import config.WebConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class})
@WebAppConfiguration
public class AppControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setMockMvc() {
        mockMvc = MockMvcBuilders.standaloneSetup(new AppController()).build();
    }

    @Test
    public void testIndexViewResolver() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void testAppControllerToNormalOut() throws Exception {
        String randomNumber = String.valueOf(new Random().nextInt(100));
        mockMvc.perform(post("/message/" + randomNumber).param("string", "someMessage"))
                .andExpect(status().isOk())
                .andExpect(view().name("message"))
                .andExpect(model().attribute("string", "someMessage"))
                .andExpect(model().attribute("number", Integer.valueOf(randomNumber)))
                .andDo(print());
    }
}
