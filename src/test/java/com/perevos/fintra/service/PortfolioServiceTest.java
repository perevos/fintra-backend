package com.perevos.fintra.service;

import com.perevos.fintra.dto.PortfolioOverviewDto;
import com.perevos.fintra.entity.Portfolio;
import com.perevos.fintra.exception.ResourceNotFoundException;
import com.perevos.fintra.mapper.PortfolioMapper;
import com.perevos.fintra.repository.PortfolioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PortfolioServiceTest {

    private static final long PORTFOLIO_1_ID = 1L;
    private static final long PORTFOLIO_2_ID = 2L;
    private static final long INVALID_PORTFOLIO_ID = 99L;

    @Mock
    private PortfolioRepository portfolioRepository;

    @Mock
    private PortfolioMapper portfolioMapper;

    @InjectMocks
    private PortfolioService portfolioService;

    @Test
    void getAllPortfolios_shouldReturnEmptyList_whenNoPortfolios() {
        //arrange
        when(portfolioRepository.findAll()).thenReturn(List.of());

        // act
        List<PortfolioOverviewDto> result = portfolioService.getAllPortfolios();

        // assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(portfolioRepository, times(1)).findAll();
        verifyNoInteractions(portfolioMapper);
    }

    @Test
    void getAllPortfolios_shouldReturnDtoList() {
        // arrange
        Portfolio portfolio1 = Portfolio.builder().id(PORTFOLIO_1_ID).build();
        Portfolio portfolio2 = Portfolio.builder().id(PORTFOLIO_2_ID).build();

        when(portfolioRepository.findAll()).thenReturn(List.of(portfolio1, portfolio2));

        // act
        List<PortfolioOverviewDto> result = portfolioService.getAllPortfolios();

        // assert
        assertNotNull(result);
        assertEquals(2, result.size());

        verify(portfolioRepository, times(1)).findAll();
        verify(portfolioMapper, times(1)).toPortfolioOverviewDto(portfolio1);
        verify(portfolioMapper, times(1)).toPortfolioOverviewDto(portfolio2);
    }

    @Test
    void getPortfolioDetails_shouldReturnDto_whenPortfolioExists() {
        // arrange
        Portfolio portfolio = Portfolio.builder().id(PORTFOLIO_1_ID).build();
        when(portfolioRepository.findById(PORTFOLIO_1_ID)).thenReturn(Optional.of(portfolio));

        // act
        portfolioService.getPortfolioDetails(PORTFOLIO_1_ID);

        verify(portfolioRepository, times(1)).findById(PORTFOLIO_1_ID);
        verify(portfolioMapper, times(1)).toPortfolioDetailsDto(portfolio);
    }

    @Test
    void getPortfolioDetails_shouldThrow_whenPortfolioNotFound() {
        // arrange
        when(portfolioRepository.findById(INVALID_PORTFOLIO_ID)).thenReturn(Optional.empty());

        // act && assert
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class,
                () -> portfolioService.getPortfolioDetails(INVALID_PORTFOLIO_ID));

        assertEquals("Portfolio not found with id: " + INVALID_PORTFOLIO_ID, ex.getMessage());
        verify(portfolioRepository).findById(INVALID_PORTFOLIO_ID);
        verifyNoInteractions(portfolioMapper);
    }
}
