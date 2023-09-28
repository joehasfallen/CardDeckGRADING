// Joe Lentini
// Matching Braces 
// Java Methods CH15 EX4

import java.io.*;

public class BracesClass {

    public static void main(String[] args) {
        String inputFile = "BracesTXT.java"; // input file
        String outputFile = "outputB.txt"; // output file 

        int braceCount = 0;
        boolean isBalanced = true;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
        	
            int c;
            
            while ((c = reader.read()) != -1) {
            	
                char character = (char) c;
                
                if (character == '{') {
                	
                    braceCount++;
                    
                } else if (character == '}') {
                	
                    braceCount--;
                    
                    if (braceCount < 0) {
                    	
                        isBalanced = false;
                        
                        break; 
                    }
                }
            }
            
            
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        
        // Check if braces are balanced
        if (braceCount == 0 && isBalanced) {
            System.out.println("balanced.");
            
        } else {
            System.out.println("not balanced.");
            
        }

        
        // Write to output file
        try (BufferedWriter writer1 = new BufferedWriter(new FileWriter(outputFile))) {
            writer1.write("balanced: " + (braceCount == 0 && isBalanced));
            
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
