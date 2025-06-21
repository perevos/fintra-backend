package com.perevos.fintra.controller;

import com.perevos.fintra.dto.PortfolioOverviewDto;
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

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PortfolioService portfolioService;

    @Test
    void getAllPortfolios_shouldReturnJsonList() throws Exception {
        // arrange
        List<PortfolioOverviewDto> mockResponse = List.of(
                PortfolioOverviewDto.builder()
                        .id(1L)
                        .name("Growth")
                        .description("Growth portfolio")
                        .build(),
                PortfolioOverviewDto.builder()
                        .id(2L)
                        .name("Income")
                        .description("Income portfolio")
                        .build()
        );

        when(portfolioService.getAllPortfolios()).thenReturn(mockResponse);

        // act & assert
        mockMvc.perform(get("/api/portfolio"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Growth"))
                .andExpect(jsonPath("$[0].description").value("Growth portfolio"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("Income"))
                .andExpect(jsonPath("$[1].description").value("Income portfolio"));

    }
}
