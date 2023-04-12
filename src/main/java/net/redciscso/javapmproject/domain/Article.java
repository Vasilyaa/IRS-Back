package net.redciscso.javapmproject.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "content")
    private String content;

    @OneToMany(mappedBy = "article",cascade = CascadeType.ALL)
    private List<Image> images;

    @OneToOne(mappedBy = "article", fetch = FetchType.LAZY)
    private Header header;

}
