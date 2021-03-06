package uz.mm.entity.feedback;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.mm.entity.base.Auditable;
import uz.mm.enums.types.FeedbackType;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "feedback_status_index", columnList = "status"),
        @Index(name = "feedback_userId_index", columnList = "userId"),
        @Index(name = "feedback_type_index", columnList = "type"),
})
public class Feedback extends Auditable {
    @Column(nullable = false)
    private String userId;

    @Enumerated(EnumType.STRING)
    private FeedbackType type;

    @Column(nullable = false)
    private String message;
}
