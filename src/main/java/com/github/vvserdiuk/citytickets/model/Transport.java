package com.github.vvserdiuk.citytickets.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transport {

    @Id
    @JoinColumn(name = "transport")
    @Enumerated(EnumType.STRING)
    private Name name;
    private BigDecimal price;


    public enum Name {
        BUS, TROLLEY, TRAM, METRO,
        /**
         * Stub value to store price for universal travel card
         */
        ALL;
    }
}
