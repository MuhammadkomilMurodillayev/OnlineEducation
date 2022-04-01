package uz.mm.entity.courses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.mm.entity.base.Auditable;
import uz.mm.enums.types.CommentType;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "course_comment_type_index", columnList = "type"),
        @Index(name = "course_comment_created_by_index", columnList = "createdBy"),
        @Index(name = "course_comment_parent_id_index", columnList = "parentId"),
        @Index(name = "course_comment_course_id_index", columnList = "courseId"),
        @Index(name = "course_comment_status_index", columnList = "status")

})
public class CourseComment extends Auditable {
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String createdBy;

    @Column(nullable = true)
    private String parentId; //this might be courseId or commentId

    @Column(nullable = false)
    private String courseId;

    @Enumerated(EnumType.STRING)
    private CommentType type;
}
