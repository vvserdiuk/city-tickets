package com.github.vvserdiuk.citytickets.service;

import com.github.vvserdiuk.citytickets.data.TransportRepository;
import com.github.vvserdiuk.citytickets.data.TravelCardRepository;
import com.github.vvserdiuk.citytickets.model.Transport;
import com.github.vvserdiuk.citytickets.model.TravelCard;
import com.github.vvserdiuk.citytickets.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TravelCardServiceTest {

    @Mock
    private TravelCardRepository travelCardRepository;
    @Mock
    private TransportRepository transportRepository;
    @Mock
    private UserService userService;

    @Captor
    ArgumentCaptor<TravelCard> travelCardCaptor;
    private TravelCardService travelCardService;

    @BeforeEach
    void setUp() {
        travelCardService = new TravelCardService(travelCardRepository, transportRepository, userService);
    }

    @Test
    public void shouldNotProceedWhenTryBuyButInsufficientBalance() {
        // setup
        var user = User.builder()
                .id(1L)
                .balance(BigDecimal.ONE)
                .build();

        var transport = new Transport(Transport.Name.BUS, BigDecimal.TEN);
        when(transportRepository.findById(Transport.Name.BUS)).thenReturn(Optional.of(transport));

        // act
        travelCardService.buyTravelCard(user, transport);

        // assert
        verify(userService, never()).save(user);
        verify(transportRepository, never()).save(any());
    }

    @Test
    public void shouldBuyTicket() {
        // setup
        var user = User.builder()
                .id(1L)
                .balance(BigDecimal.TEN)
                .build();

        var transport = new Transport(Transport.Name.BUS, BigDecimal.ONE);
        when(transportRepository.findById(Transport.Name.BUS)).thenReturn(Optional.of(transport));

        // act
        travelCardService.buyTravelCard(user, transport);

        // assert
        verify(userService).save(user);
        verify(travelCardRepository).save(any());
    }

    @Test
    public void shouldBuyTravelCardForMonth() {
        // setup
        var user = User.builder()
                .id(1L)
                .balance(BigDecimal.TEN)
                .build();

        var transport = new Transport(Transport.Name.BUS, BigDecimal.ONE);
        when(transportRepository.findById(Transport.Name.BUS)).thenReturn(Optional.of(transport));

        // act
        travelCardService.buyTravelCard(user, transport);

        // assert
        verify(userService).save(user);
        verify(travelCardRepository).save(travelCardCaptor.capture());
        TravelCard createdTravelCard = travelCardCaptor.getValue();
        assertThat(createdTravelCard.getEnd().minusMonths(1)).isEqualTo(createdTravelCard.getStart());
    }
}