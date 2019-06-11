package com.himalaya;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
//@WebMvcTest(com.himalaya.login.LoginController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getSessionKeys() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                .get("/getSessionKeys?sessionId=Njc1Njc4OTctMWMxOS00YTg2LWI5YTktMWFlMjhlZTIzZDVi")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.keys").exists());
    }

    @Test
    public void getSessionValue() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                .get("/getSessionValue?sessionId=Njc1Njc4OTctMWMxOS00YTg2LWI5YTktMWFlMjhlZTIzZDVi&key=userVo")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userVo.username").value("himalaya"));
    }

    @Test
    public void getSessionValues() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                .get("/getSessionValues?sessionId=Njc1Njc4OTctMWMxOS00YTg2LWI5YTktMWFlMjhlZTIzZDVi")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("sunghee"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userVo").hasJsonPath())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userVo.age").value(38));
    }

    @Test
    public void deleteSession() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                .get("/deleteSession?sessionId=Njc1Njc4OTctMWMxOS00YTg2LWI5YTktMWFlMjhlZTIzZDVi")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userVo.username").doesNotExist());
    }

}