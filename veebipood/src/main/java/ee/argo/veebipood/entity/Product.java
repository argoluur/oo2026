package ee.argo.veebipood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Boolean active;
    private Integer stock;

    // @ManyToMany --> private List<Items> items
    // @OneToMany --> private List<Items> items
    // @ManyToOne --> tooted jagavad seda kategooriat
    // @OneToOne --> tooted ei jaga seda kategooriat
    @ManyToOne

    private Category category; // automaatselt võõrvõtmega @Id väljaga siia tabelisse

}
