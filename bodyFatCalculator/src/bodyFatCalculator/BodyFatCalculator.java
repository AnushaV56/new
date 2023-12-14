package bodyFatCalculator;

import java.util.*;
public class BodyFatCalculator
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
       
       
        System.out.print("Enter your gender (Male/Female): ");
        String gender = scanner.next();
        
        System.out.print("Enter your weight : ");
        double weight = scanner.nextDouble();
        
        System.out.print("Enter your waist circumference : ");
        double waistCircumference = scanner.nextDouble();
        
        System.out.print("Enter your wrist circumference : ");
        double wristCircumference = scanner.nextDouble();
        
        
       
        double chest, abdominal, thigh;
        System.out.print("Enter chest skinfold thickness in mm: ");
        chest = scanner.nextDouble();
        System.out.print("Enter abdominal skinfold thickness in mm: ");
        abdominal = scanner.nextDouble();
        System.out.print("Enter thigh skinfold thickness in mm: ");
        thigh = scanner.nextDouble();
        
        
        double bodyDensity;
        if (gender.equalsIgnoreCase("Male"))
        {
            bodyDensity = 1.10938 - (0.0008267 * (chest + abdominal + thigh)) + (0.0000016 * Math.pow(chest + abdominal + thigh, 2)) - (0.0002574 * weight);
        } else {
            double triceps, suprailiac;
            System.out.print("Enter triceps skinfold thickness in mm: ");
            triceps = scanner.nextDouble();
            System.out.print("Enter suprailiac skinfold thickness in mm: ");
            suprailiac = scanner.nextDouble();
            bodyDensity = 1.0994921 - (0.0009929 * (triceps + suprailiac + thigh)) + (0.0000023 * Math.pow(triceps + suprailiac + thigh, 2)) - (0.0001392 * weight);
        }
        double bodyFatPercentage = (495 / bodyDensity) - 450;
        
        System.out.printf("Your body fat percentage is %.2f%%.%n", bodyFatPercentage);
        
        // classify body fat percentage based on gender and age
        
        if (gender.equalsIgnoreCase("Male")) {
            if (bodyFatPercentage < 6) {
                System.out.println("Essential fat");
            } else if (bodyFatPercentage >= 6 && bodyFatPercentage < 14) {
                System.out.println("Athletic");
            } else if (bodyFatPercentage >= 14 && bodyFatPercentage < 18) {
                System.out.println("Fit");
            } else if (bodyFatPercentage >= 18 && bodyFatPercentage < 25) {
                System.out.println("Average");
            } else {
                System.out.println("Obese");
            }
        } else {
            if (bodyFatPercentage < 16) {
                System.out.println("Essential fat");
            } else if (bodyFatPercentage >= 16 && bodyFatPercentage < 21) {
                System.out.println("Athletic");
            } else if (bodyFatPercentage >= 21 && bodyFatPercentage < 25) {
                System.out.println("Fit");
            } else if (bodyFatPercentage >= 25 && bodyFatPercentage < 32) {
                System.out.println("Average");
            } else {
                System.out.println("Obese");
            }
        }
        scanner.close();
    }
}