package net.redciscso.javapmproject.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

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

    @CreationTimestamp
    @Column(name = "create_time")
    private Date createTime;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", referencedColumnName = "id")
    private Article article;

}
