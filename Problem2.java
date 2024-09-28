/* Example1:

List<List<Double>> levels = new ArrayList<>();
levels.add(Arrays.asList(10000.0, 0.3));
levels.add(Arrays.asList(20000.0, 0.2));
levels.add(Arrays.asList(30000.0, 0.1));
levels.add(Arrays.asList(null, 0.1));
double salary = 25000;
double tax = calculateTax(levels, salary);
System.out.println("Salary: " + salary);
System.out.println("Tax: " + tax);

Output1:
Salary: 25000.0
Tax: 3500.0

Explanation 1:
In this example, the tax brackets are defined as:

Income up to 10000 is taxed at 30%.
Income from 10001 to 20000 is taxed at 20%.
Income from 20001 to 30000 is taxed at 10%.
Income above 30000 is taxed at 10%.
The salary is 25000, so it falls in the third tax bracket. The taxable income for this bracket is 5000 (25000 - 20000), and the tax rate is 10%. 
Therefore, the tax for this bracket is 500. The taxable income for the second bracket is 10000 (20000 - 10000), and the tax rate is 20%.
Therefore, the tax for this bracket is 2000. The taxable income for the first bracket is 10000, and the tax rate is 30%.
Therefore, the tax for this bracket is 3000. The total tax is the sum of the taxes for all brackets, which is 3500.
-------------------------------------------------------------------
Example2:
List<List<Double>> levels = new ArrayList<>();
levels.add(Arrays.asList(5000.0, 0.1));
levels.add(Arrays.asList(10000.0, 0.2));
levels.add(Arrays.asList(15000.0, 0.3));
levels.add(Arrays.asList(null, 0.4));
double salary = 12000;
double tax = calculateTax(levels, salary);
System.out.println("Salary: " + salary);
System.out.println("Tax: " + tax);

Output2:
Salary: 12000.0
Tax: 1900.0

Explanation 2:
In this example, the tax brackets are defined as:

Income up to 5000 is taxed at 10%.
Income from 5001 to 10000 is taxed at 20%.
Income from 10001 to 15000 is taxed at 30%.
Income above 15000 is taxed at 40%.
The salary is 12000, so it falls in the third tax bracket. The taxable income for this bracket is 2000 (12000 - 10000), and the tax rate is 30%. 
Therefore, the tax for this bracket is 600. The taxable income for the second bracket is 5000 (10000 - 5000), and the tax rate is 20%. 
Therefore, the tax for this bracket is 1000. The total tax is the sum of the taxes for the second and third brackets, which is 1900.
*/
//-------------------------------------------------------------------------------------------------------------------------------------------

//Time = O(n)
//Space = O(1)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Define tax brackets as a list of lists, where each sublist contains a maximum income limit and a tax rate
        List<List<Double>> levels = new ArrayList<>();
        levels.add(Arrays.asList(10000.0, 0.3));
        levels.add(Arrays.asList(20000.0, 0.2));
        levels.add(Arrays.asList(30000.0, 0.1));
        levels.add(Arrays.asList(null, 0.1)); // null means the current rate applies to all income above the previous level
        
        // Define the salary for which to calculate the tax
        double salary = 45000;
        
        // Call the calculateTax function with the tax brackets and salary, and print the result
        double tax = calculateTax(levels, salary);
        System.out.println("Salary: " + salary);
        System.out.println("Tax: " + tax);
    }
    
    public static double calculateTax(List<List<Double>> levels, double salary) {
        // Initialize variables to keep track of previous level and rate, and the accumulated tax amount
        double tax = 0.0;
        double previousLevel = 0.0;
        double previousRate = 0.0;
        
        // Iterate through each tax bracket
        for (List<Double> level : levels) {
            // Extract the current level and rate from the current tax bracket
            Double currentLevel = level.get(0);
            Double currentRate = level.get(1);
            
            // If the current level is null, it means that the current rate applies to all income above the previous level
            if (currentLevel == null) {
                // Calculate the tax for all income above the previous level, and stop checking further levels
                tax += (salary - previousLevel) * previousRate;
                break;
            }
            
            // If the current level is greater than the salary, only calculate the tax up to the salary
            double taxableIncome = Math.min(salary, currentLevel) - previousLevel;
            if (taxableIncome > 0) {
                tax += taxableIncome * previousRate;
            }
            
            // Update the previous level and rate for the next iteration
            previousLevel = currentLevel;
            previousRate = currentRate;
        }
        
        // Return the total tax amount
        return tax;
    }
}
