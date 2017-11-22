package com.andreicristianpetcu.diytdd.service;

public class ExpressionEvaluatorService {

    private final VariablesEvaluatorService variablesEvaluatorService;

    public ExpressionEvaluatorService(VariablesEvaluatorService variablesEvaluatorService) {
        this.variablesEvaluatorService = variablesEvaluatorService;
    }

    long evaluate(String expressionToEvaluate) {
        return 1L;
    }

}