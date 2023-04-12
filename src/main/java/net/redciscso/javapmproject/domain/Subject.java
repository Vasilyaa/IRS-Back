package net.redciscso.javapmproject.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name ="name")
    private String name;

    @Column(name = "semester")
    private Integer semester;

    @Column(name = "icon")
    private String icon;


    @OneToMany(mappedBy = "subject",cascade = CascadeType.ALL)
    private List<Header> headers;

}
