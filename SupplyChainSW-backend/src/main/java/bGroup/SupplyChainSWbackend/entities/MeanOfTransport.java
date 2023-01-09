package bGroup.SupplyChainSWbackend.entities;

import bGroup.SupplyChainSWbackend.entities.enums.CompanyRole;
import bGroup.SupplyChainSWbackend.entities.enums.MeanOfTransportStatus;
import bGroup.SupplyChainSWbackend.entities.enums.MeanOfTransportType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "means_of_transport")
public class MeanOfTransport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identification_number")
    private String identificationNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private MeanOfTransportType type;

    @OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private MeanOfTransportStatus status;

}

