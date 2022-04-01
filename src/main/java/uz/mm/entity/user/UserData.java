package uz.mm.entity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.mm.entity.base.BaseGenericEntity;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "user_data", indexes = {
        @Index(name = "user_date_user_id_index", columnList = "userId")
})
@Entity
@NoArgsConstructor
public class UserData implements BaseGenericEntity {
    @Id
    @Column(unique = true)
    private String id;

    @Column(nullable = false)
    private String userId;

    private Integer rating;
}
