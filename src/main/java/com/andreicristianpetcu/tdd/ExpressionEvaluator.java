package com.andreicristianpetcu.tdd;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ExpressionEvaluator {

    private final VariablesEvaluator variablesEvaluator;

    public ExpressionEvaluator(VariablesEvaluator variablesEvaluator) {
        this.variablesEvaluator = variablesEvaluator;
    }

    public long evaluate(String expressionToEvaluate) {
        throw new NotImplementedException();
    }

}