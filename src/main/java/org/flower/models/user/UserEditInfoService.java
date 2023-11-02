package org.flower.models.user;

import org.flower.entities.User;
import org.flower.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserEditInfoService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){return userRepository.findAll();}

}
