package edu.gatech.tbd.model;

/**
 * Available Overall Water Conditions for Purity Reports.
 */
public enum OverallCondition {

    Safe(0, "Safe"), Treatable(1, "Treatable"), Unsafe(2, "Unsafe");

    private int val;
    private String Ocondition;

    OverallCondition(int i, String condition) {
        val = i;
        Ocondition = condition;
    }

    public int getLevel() {
        return val;
    }
        
    public String toString(){
        return Ocondition;
    }

}
