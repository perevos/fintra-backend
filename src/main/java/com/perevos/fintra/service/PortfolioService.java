package com.perevos.fintra.service;

import com.perevos.fintra.dto.PortfolioResponseDto;
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

    public List<PortfolioResponseDto> getAllPortfolios() {
        return portfolioRepository.findAll()
                .stream()
                .map(portfolioMapper::toDto)
                .toList();
    }
}
