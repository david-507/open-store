package org.dmace.store.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

//@Entity
//@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String text;

    @CreatedDate
    private Date created;

    public Comment() {
    }

    public Comment(String text, Date created) {
        this.text = text;
        this.created = created;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Date getCreated() {
        return created;
    }
}
