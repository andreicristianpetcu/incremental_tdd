package com.andreicristianpetcu.diytdd.service;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ExpressionEvaluatorService {

    private final VariablesEvaluatorService variablesEvaluatorService;

    public ExpressionEvaluatorService(VariablesEvaluatorService variablesEvaluatorService) {
        this.variablesEvaluatorService = variablesEvaluatorService;
    }

    public long evaluate(String expressionToEvaluate) {
        throw new NotImplementedException();
    }

}