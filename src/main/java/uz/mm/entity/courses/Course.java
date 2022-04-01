package uz.mm.entity.courses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.mm.entity.base.Auditable;
import uz.mm.enums.CourseCategory;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "courses", indexes = {
        @Index(name = "course_name_index", columnList = "name"),
        @Index(name = "course_created_by_index", columnList = "createdBy"),
        @Index(name = "course_category_index", columnList = "category"),
        @Index(name = "course_status_index", columnList = "status")

})
@Entity
@NoArgsConstructor
public class Course extends Auditable {
    @Column(nullable = false)
    private String name;

    private String description;

    private Integer purchaseCount;

    @Column(nullable = false)
    private Double price;

    private Integer rating;

    private Integer ratingCount;

    @Column(nullable = false)
    private String createdBy;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CourseCategory category;

    private Short duration; // -> sertifikat olish uchun muddat

}
