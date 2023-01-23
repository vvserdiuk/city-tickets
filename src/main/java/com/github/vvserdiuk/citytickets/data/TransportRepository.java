package com.github.vvserdiuk.citytickets.data;

import com.github.vvserdiuk.citytickets.model.Transport;
import org.springframework.data.repository.CrudRepository;

public interface TransportRepository extends CrudRepository<Transport, Transport.Name> {
}
