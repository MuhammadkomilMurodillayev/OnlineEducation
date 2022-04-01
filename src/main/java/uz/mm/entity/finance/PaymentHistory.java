package uz.mm.entity.finance;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.mm.entity.base.Auditable;
import uz.mm.enums.Currency;
import uz.mm.enums.types.PaymentType;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "payment_history_status_index", columnList = "status"),
        @Index(name = "payment_history_user_id_index", columnList = "userId"),
        @Index(name = "payment_history_type_index", columnList = "type")
})
public class PaymentHistory extends Auditable {
    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private Double amount;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private String paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentType type;
}
