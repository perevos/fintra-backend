package com.perevos.fintra.mapper;

import com.perevos.fintra.dto.PortfolioOverviewDto;
import com.perevos.fintra.entity.Portfolio;
import org.springframework.stereotype.Component;

@Component
public class PortfolioMapper {

    public PortfolioOverviewDto toDto(Portfolio portfolio) {
        return PortfolioOverviewDto.builder()
                .id(portfolio.getId())
                .name(portfolio.getName())
                .description(portfolio.getDescription())
                .build();
    }
}
