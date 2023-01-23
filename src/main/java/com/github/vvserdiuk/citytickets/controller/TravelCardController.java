package com.github.vvserdiuk.citytickets.controller;

import com.github.vvserdiuk.citytickets.data.TravelCardRepository;
import com.github.vvserdiuk.citytickets.model.Transport;
import com.github.vvserdiuk.citytickets.model.TravelCard;
import com.github.vvserdiuk.citytickets.model.User;
import com.github.vvserdiuk.citytickets.service.TravelCardService;
import com.github.vvserdiuk.citytickets.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class TravelCardController {

    private final UserService userService;
    private final TravelCardService travelCardService;

    private final TravelCardRepository travelCardRepository;

    @GetMapping(path = "/buy")
    public String buyTicketCard(Long userId, Transport transport) {
        Optional<User> user = userService.findUser(userId);
        if (user.isEmpty()) {
            return "Bad request";
        }

        travelCardService.buyTravelCard(user.get(), transport);
        return "Ok";
    }

    @GetMapping(path = "/test")
    public String test() {
        Transport transport = new Transport();
        transport.setName(Transport.Name.BUS);
        transport.setPrice(BigDecimal.TEN);
        TravelCard travelCard = TravelCard.builder()
                .transport(transport)
                .end(OffsetDateTime.now())
                .start(OffsetDateTime.now())
                .build();

        return travelCardRepository.save(travelCard).toString();
    }
}
