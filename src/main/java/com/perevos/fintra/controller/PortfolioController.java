package com.perevos.fintra.controller;

import com.perevos.fintra.dto.PortfolioResponseDto;
import com.perevos.fintra.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
@RequiredArgsConstructor
public class PortfolioController {

    private final PortfolioService portfolioService;

    @GetMapping
    public List<PortfolioResponseDto> getAllPortfolios() {
        return portfolioService.getAllPortfolios();
    }

}
