package org.poc.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Entity
@Table(schema = "public", name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Order extends BaseModel {

    @Column
    private Long orderNumber;

    @Column
    private Enum<OrderStatus> status;

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    private Long totalValue;


}
