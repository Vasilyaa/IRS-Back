package net.redciscso.javapmproject.domain;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "commentary")
public class Commentary {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "header_id")
    private Header header;

    @Override
    public String toString() {
        return "Commentary{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
