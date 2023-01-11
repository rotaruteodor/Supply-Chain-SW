package bGroup.SupplyChainSWbackend.dtos;

import bGroup.SupplyChainSWbackend.entities.Address;
import bGroup.SupplyChainSWbackend.entities.enums.MeanOfTransportStatus;
import bGroup.SupplyChainSWbackend.entities.enums.MeanOfTransportType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MeanOfTransportDto {

    private Long id;
    private String identificationNumber;
    private MeanOfTransportType type;
    private Address address;
    private MeanOfTransportStatus status;
}
