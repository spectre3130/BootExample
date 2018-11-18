package com.spectre.example.journal;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String summary;

    private Date created;

    @Transient
    @JsonIgnore
    private SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

    public Journal(String title, String summary, String created) throws ParseException {
        this.title = title;
        this.summary = summary;
        this.created = format.parse(created);
    }

}
