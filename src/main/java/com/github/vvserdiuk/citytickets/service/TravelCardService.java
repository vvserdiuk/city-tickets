package com.github.vvserdiuk.citytickets.service;

import com.github.vvserdiuk.citytickets.data.TransportRepository;
import com.github.vvserdiuk.citytickets.data.TravelCardRepository;
import com.github.vvserdiuk.citytickets.model.Transport;
import com.github.vvserdiuk.citytickets.model.TravelCard;
import com.github.vvserdiuk.citytickets.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class TravelCardService {

    private TravelCardRepository travelCardRepository;
    private TransportRepository transportRepository;

    private UserService userService;

    @Transactional
    public void buyTravelCard(User user, Transport transport) {
        BigDecimal travelCardPrice = getPrice(transport);
        if (user.getBalance().compareTo(travelCardPrice) < 0 ) {
            log.info("Not enough balance");
            return;
        }

        user.setBalance(user.getBalance().subtract(travelCardPrice));
        TravelCard travelCard = createTravelCard(Set.of(transport));
        user.setTravelCards(Set.of(travelCard));
        userService.save(user);
        travelCardRepository.save(travelCard);
    }

    private TravelCard createTravelCard(Set<Transport> transport) {
        TravelCard travelCard = TravelCard.builder()
                .start(OffsetDateTime.now())
                .end(OffsetDateTime.now().plusMonths(1))
                .allowedTransport(transport)
                .build();

        return travelCardRepository.save(travelCard);
    }

    private BigDecimal getPrice(Transport transport) {
         return transportRepository.findById(transport.ordinal()).orElseThrow().getPrice();
    }

    public boolean isValid(TravelCard travelCard) {
        return travelCard.getEnd().isAfter(OffsetDateTime.now());
    }
}
