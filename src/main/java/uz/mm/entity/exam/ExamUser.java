package uz.mm.entity.exam;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.mm.entity.base.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "exam_user_status_index", columnList = "status"),
        @Index(name = "exam_user_user_id_index", columnList = "userId"),
        @Index(name = "exam_user_exam_id_index", columnList = "examId")
})
public class ExamUser extends Auditable {
    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String examId;

    private Short mark;

    private Double percentage;

    private LocalDateTime finishingTime;
}
