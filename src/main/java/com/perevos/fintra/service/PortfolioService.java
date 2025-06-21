package com.perevos.fintra.service;

import com.perevos.fintra.dto.CreatePortfolioRequest;
import com.perevos.fintra.dto.PortfolioDetailsDto;
import com.perevos.fintra.dto.PortfolioOverviewDto;
import com.perevos.fintra.entity.Portfolio;
import com.perevos.fintra.exception.ResourceNotFoundException;
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
                .map(portfolioMapper::toPortfolioOverviewDto)
                .toList();
    }

    public PortfolioDetailsDto getPortfolioDetails(Long portfolioId) {
        Portfolio portfolioEntity = portfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio not found with id: " + portfolioId));
        return portfolioMapper.toPortfolioDetailsDto(portfolioEntity);
    }

    public Long createPortfolio(CreatePortfolioRequest request) {
        Portfolio portfolioEntity = portfolioMapper.toPortfolioEntity(request);
        Portfolio saved = portfolioRepository.save(portfolioEntity);
        return saved.getId();
    }
}
