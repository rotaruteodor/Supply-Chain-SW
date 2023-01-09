package bGroup.SupplyChainSWbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "shipments")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = Order.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "departure_date")
    private LocalDateTime departureDate;

    @Column(name = "deliver_date")
    private LocalDateTime deliverDate;

    @ManyToOne(targetEntity = MeanOfTransport.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "mean_of_transport_id")
    private MeanOfTransport meanOfTransport;

}
