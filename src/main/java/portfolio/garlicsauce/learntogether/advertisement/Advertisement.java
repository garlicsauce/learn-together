package portfolio.garlicsauce.learntogether.advertisement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import portfolio.garlicsauce.learntogether.student.Student;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    @SequenceGenerator(name = "id_seq", sequenceName = "hibernate_sequence")
    private long id;

    private String university;
    private String subject;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", updatable = false, nullable = false)
    private Student author;
}
