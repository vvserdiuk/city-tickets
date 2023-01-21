package com.github.vvserdiuk.citytickets.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
public enum Transport {
    BUS, TROLLEY, TRAM, METRO,
    /**
     * Stub value to store price for universal travel card
     */
    ALL;

    private BigDecimal price;

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
