package com.github.vvserdiuk.citytickets.data;

import com.github.vvserdiuk.citytickets.model.TravelCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelCardRepository extends CrudRepository<TravelCard, Long> {
}
