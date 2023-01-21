package com.github.vvserdiuk.citytickets.data;

import com.github.vvserdiuk.citytickets.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
