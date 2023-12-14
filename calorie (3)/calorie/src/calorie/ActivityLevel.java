package calorie;

import java.io.File;

public enum ActivityLevel {
    SEDENTARY(1.2),
    LIGHTLY_ACTIVE(1.375),
    MODERATELY_ACTIVE(1.55),
    VERY_ACTIVE(1.725),
    EXTRA_ACTIVE(1.9);

	//initialize 
	private final double factor;

	ActivityLevel(double factor) {
	    this.factor = factor;
	}

	//getter method
	public double getFactor() {

	    return factor;
	}
}