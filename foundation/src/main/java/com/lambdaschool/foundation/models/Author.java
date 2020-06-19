package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long authorid;

    private String lastname;

    private String firstname;

//    @ManyToMany()
//    @JoinTable(name = "wrotes",
//        joinColumns = @JoinColumn(name = "authorid"),
//        inverseJoinColumns = @JoinColumn(name = "bookid"))
//    @JsonIgnoreProperties("authors")
//    List<Book> books = new ArrayList<>();

    @OneToMany(mappedBy = "author",
    cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "author", allowSetters = true)
    private List<Wrote> wrotes = new ArrayList<>();

    public Author() {
    }

    public Author( String firstname, String lastname) {
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public long getAuthorid() {
        return authorid;
    }

    public void setAuthorid(long authorid) {
        this.authorid = authorid;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public List<Wrote> getWrotes() {
        return wrotes;
    }

    public void setWrotes(List<Wrote> wrotes) {
        this.wrotes = wrotes;
    }
}
