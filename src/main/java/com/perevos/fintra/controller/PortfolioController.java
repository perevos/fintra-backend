package com.perevos.fintra.controller;

import com.perevos.fintra.dto.PortfolioDetailsDto;
import com.perevos.fintra.dto.PortfolioOverviewDto;
import com.perevos.fintra.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
@RequiredArgsConstructor
public class PortfolioController {

    private final PortfolioService portfolioService;

    @GetMapping
    public List<PortfolioOverviewDto> getAllPortfolios() {
        return portfolioService.getAllPortfolios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PortfolioDetailsDto> getPortfolioDetails(@PathVariable Long id) {
        PortfolioDetailsDto portfolioDetails = portfolioService.getPortfolioDetails(id);
        return ResponseEntity.ok(portfolioDetails);
    }

}
