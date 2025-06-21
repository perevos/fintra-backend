package com.perevos.fintra.mapper;

import com.perevos.fintra.dto.PortfolioAssetDto;
import com.perevos.fintra.entity.PortfolioAsset;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PortfolioAssetMapperTest {

    private static final long PORTFOLIO_ASSET_ID = 1L;
    private static final String PORTFOLIO_ASSET_TICKER = "APPL";
    private static final double PORTFOLIO_ASSET_QUANTITY = 10.0;
    private static final double PORTFOLIO_ASSET_PURCHASE_PRICE = 120.5;

    private final PortfolioAssetMapper assetMapper = new PortfolioAssetMapper();

    @Test
    void toPortfolioAssetDto_shouldMapToDtoCorrectly() {
        // arrange
        PortfolioAsset asset = PortfolioAsset.builder()
                .id(PORTFOLIO_ASSET_ID)
                .ticker(PORTFOLIO_ASSET_TICKER)
                .quantity(PORTFOLIO_ASSET_QUANTITY)
                .purchasePrice(PORTFOLIO_ASSET_PURCHASE_PRICE)
                .build();

        // act
        PortfolioAssetDto dto = assetMapper.toPortfolioAssetDto(asset);

        // assert
        assertNotNull(dto);
        assertEquals(PORTFOLIO_ASSET_ID, dto.getId());
        assertEquals(PORTFOLIO_ASSET_TICKER, dto.getTicker());
        assertEquals(PORTFOLIO_ASSET_QUANTITY, dto.getQuantity());
        assertEquals(PORTFOLIO_ASSET_PURCHASE_PRICE, dto.getPurchasePrice());
    }
}
