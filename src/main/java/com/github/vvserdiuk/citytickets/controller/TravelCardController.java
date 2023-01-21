package com.github.vvserdiuk.citytickets.controller;

import com.github.vvserdiuk.citytickets.model.Transport;
import com.github.vvserdiuk.citytickets.model.TravelCard;
import com.github.vvserdiuk.citytickets.model.User;
import com.github.vvserdiuk.citytickets.service.TravelCardService;
import com.github.vvserdiuk.citytickets.service.UserService;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TravelCardController {

    private UserService userService;
    private TravelCardService travelCardService;

    public String buyTicketCard(Long userId, Transport transport) {
        Optional<User> user = userService.findUser(userId);
        if (user.isEmpty()) {
            return "Bad request";
        }

        travelCardService.buyTravelCard(user.get(), transport);
        return "Ok";
    }
}
