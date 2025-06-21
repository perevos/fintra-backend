package com.perevos.fintra.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class CreatePortfolioRequest {
    @EqualsAndHashCode.Include
    private String name;

    @EqualsAndHashCode.Include
    private String description;
}
