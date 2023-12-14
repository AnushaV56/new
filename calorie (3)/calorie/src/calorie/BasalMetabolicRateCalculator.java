package calorie;

public class BasalMetabolicRateCalculator {

	public static double calculate(int age, String gender, double height, double weight) {
	    
	    // Validate gender input
	    if (gender == null || gender.isEmpty()) {
	        throw new IllegalArgumentException("Gender cannot be null ");
	    }
	    // Calculate basal metabolic rate based on gender - Mifflin - St Jeor Equation
	    double bmr = gender.equalsIgnoreCase("M") ?
                (10 * weight) + (6.25 * height) - (5 * age) + 5 :
                (10 * weight) + (6.25 * height) - (5 * age) - 161;

   return bmr;
	}
}