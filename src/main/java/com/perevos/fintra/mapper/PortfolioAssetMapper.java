package com.perevos.fintra.mapper;

import com.perevos.fintra.dto.PortfolioAssetDto;
import com.perevos.fintra.entity.PortfolioAsset;
import org.springframework.stereotype.Component;

@Component
public class PortfolioAssetMapper {

    public PortfolioAssetDto toPortfolioAssetDto(PortfolioAsset portfolioAsset) {
        return PortfolioAssetDto.builder()
                .id(portfolioAsset.getId())
                .ticker(portfolioAsset.getTicker())
                .quantity(portfolioAsset.getQuantity())
                .purchasePrice(portfolioAsset.getPurchasePrice())
                .build();
    }

}
