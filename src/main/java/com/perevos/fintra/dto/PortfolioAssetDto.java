package com.perevos.fintra.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioAssetDto {
    private Long id;
    private String ticker;
    private Double quantity;
    private Double purchasePrice;
}
