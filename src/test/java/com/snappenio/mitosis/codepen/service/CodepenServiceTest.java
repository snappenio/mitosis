package com.snappenio.mitosis.codepen.service;

import com.snappenio.mitosis.codepen.model.Codepen;
import com.snappenio.mitosis.utility.url.URLReader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CodepenServiceTest {

    private static final String BASE_URL = "http://localhost:8080/codepen";
    private static final String HTML_FILE_URL = BASE_URL + ".html";
    private static final String CSS_FILE_URL = BASE_URL + ".css";
    private static final String JS_FILE_URL = BASE_URL + ".js";

    @Mock
    private URLReader urlReader;

    private CodepenService codepenService;

    @Before
    public void setup() {
        codepenService = new CodepenService(urlReader);
    }

    @Test
    public void testSuccessfulCodePenRetrieval() {
        when(urlReader.read(HTML_FILE_URL)).thenReturn(Optional.of("html content"));
        when(urlReader.read(CSS_FILE_URL)).thenReturn(Optional.of("css content"));
        when(urlReader.read(JS_FILE_URL)).thenReturn(Optional.of("js content"));

        Optional<Codepen> codepenOptional = codepenService.retrieveCodepen(BASE_URL);

        assertTrue(codepenOptional.isPresent());

        Codepen codepen = codepenOptional.get();
        assertEquals(codepen.getHtml(), "html content");
        assertEquals(codepen.getCss(), "css content");
        assertEquals(codepen.getJs(), "js content");
    }

    @Test
    public void testFailedCodePenRetrievalWithAFileUnavailable() {
        when(urlReader.read(HTML_FILE_URL)).thenReturn(Optional.empty());
        when(urlReader.read(CSS_FILE_URL)).thenReturn(Optional.of("css content"));
        when(urlReader.read(JS_FILE_URL)).thenReturn(Optional.of("js content"));

        Optional<Codepen> codepenOptional = codepenService.retrieveCodepen(BASE_URL);

        assertFalse(codepenOptional.isPresent());
    }

    @Test
    public void testFailedCodePenRetrievalWithAllFilesUnavailable() {
        when(urlReader.read(anyString())).thenReturn(Optional.empty());

        Optional<Codepen> codepenOptional = codepenService.retrieveCodepen(BASE_URL);

        assertFalse(codepenOptional.isPresent());
    }

}
