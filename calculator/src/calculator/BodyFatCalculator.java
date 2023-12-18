package calculator;

import java.io.*;
import java.util.*;

public class BodyFatCalculator {
	private List<Double> history;

	private int age;
	private String gender;
	private double bodyfatpercentage;
	private double BMI;
	private double weight;
	private double height;

	public BodyFatCalculator() {
		history = new ArrayList<>();
	}

	
	public double calculateBodyFat() {
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		while (!exit) {
			try {
				System.out.println("--------------------");
				System.out.println("BODY FAT CALCULATOR");
				System.out.println("--------------------");
				System.out.println("Select an option:");
				System.out.println("1. Body Fat Calculator");
				System.out.println("2. To view the History");
				System.out.println("3. For Reset");
				System.out.println("4. To see the BMI chart");
				System.out.println("5. Histoy in textfile");
				System.out.println("6. Exit");

				int option = sc.nextInt();

				switch (option) {
				case 1:
					System.out.print("Enter your gender (M/F): ");

					this.gender = sc.next();
					System.out.println(" ");
					if (!this.gender.equalsIgnoreCase("M") && !this.gender.equalsIgnoreCase("F")) {
						throw new IllegalArgumentException(
								"Invalid gender. Please enter 'M' for male or 'F' for female.");
					}

					System.out.print("Enter your current age in years: ");
					this.age = sc.nextInt();
					System.out.println(" ");
					if(this.age>101) {
						throw new IllegalArgumentException("Please enter a valid Age");
					}
					if (this.age < 0) {
						throw new IllegalArgumentException("Age cannot be negative.");
					}

					System.out.print("Enter your height in m: ");
					this.height = sc.nextDouble();
					System.out.println(" ");
					if (this.height < 0) {
						throw new IllegalArgumentException("Height cannot be negative.");
					}

					System.out.print("Enter your weight in kg: ");
					this.weight = sc.nextDouble();
					System.out.println(" ");
					if (this.weight < 0) {
						throw new IllegalArgumentException("Weight cannot be negative.");
					}

					if (this.age <= 18) {
						if (gender.equalsIgnoreCase("M")) {
							System.out.println("Body Fat Calculator for Boys under 18 years old");
							System.out.println(" ");
							BMI = (long) (weight / (height * height));
							bodyfatpercentage = (1.20 * BMI + 0.23 * age - 16.2);
						} else if (gender.equalsIgnoreCase("F")) {
							System.out.println("Body Fat Calculator for Girls under 18 years old");
							System.out.println(" ");
							BMI = (long) (weight / (height * height));
							bodyfatpercentage = (1.20 * BMI + 0.23 * age - 16.2);
						}
					} else {
						if (gender.equalsIgnoreCase("M")) {
							System.out.println("Body Fat Calculator for Men over 18 years old");
							System.out.println(" ");
							BMI = (long) (weight / (height * height));
							bodyfatpercentage = (1.20 * BMI + 0.23 * age - 16.2);
						} else if (gender.equalsIgnoreCase("F")) {
							System.out.println("Body Fat Calculator for Women over 18 years old");
							System.out.println(" ");
							BMI = (long) (weight / (height * height));
							bodyfatpercentage = (1.20 * BMI + 0.23 * age - 16.2);

						}
					}

					if (BMI < 18.5) {
						System.out.println("Your BMI score is " + BMI);
					} else if (BMI < 25) {
						System.out.println("Your BMI score is " + BMI);
					} else if (BMI < 30) {
						System.out.println("Your BMI score is " + BMI);
					} else {
						System.out.println("Your BMI score is " + BMI);
					}

					System.out.println("Your body fat percentage is: " + bodyfatpercentage + "%");
					history.add(bodyfatpercentage);
					System.out.println(" ");
					break;
				case 2:
					if (history.size() == 0) {
						System.out.println("No history found.");
						break;
					}
					System.out.println("BodyFatCalculation History \n");
					for (int i = 0; i < history.size(); i++) {
						System.out.println((i + 1) + ". " + history.get(i) + "%");
						System.out.println(" ");

					}
					break;

				case 3:
					resetHistory();
					break;

				case 5:
					History();
					break;

				case 4:
					System.out.println("BMI Chart:");
					System.out.println("-----------------------------");
					System.out.println("BMI\t\t| Category");
					System.out.println("-----------------------------");
					System.out.println("<18.5\t\t| Underweight");
					System.out.println("18.5-24.9\t| Normal weight");
					System.out.println("25.0-29.9\t| Overweight");
					System.out.println(">=30.0\t\t| Obese");
					System.out.println("-----------------------------");
					break;
				case 6:
					exit = true;
					System.out.println("Exiting calculator. Thank you");
					break;
				default:
					System.out.println("Invalid option. Please enter a number between 1-6");
					break;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				
			}
		}
		return bodyfatpercentage;

	}

	// reset operation
	private void resetHistory() {
		history.clear();
		System.out.println(" ");
		System.out.println("History has been reset.");
	}
	
	//history operation

	private void History() {
		if (history.isEmpty()) {

			System.out.println("\n");
			System.out.println("No calculations have been made yet.");
		} else {
			try {
				FileWriter writer = new FileWriter("bodyfathistory.txt");
				writer.write("Bodyfat history:\n");
				int serialNumber = 1;
				for (Double bodyfatHistory : history) {
					writer.write(serialNumber + ". " + bodyfatHistory + "\n");
					serialNumber++;
				}
				writer.close();
				System.out.println("Bodyfat history has been saved to file 'bodyfathistory.txt'.");
				System.out.println(" ");
			} catch (IOException e) {
				System.out.println("Error writing to file: " + e.getMessage());
			}
			try {
				List<Double> bodyfatHistory = BodyFatReader.bodyfatReader("bodyfathistory.txt");
				System.out.println("Bodyfat history: " + bodyfatHistory);
			} catch (IOException e) {
				System.out.println("Error reading bodyfat history from file: " + e.getMessage());
			}
		}
	}
	public static void main(String[] args) {

		BodyFatCalculator calcaulator = new BodyFatCalculator();

		calcaulator.calculateBodyFat();
		
	}

}

