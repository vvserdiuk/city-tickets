package com.github.vvserdiuk.citytickets.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "travel_cards")
public class TravelCard {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "start_time")
    private OffsetDateTime start;
    @Column(name = "end_time")
    private OffsetDateTime end;

    @OneToOne
    @JoinColumn(name = "transport")
    private Transport transport;
}
