package com.perevos.fintra.mapper;

import com.perevos.fintra.dto.PortfolioResponseDto;
import com.perevos.fintra.entity.Portfolio;
import org.springframework.stereotype.Component;

@Component
public class PortfolioMapper {

    public PortfolioResponseDto toDto(Portfolio portfolio) {
        return PortfolioResponseDto.builder()
                .id(portfolio.getId())
                .name(portfolio.getName())
                .description(portfolio.getDescription())
                .build();
    }
}
