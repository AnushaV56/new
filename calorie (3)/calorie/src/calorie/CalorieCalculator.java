package calorie;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CalorieCalculator {

	private List<Double> history;

	public CalorieCalculator() {
		history = new ArrayList<>();

	}

	public void run() {
		Scanner scanner = new Scanner(System.in);
		boolean exit = false;

		while (!exit) {
			try {

				System.out.println("Hi Welcome to Calorie Calculator");
				System.out.println("------------------------------------");
				System.out.println("Select an option:");
				System.out.println("1. Calculate daily calorie needs");
				System.out.println("2. Show calorie history");
				System.out.println("3. Show calorie history txt file");
				System.out.println("4. Reset calorie history");
				System.out.println("5. Exit");
				System.out.println("------------------------------------");
				int option = scanner.nextInt();

				switch (option) {
				case 1:
					System.out.print("Enter your age in years: ");
					int age = scanner.nextInt();
					System.out.println(" ");
					if (age < 0) {
						throw new IllegalArgumentException("Age cannot be negative.");
					}

					System.out.print("Enter your gender (M/F): ");
					String gender = scanner.next();
					System.out.println(" ");
					if (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F")) {
						throw new IllegalArgumentException(
								"Invalid gender. Please enter 'M' for male or 'F' for female.");
					}

					System.out.print("Enter your height in cm: ");

					double height = scanner.nextDouble();
					System.out.println(" ");
					if (height < 0) {
						throw new IllegalArgumentException("Height cannot be negative.");
					}

					System.out.print("Enter your weight in kg: ");

					double weight = scanner.nextDouble();
					System.out.println(" ");
					if (weight < 0) {
						throw new IllegalArgumentException("Weight cannot be negative.");
					}

					System.out.print("Enter your activity level (1-5): ");

					int activityLevel = scanner.nextInt();
					System.out.println(" ");
					if (activityLevel < 1 || activityLevel > 5) {
						throw new InvalidActivityLevelException(
								"Invalid activity level. Please enter a value between 1-5.");
					}

					ActivityLevel level = ActivityLevel.values()[activityLevel - 1];
					double basalMetabolicRate = BasalMetabolicRateCalculator.calculate(age, gender, height, weight);
					double dailyCalorieNeeds = DailyCalorieNeedsCalculator.calculate(basalMetabolicRate, level);
					System.out.println("Your daily calorie needs are: " + dailyCalorieNeeds);
					System.out.println("---------------------------------------------");
					System.out.println("");
					history.add(dailyCalorieNeeds);
					break;

				case 2:
					showHistory();
					break;

				case 3:
					History();
					break;

				case 4:
					resetHistory();
					break;

				case 5:
					exit = true;
					System.out.println("Exiting calorie calculator. Thank you for using!");
					break;

				default:
					System.out.println("Invalid option. Please enter a number between 1-3.");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid number.");
				scanner.nextLine();
			} catch (InvalidActivityLevelException | IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void showHistory() {
	    if (history.isEmpty()) {
	        System.out.println("\n");
	        System.out.println("No calculations have been made yet.");
	    } else {
	        System.out.println("Calorie history:");

	        int serialNumber = 1;
	        for (Double calorieCount : history) {
	            DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	            Date date = new Date();
	            String dateTime = dateFormat.format(date);
	            System.out.println(serialNumber + ". " + calorieCount + " (" + dateTime + ")");
	            System.out.println(" ");
	            serialNumber++;
	        }
	    }
	}
	private void loadHistory() {
	    Scanner scanner = new Scanner(System.in);
	    System.out.print("Enter the file name: ");
	    String fileName = scanner.next();
	    scanner.close();

	    try {
	        List<String> lines = Files.readAllLines(Paths.get(fileName));
	        if (lines.size() <= 1) {
	            System.out.println("\n");
	            System.out.println("No calorie history found in the file.");
	        } else {
	            history.clear();
	            for (int i = 1; i < lines.size(); i++) {
	                String[] parts = lines.get(i).split("\\s+");
	                double calorieCount = Double.parseDouble(parts[1]);
	                history.add(calorieCount);
	            }
	            System.out.println("Calorie history has been loaded from file '" + fileName + "'.");
	            System.out.println(" ");
	        }
	    } catch (FileNotFoundException e) {
	        System.out.println("File not found: " + e.getMessage());
	    } catch (IOException e) {
	        System.out.println("Error reading file: " + e.getMessage());
	    } catch (NumberFormatException e) {
	        System.out.println("Invalid data in file: " + e.getMessage());
	    }
	}


	private void History() {
	    if (history.isEmpty()) {
	        System.out.println("There is no calorie history to display.");
	        return;
	    }

	    DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	    Date date = new Date();
	    String dateTime = dateFormat.format(date);
	    String filename = "calorie1_history_" + dateTime + ".txt";
	    try {
	        FileWriter writer = new FileWriter(filename);
	        writer.write("Calorie history:\n");

	        int serialNumber = 1;
	        for (Double calorieCount : history) {
	            dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	           
	            writer.write(serialNumber + ". " + calorieCount + " (" + dateTime + ")\n");
	            serialNumber++;
	        }

	        writer.close();
	        System.out.println("Calorie history has been saved to file '" + filename + "'.");
	        System.out.println(" ");

	        List<String> lines = Files.readAllLines(Paths.get(filename));
	        for (String line : lines) {
	            System.out.println(line);
	        }
	        System.out.println(" ");
	    } catch (IOException e) {
	        System.out.println("Error writing to file: " + e.getMessage());
	        return;
	    }
	}


	private void resetHistory() {
		history.clear();
		System.out.println(" ");
		System.out.println("Calorie history has been reset.");
	}

}