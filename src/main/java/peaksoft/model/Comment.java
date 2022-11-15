package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @SequenceGenerator(name = "comment_seq",
            sequenceName = "comment_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "comment_seq")
    private Long id;
    @Column(length = 10000)
    private String text;

    @ManyToOne
    private House house;
}
