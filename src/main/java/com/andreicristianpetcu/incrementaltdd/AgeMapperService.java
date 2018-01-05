package com.andreicristianpetcu.incrementaltdd;

import com.andreicristianpetcu.incrementaltdd.after.model.User;
import java8.util.Optional;

import java.util.Map;

public class AgeMapperService {

    private final Map<Long, Integer> userIdToAgeMapper;

    public AgeMapperService(Map<Long, Integer> userIdToAgeMapper) {
        this.userIdToAgeMapper = userIdToAgeMapper;
    }

    public Optional<Integer> getAge(User user){
        if(userIdToAgeMapper.containsKey(user.getId())){
            return Optional.of(userIdToAgeMapper.get(user.getId()));
        } else {
            return Optional.empty();
        }
    }

}
