package uz.mm.repositories.course;

import org.springframework.stereotype.Repository;
import uz.mm.entity.courses.Section;
import uz.mm.repositories.base.AbstractRepository;

@Repository
public interface CourseSectionRepository extends AbstractRepository<Section, String> {

}
