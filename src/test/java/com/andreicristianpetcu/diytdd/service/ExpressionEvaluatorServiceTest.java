package com.andreicristianpetcu.diytdd.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@Ignore
public class ExpressionEvaluatorServiceTest {

    @InjectMocks
    private ExpressionEvaluatorService testSubject;

    @Mock
    private VariablesEvaluatorService variablesEvaluatorServiceMock;

    @Test
    public void evaluate_withNumber(){
        String expressionToEvaluate = "1";

        long result = testSubject.evaluate(expressionToEvaluate);

        assertThat(result).isEqualTo(1L);
    }

    @Test
    public void evaluate_withSum(){
        String expressionToEvaluate = "5+3";

        long result = testSubject.evaluate(expressionToEvaluate);

        assertThat(result).isEqualTo(8L);
    }

    @Test
    public void evaluate_withDifference(){
        String expressionToEvaluate = "5-3";

        long result = testSubject.evaluate(expressionToEvaluate);

        assertThat(result).isEqualTo(2L);
    }

    @Test
    public void evaluate_withMultiplication(){
        String expressionToEvaluate = "5*10";

        long result = testSubject.evaluate(expressionToEvaluate);

        assertThat(result).isEqualTo(50L);
    }

    @Test
    public void evaluate_withMultipleOperations(){
        String expressionToEvaluate = "5-3*10";

        long result = testSubject.evaluate(expressionToEvaluate);

        assertThat(result).isEqualTo(-25L);
    }

    @Test
    public void evaluate_withVariable(){
        String expressionToEvaluate = "5+currentYear";
        when(variablesEvaluatorServiceMock.evaluateVariable("currentYear")).thenReturn("2017");

        long result = testSubject.evaluate(expressionToEvaluate);

        assertThat(result).isEqualTo(2022L);
    }

}