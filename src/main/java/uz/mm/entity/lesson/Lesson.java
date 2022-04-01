package uz.mm.entity.lesson;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.mm.entity.base.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "lesson_status_index", columnList = "status"),
        @Index(name = "lesson_section_id_index", columnList = "section_id"),
        @Index(name = "lesson_created_by_index", columnList = "created_by")
})
public class Lesson extends Auditable {
    @Column(name = "section_id", nullable = false)
    private String sectionId;

    @Column(nullable = false)
    private Short position;

    @Column(nullable = false)
    private String content;

    @Column(name = "video_url", nullable = false)
    private String videoUrl;

    @Column(name = "created_by", nullable = false)
    private String createdBy;
}
