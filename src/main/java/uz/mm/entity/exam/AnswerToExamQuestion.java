package uz.mm.entity.exam;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor@Table(indexes = {
        @Index(name = "answer_to_exam_question_exam_question_id_index", columnList = "examQuestionId")
})
public class AnswerToExamQuestion {
    @Id
    @Column(unique = true)
    private String id;

    @Column(nullable = false)
    private String examQuestionId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "NUMERIC default 0")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean correct;
}
