package org.dmace.store.model.bean;

import javax.validation.constraints.NotNull;

public class PostBean {

    @NotNull
    private String title;

    private String url;

    @NotNull
    private String content;

    public PostBean() {
    }

    public PostBean(String title, String url, String content) {
        this.title = title;
        this.url = url;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
