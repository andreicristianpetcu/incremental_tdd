package com.andreicristianpetcu.incrementaltdd.before.service;

import com.andreicristianpetcu.incrementaltdd.AgeMapperService;
import com.andreicristianpetcu.incrementaltdd.after.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AgeMapperServiceTest {

    private static final Long EXISTING_USER_ID = 42L;
    public static final int USER_AGE = 30;

    private AgeMapperService testSubject;
    @Mock
    private Map<Long, Integer> userIdToAgeMapperMock;
    @Mock
    private User userMock;

    @Before
    public void before(){
        testSubject = new AgeMapperService(userIdToAgeMapperMock);
        when(userIdToAgeMapperMock.get(EXISTING_USER_ID)).thenReturn(USER_AGE);
        when(userMock.getId()).thenReturn(EXISTING_USER_ID);
    }

    @Test
    public void getAgeIsPresent() throws ServletException, IOException {
        when(userIdToAgeMapperMock.containsKey(EXISTING_USER_ID)).thenReturn(true);

        int age = testSubject.getAge(userMock).get();

        assertThat(age).isEqualTo(USER_AGE);
    }

    @Test
    public void getAgeIsMissing() throws ServletException, IOException {
        when(userIdToAgeMapperMock.containsKey(EXISTING_USER_ID)).thenReturn(false);

        boolean ageIsPresent = testSubject.getAge(userMock).isPresent();

        assertFalse(ageIsPresent);
    }

}