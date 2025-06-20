package com.perevos.fintra.mapper;

import com.perevos.fintra.dto.PortfolioResponseDto;
import com.perevos.fintra.entity.Portfolio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PortfolioMapperTest {

    private final PortfolioMapper portfolioMapper = new PortfolioMapper();

    @Test
    void toDto_shouldMapToDtoCorrectly() {
        // given
        Portfolio portfolio = Portfolio.builder()
                .id(1L)
                .name("Test Portfolio")
                .description("Sample description")
                .build();

        // when
        PortfolioResponseDto dto = portfolioMapper.toDto(portfolio);

        // then
        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Test Portfolio", dto.getName());
        assertEquals("Sample description", dto.getDescription());
    }
}
