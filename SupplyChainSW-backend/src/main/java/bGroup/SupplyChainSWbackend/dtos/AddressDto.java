package bGroup.SupplyChainSWbackend.dtos;

import bGroup.SupplyChainSWbackend.entities.City;
import bGroup.SupplyChainSWbackend.entities.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {

    private Long id;
    private Country country;
    private City city;
    private String locationInfo; // street, building name & number
    private Integer zipCode;
}
