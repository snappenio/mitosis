package com.snappenio.mitosis.codepen.service;

import com.snappenio.mitosis.codepen.model.Codepen;
import com.snappenio.mitosis.utility.url.URLReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CodepenService {

    private static final String DOT_HTML = ".html";
    private static final String DOT_CSS = ".css";
    private static final String DOT_JS = ".js";

    private URLReader urlReader;

    @Autowired
    public CodepenService(URLReader urlReader) {
        this.urlReader = urlReader;
    }

    public Optional<Codepen> retrieveCodepen(String url) {
        Optional<String> html = getHtml(url);
        Optional<String> css = getCss(url);
        Optional<String> js = getJs(url);

        if (html.isPresent() && css.isPresent() && js.isPresent()) {
            return Optional.of(new Codepen(html.get(), css.get(), js.get()));
        }
        return Optional.empty();
    }

    private Optional<String> getHtml(String url) {
        return urlReader.read(url + DOT_HTML);
    }

    private Optional<String> getCss(String url) {
        return urlReader.read(url + DOT_CSS);
    }

    private Optional<String> getJs(String url) {
        return urlReader.read(url + DOT_JS);
    }

}
