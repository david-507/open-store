package org.dmace.store.model;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;

//@Entity
//@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String url;

    private String body;

    @CreatedDate
    private Timestamp created;

    @UpdateTimestamp
    private Timestamp updated;

    @ManyToOne
    private User owner;

    public Post() {
    }

    public Post(long id, String url, String body, Timestamp created, Timestamp updated) {
        this.id = id;
        this.url = url;
        this.body = body;
        this.created = created;
        this.updated = updated;
    }

    public long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getBody() {
        return body;
    }

    public Timestamp getCreated() {
        return created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }
}
