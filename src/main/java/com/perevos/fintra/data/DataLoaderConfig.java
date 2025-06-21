package com.perevos.fintra.data;

import com.perevos.fintra.entity.Portfolio;
import com.perevos.fintra.entity.PortfolioAsset;
import com.perevos.fintra.repository.PortfolioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoaderConfig {

    /**
     * Defines a CommandLineRunner bean that runs once on application startup
     * after the Spring context has been fully initialized.
     * We return a CommandLineRunner instead of using a void method,
     * because Spring Boot automatically detects and executes all CommandLineRunner beans.
     * This ensures our data-loading logic runs at the correct lifecycle phase,
     * after all beans (including the PortfolioRepository) are ready to use.
     */
    @Bean
    public CommandLineRunner loadDummyPortfolios(PortfolioRepository portfolioRepository) {
        return args -> {
            if (portfolioRepository.count() == 0) {
                portfolioRepository.save(getGrowthPortfolio());
                portfolioRepository.save(getIncomePortfolio());
                portfolioRepository.save(getBalancedPortfolio());
            }
        };
    }

    private Portfolio getGrowthPortfolio() {
        Portfolio growthPortfolio = Portfolio.builder()
                .name("Growth portfolio")
                .description("Focused on high growth stocks")
                .build();

        growthPortfolio.getAssets().add(PortfolioAsset.builder()
                .ticker("AAPL")
                .quantity(10.0)
                .purchasePrice(190.3)
                .portfolio(growthPortfolio)
                .build());

        return growthPortfolio;
    }

    private Portfolio getIncomePortfolio() {
        Portfolio incomePortfolio = Portfolio.builder()
                .name("Income Portfolio")
                .description("Designed for steady dividend income")
                .build();

        incomePortfolio.getAssets().add(PortfolioAsset.builder()
                .ticker("JNJ")
                .quantity(20.0)
                .purchasePrice(149.99)
                .portfolio(incomePortfolio)
                .build());

        return incomePortfolio;
    }

    private Portfolio getBalancedPortfolio() {
        Portfolio balancedPortfolio = Portfolio.builder()
                .name("Balanced Portfolio")
                .description("Mix of growth and income investments")
                .build();

        balancedPortfolio.getAssets().add(PortfolioAsset.builder()
                .ticker("AAPL")
                .quantity(5.0)
                .purchasePrice(190.3)
                .portfolio(balancedPortfolio)
                .build());

        balancedPortfolio.getAssets().add(PortfolioAsset.builder()
                .ticker("JNJ")
                .quantity(6.0)
                .purchasePrice(149.99)
                .portfolio(balancedPortfolio)
                .build());

        return balancedPortfolio;
    }
}
