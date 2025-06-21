package com.perevos.fintra.controller;

import com.perevos.fintra.dto.PortfolioAssetDto;
import com.perevos.fintra.dto.PortfolioDetailsDto;
import com.perevos.fintra.dto.PortfolioOverviewDto;
import com.perevos.fintra.exception.ResourceNotFoundException;
import com.perevos.fintra.service.PortfolioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PortfolioController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PortfolioControllerTest {

    private static final long GROWTH_PORTFOLIO_ID = 1L;
    private static final String GROWTH_PORTFOLIO_NAME = "Growth";
    private static final String GROWTH_PORTFOLIO_DESCRIPTION = "Growth portfolio";

    private static final long INCOME_PORTFOLIO_ID = 2L;
    private static final String INCOME_PORTFOLIO_NAME = "Income";
    private static final String INCOME_PORTFOLIO_DESCRIPTION = "Income portfolio";

    private static final long INVALID_PORTFOLIO_ID = 3L;

    private static final long APPL_ASSET_ID = 100L;
    private static final String APPL_TICKER = "APPL";
    private static final double APPL_QUANTITY = 10.0;
    private static final double APPL_PURCHASE_PRICE = 120.5;


    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PortfolioService portfolioService;

    @Test
    void getAllPortfolios_shouldReturnJsonList() throws Exception {
        // arrange
        List<PortfolioOverviewDto> mockResponse = List.of(
                PortfolioOverviewDto.builder()
                        .id(GROWTH_PORTFOLIO_ID)
                        .name(GROWTH_PORTFOLIO_NAME)
                        .description(GROWTH_PORTFOLIO_DESCRIPTION)
                        .build(),
                PortfolioOverviewDto.builder()
                        .id(INCOME_PORTFOLIO_ID)
                        .name(INCOME_PORTFOLIO_NAME)
                        .description(INCOME_PORTFOLIO_DESCRIPTION)
                        .build()
        );

        when(portfolioService.getAllPortfolios()).thenReturn(mockResponse);

        // act & assert
        mockMvc.perform(get("/api/portfolio"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(GROWTH_PORTFOLIO_ID))
                .andExpect(jsonPath("$[0].name").value(GROWTH_PORTFOLIO_NAME))
                .andExpect(jsonPath("$[0].description").value(GROWTH_PORTFOLIO_DESCRIPTION))
                .andExpect(jsonPath("$[1].id").value(INCOME_PORTFOLIO_ID))
                .andExpect(jsonPath("$[1].name").value(INCOME_PORTFOLIO_NAME))
                .andExpect(jsonPath("$[1].description").value(INCOME_PORTFOLIO_DESCRIPTION));

    }

    @Test
    void getPortfolioDetails_shouldReturnJsonWithAssetsList() throws Exception {
        // arrange
        PortfolioDetailsDto mockResponse = PortfolioDetailsDto.builder()
                .id(GROWTH_PORTFOLIO_ID)
                .name(GROWTH_PORTFOLIO_NAME)
                .description(GROWTH_PORTFOLIO_DESCRIPTION)
                .assets(List.of(
                        PortfolioAssetDto.builder()
                                .id(APPL_ASSET_ID)
                                .ticker(APPL_TICKER)
                                .quantity(APPL_QUANTITY)
                                .purchasePrice(APPL_PURCHASE_PRICE)
                                .build()
                ))
                .build();

        when(portfolioService.getPortfolioDetails(GROWTH_PORTFOLIO_ID)).thenReturn(mockResponse);

        // act && assert
        mockMvc.perform(get("/api/portfolio/" + GROWTH_PORTFOLIO_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(GROWTH_PORTFOLIO_ID))
                .andExpect(jsonPath("$.name").value(GROWTH_PORTFOLIO_NAME))
                .andExpect(jsonPath("$.description").value(GROWTH_PORTFOLIO_DESCRIPTION))
                .andExpect(jsonPath("$.assets.length()").value(1))
                .andExpect(jsonPath("$.assets[0].id").value(APPL_ASSET_ID))
                .andExpect(jsonPath("$.assets[0].ticker").value(APPL_TICKER))
                .andExpect(jsonPath("$.assets[0].quantity").value(APPL_QUANTITY))
                .andExpect(jsonPath("$.assets[0].purchasePrice").value(APPL_PURCHASE_PRICE));
    }

    @Test
    void getPortfolioDetails_returnsNotFoundForInvalidId() throws Exception {
        // arrange
        when(portfolioService.getPortfolioDetails(INVALID_PORTFOLIO_ID))
                .thenThrow(new ResourceNotFoundException("Portfolio not found"));

        // act && assets
        mockMvc.perform(get("/api/portfolio/" + INVALID_PORTFOLIO_ID))
                .andExpect(status().isNotFound());
    }
}
