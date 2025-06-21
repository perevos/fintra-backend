package com.perevos.fintra.mapper;

import com.perevos.fintra.dto.PortfolioDetailsDto;
import com.perevos.fintra.dto.PortfolioOverviewDto;
import com.perevos.fintra.entity.Portfolio;
import com.perevos.fintra.entity.PortfolioAsset;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PortfolioMapperTest {

    private static final long PORTFOLIO_ID = 1L;
    private static final String PORTFOLIO_NAME = "Test Portfolio";
    private static final String PORTFOLIO_DESCRIPTION = "Sample description";
    private static final String ASSET_TICKER_1 = "AAPL";
    private static final String ASSET_TICKER_2 = "AMZN";

    @Mock
    private PortfolioAssetMapper assetMapper;

    @InjectMocks
    private PortfolioMapper portfolioMapper;

    @Test
    void toPortfolioOverviewDto_shouldMapToDtoCorrectly() {
        // arrange
        Portfolio portfolio = Portfolio.builder()
                .id(PORTFOLIO_ID)
                .name(PORTFOLIO_NAME)
                .description(PORTFOLIO_DESCRIPTION)
                .build();

        // act
        PortfolioOverviewDto dto = portfolioMapper.toPortfolioOverviewDto(portfolio);

        // assert
        assertNotNull(dto);
        assertEquals(PORTFOLIO_ID, dto.getId());
        assertEquals(PORTFOLIO_NAME, dto.getName());
        assertEquals(PORTFOLIO_DESCRIPTION, dto.getDescription());
    }

    @Test
    void toPortfolioDetailsDto_shouldMapToDtoCorrectly() {
        // arrange
        PortfolioAsset asset1 = PortfolioAsset.builder().ticker(ASSET_TICKER_1).build();
        PortfolioAsset asset2 = PortfolioAsset.builder().ticker(ASSET_TICKER_2).build();
        Portfolio portfolio = Portfolio.builder()
                .id(PORTFOLIO_ID)
                .name(PORTFOLIO_NAME)
                .description(PORTFOLIO_DESCRIPTION)
                .assets(List.of(
                        asset1,
                        asset2
                ))
                .build();

        // act
        PortfolioDetailsDto dto = portfolioMapper.toPortfolioDetailsDto(portfolio);

        // assert
        assertNotNull(dto);
        assertEquals(PORTFOLIO_ID, dto.getId());
        assertEquals(PORTFOLIO_NAME, dto.getName());
        assertEquals(PORTFOLIO_DESCRIPTION, dto.getDescription());
        assertEquals(2, dto.getAssets().size());

        verify(assetMapper, times(1)).toPortfolioAssetDto(asset1);
        verify(assetMapper, times(1)).toPortfolioAssetDto(asset2);
    }

}
