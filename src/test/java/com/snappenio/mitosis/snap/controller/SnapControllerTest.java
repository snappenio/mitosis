package com.snappenio.mitosis.snap.controller;

import com.snappenio.mitosis.codepen.model.Codepen;
import com.snappenio.mitosis.codepen.service.CodepenService;
import com.snappenio.mitosis.snap.model.Snap;
import com.snappenio.mitosis.snap.service.SnapService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SnapController.class)
public class SnapControllerTest {

    @MockBean
    private CodepenService codepenService;

    @MockBean
    private SnapService snapService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void testGetSnap() throws Exception {
        Snap snap = new Snap("html", "css", "js");
        snap.setSnapId("1");

        when(snapService.getSnap("1")).thenReturn(Optional.of(snap));

        mvc.perform(MockMvcRequestBuilders.get("/snap/{snapId}", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.snap_id", is("1")))
                .andExpect(jsonPath("$.html", is("html")))
                .andExpect(jsonPath("$.css", is("css")))
                .andExpect(jsonPath("$.js", is("js")));
    }

    @Test
    public void testGetNotExistingSnap() throws Exception {
        when(snapService.getSnap("1")).thenReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders.get("/snap/{snapId}", "1"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testCreateSnap() throws Exception {
        final String url = "http://localhost:8080/codepen";

        when(codepenService.retrieveCodepen(url)).thenReturn(Optional.of(new Codepen("html", "css", "js")));
        when(snapService.insertSnap(Matchers.any())).thenReturn("abc123");

        mvc.perform(MockMvcRequestBuilders.post("/snap")
                                          .contentType(MediaType.APPLICATION_JSON_UTF8)
                                          .content("{\"codepen_url\":\"http://localhost:8080/codepen\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.snap_id", is("abc123")));
    }

    @Test
    public void testFailedCreateSnap() throws Exception {
        final String url = "http://localhost:8080/codepen";

        when(codepenService.retrieveCodepen(url)).thenReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders.post("/snap")
                                          .contentType(MediaType.APPLICATION_JSON_UTF8)
                                          .content("{\"codepen_url\":\"http://localhost:8080/codepen\"}"))
                .andExpect(status().is4xxClientError());
    }

}
