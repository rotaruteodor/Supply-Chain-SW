package bGroup.SupplyChainSWbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobTitleDto {

    private Long id;
    private String name;
    private String description;
}
