package uz.mm.entity.file;

import lombok.Getter;
import lombok.Setter;
import uz.mm.entity.base.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class FileStorage extends Auditable {

    @Column(nullable = false)
    String lessonId;

    @Column(nullable = false)
    String path;

    @Column(nullable = false)
    String originalName;

    @Column(nullable = false)
    String generatedName;

    String type;

}
