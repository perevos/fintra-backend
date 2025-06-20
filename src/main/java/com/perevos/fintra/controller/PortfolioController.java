package com.perevos.fintra.controller;

import com.perevos.fintra.dto.PortfolioResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {

    @GetMapping
    public List<PortfolioResponseDto> getAllPortfolios() {
        return List.of(
                PortfolioResponseDto.builder()
                        .id(1L)
                        .name("My private portfolio")
                        .description("Sample description")
                        .build()
        );
    }

}
