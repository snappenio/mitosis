package com.snappenio.mitosis.codepen.model;

public class Codepen {

    private String html;
    private String css;
    private String js;

    public Codepen(String html, String css, String js) {
        this.html = html;
        this.css = css;
        this.js = js;
    }

    public String getHtml() {
        return html;
    }
    public void setHtml(String html) {
        this.html = html;
    }

    public String getCss() {
        return css;
    }
    public void setCss(String css) {
        this.css = css;
    }

    public String getJs() {
        return js;
    }
    public void setJs(String js) {
        this.js = js;
    }

}
