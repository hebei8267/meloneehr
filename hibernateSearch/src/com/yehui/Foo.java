package com.yehui;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@Entity
@Table(name = "search_foo")
@Indexed(index = "indexes/foo")
public class Foo {
    @Id
    @DocumentId
    @Field(name = "id", index = Index.TOKENIZED, store = Store.YES)
    private Integer id;

    @Column(nullable = false, length = 200)
    @Field(name = "name", index = Index.TOKENIZED, store = Store.YES)
    private String name;

    @Column(nullable = false, length = 200)
    @Field(name = "title", index = Index.TOKENIZED, store = Store.YES)
    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
