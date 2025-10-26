package src;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    private float result;
    private List<Float> history;


    public Calculator() {
        result = 0;
        history = new ArrayList<>();
    }


    public float sum(float a, float b) {
        return a + b;
    }

    public float subtract(float a, float b) {
        return a - b;
    }

    public float multiply(float a, float b) {
        return a * b;
    }

    public float divide(float a, float b) {
        return a / b;
    }

    public void appendToHistory() {
        history.add(result);
    }

    public String displayHistory() {
        var sb = new StringBuilder();
        for(var value : history) sb.append(value + " ");

        return sb.toString().trim();
    }
}