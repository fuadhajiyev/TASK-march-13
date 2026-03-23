package az.company.training.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal price;
    private Integer quantity;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void onPrePersist() {
        // Bu metod obyekt TRANSIENT-dən PERSISTENT vəziyyətinə keçəndə işə düşür
        this.createdAt = LocalDateTime.now();
        System.out.println("Hibernate: Obyekt indi PERSISTENT (Managed) oldu.");
    }


    // toString metodunu əlavə edirik ki, konsolda nəticəni qəşəng görək
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}