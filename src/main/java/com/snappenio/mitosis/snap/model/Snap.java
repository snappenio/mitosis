package com.snappenio.mitosis.snap.model;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

public class Snap {

    @Id
    private String snapId;

    private String html;

    private String css;

    private String js;

    @CreatedDate
    private DateTime createdAt;

    @LastModifiedDate
    private DateTime lastModified;

    public Snap(String html, String css, String js) {
        this.html = html;
        this.css = css;
        this.js = js;
    }

    public String getSnapId() {
        return snapId;
    }
    public void setSnapId(String snapId) {
        this.snapId = snapId;
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

    public DateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    public DateTime getLastModified() {
        return lastModified;
    }
    public void setLastModified(DateTime lastModified) {
        this.lastModified = lastModified;
    }

}
