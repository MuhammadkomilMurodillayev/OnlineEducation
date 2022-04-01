package uz.mm.entity.exam;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.mm.entity.base.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "exam_title_index", columnList = "title"),
        @Index(name = "exam_course_id_index", columnList = "course_id"),
        @Index(name = "exam_status_index",columnList = "status")
})
public class Exam extends Auditable {
    @Column(name = "course_id", nullable = false)
    private String courseId;

    @Column(nullable = false)
    private Long duration; //milliseconds

    @Column(nullable = false)
    private Short questionCount;

    @Column(nullable = false)
    private Short maxMark;

    @Column(nullable = false)
    private String title;

    private String description;

}
