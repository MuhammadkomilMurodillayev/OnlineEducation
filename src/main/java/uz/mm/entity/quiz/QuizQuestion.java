package uz.mm.entity.quiz;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.mm.entity.base.Auditable;
import uz.mm.enums.types.QuestionType;

import javax.persistence.*;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "quiz_question_status_index", columnList = "status"),
        @Index(name = "quiz_question_quiz_id_index", columnList = "quizId")
})
public class QuizQuestion extends Auditable {
    @Column(nullable = false)
    private String quizId;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @Column(nullable = false)
    private Short mark;
}
