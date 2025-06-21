package com.perevos.fintra.service;

import com.perevos.fintra.dto.PortfolioOverviewDto;
import com.perevos.fintra.mapper.PortfolioMapper;
import com.perevos.fintra.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final PortfolioMapper portfolioMapper;

    public List<PortfolioOverviewDto> getAllPortfolios() {
        return portfolioRepository.findAll()
                .stream()
                .map(portfolioMapper::toDto)
                .toList();
    }
}
