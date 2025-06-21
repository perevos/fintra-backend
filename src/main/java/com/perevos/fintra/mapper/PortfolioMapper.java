package com.perevos.fintra.mapper;

import com.perevos.fintra.dto.PortfolioDetailsDto;
import com.perevos.fintra.dto.PortfolioOverviewDto;
import com.perevos.fintra.entity.Portfolio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PortfolioMapper {

    private final PortfolioAssetMapper assetMapper;

    public PortfolioOverviewDto toPortfolioOverviewDto(Portfolio portfolio) {
        return PortfolioOverviewDto.builder()
                .id(portfolio.getId())
                .name(portfolio.getName())
                .description(portfolio.getDescription())
                .build();
    }

    public PortfolioDetailsDto toPortfolioDetailsDto(Portfolio portfolio) {
        return PortfolioDetailsDto.builder()
                .id(portfolio.getId())
                .name(portfolio.getName())
                .description(portfolio.getDescription())
                .assets(portfolio.getAssets()
                        .stream()
                        .map(assetMapper::toPortfolioAssetDto)
                        .toList())
                .build();
    }


}
