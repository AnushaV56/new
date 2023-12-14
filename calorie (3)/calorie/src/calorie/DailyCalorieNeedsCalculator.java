package calorie;

public class DailyCalorieNeedsCalculator {

	// method called calculate
	public static double calculate(Double basalMetabolicRate, ActivityLevel activityLevel) {
		// Validate input
		if (activityLevel == null) {
			throw new IllegalArgumentException("Activity level cannot be null.");
		}
		if (basalMetabolicRate == null || basalMetabolicRate < 0) {
			throw new IllegalArgumentException("Basal metabolic rate must be a non-negative number.");
		}

		// Calculate daily calorie needs
		double activityFactor = activityLevel.getFactor();
		double dailyCalorieNeeds = basalMetabolicRate * activityFactor;
		return dailyCalorieNeeds;
	}
}
