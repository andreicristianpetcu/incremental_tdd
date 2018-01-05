package com.andreicristianpetcu.incrementaltdd.after.service;

import com.andreicristianpetcu.incrementaltdd.AgeMapperService;
import com.andreicristianpetcu.incrementaltdd.after.model.User;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

public class AgeMapperServiceTest {

    private static final Long EXISTING_USER_ID = 42L;
    private static final Long MISSING_USER_ID = 43L;
    private static final int USER_AGE = 30;

    private AgeMapperService testSubject;

    @Before
    public void before(){
        Map<Long, Integer> userIdToAgeMapper = new HashMap<Long, Integer>();
        userIdToAgeMapper.put(EXISTING_USER_ID, USER_AGE);
        testSubject = new AgeMapperService(userIdToAgeMapper);
    }

    @Test
    public void getAgeIsPresent() throws ServletException, IOException {
        int age = testSubject.getAge(new User(EXISTING_USER_ID)).get();

        assertThat(age).isEqualTo(USER_AGE);
    }

    @Test
    public void getAgeIsMissing() throws ServletException, IOException {
        boolean ageIsPresent = testSubject.getAge(new User(MISSING_USER_ID)).isPresent();

        assertFalse(ageIsPresent);
    }

}