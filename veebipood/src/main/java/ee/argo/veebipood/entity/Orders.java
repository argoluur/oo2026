package ee.argo.veebipood.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date created;
    private double total;
    private String parcelMachine;

    @ManyToOne
    private  Person person;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrdersRow> ordersRows;

}