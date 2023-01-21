package com.github.vvserdiuk.citytickets.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TravelCard {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private OffsetDateTime start;
    private OffsetDateTime end;

    @Column(name = "allowed_transport")
    @ElementCollection
    @CollectionTable
    private Set<Transport> allowedTransport;
}
