package net.redciscso.javapmproject.domain.testing;

import lombok.Getter;
import lombok.Setter;
import net.redciscso.javapmproject.domain.Header;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "test")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name ="name")
    private String name;

    @Column(name = "try_count")
    private Integer tryCount;

    @ManyToOne
    @JoinColumn(name = "header_id")
    private Header header;

    @OneToMany(mappedBy = "test",cascade = CascadeType.ALL)
    private List<Question> questions;

    @OneToMany(mappedBy = "test",cascade = CascadeType.ALL)
    private List<Session> sessions;

}
