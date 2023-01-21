package com.github.vvserdiuk.citytickets.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
public enum Transport {
    BUS, TROLLEY, TRAM, METRO;

    private BigDecimal price;
}
