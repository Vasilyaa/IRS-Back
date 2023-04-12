package net.redciscso.javapmproject.domain;

import lombok.Getter;
import lombok.Setter;
import net.redciscso.javapmproject.domain.testing.Test;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "header")
public class Header {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        return "Header{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", commentaries=" + commentaries +
                ", createTime=" + createTime +
                ", article=" + article +
                '}';
    }

    @OneToMany(mappedBy = "header",cascade = CascadeType.ALL)
    private List<Commentary> commentaries;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @CreationTimestamp
    @Column(name = "create_time")
    private Date createTime;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", referencedColumnName = "id")
    private Article article;

    @OneToMany(mappedBy = "header",cascade = CascadeType.ALL)
    private List<Test> tests;

}
