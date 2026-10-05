package lw03.unguided;

import java.util.*;

public class Main {
    public static void main(String[] args) {
      Scanner sc = new Scanner(Main.class.getResourceAsStream("enrollment.txt"));
      Map<String, Integer> enrollment = new HashMap<>();
      
      int rejected = 0; 

      while (sc.hasNextLine()) {
          String input = sc.nextLine();
          String[] parts = input.split(" ");
          String action = parts[0];
          String code = parts[1];

          if (action.equals("REGISTER")) {
              int count = Integer.parseInt(parts[2]);
              if (count <= 0) {
                  rejected++;
              } else if (enrollment.containsKey(code)) {
                  enrollment.put(code, enrollment.get(code) + count);
              } else {
                  enrollment.put(code, count);
              }
          } else if (action.equals("WITHDRAW")) {
              int count = Integer.parseInt(parts[2]);
              if (count <= 0) {
                  rejected++;
              } else if (enrollment.containsKey(code) && enrollment.get(code) >= count) {
                  enrollment.put(code, enrollment.get(code) - count);
              } else {
                  rejected++;
              }
          } else {
              if (enrollment.containsKey(code)) {
                  System.out.println(code + ": " + enrollment.get(code) + " students");
              } else {
                  System.out.println(code + ": Not found");
              }
          }
      }
      System.out.println("===== Enrollment Checks =====");
        for(Map.Entry<String, Integer> entry : enrollment.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " students");
        }
    
    System.out.println("===== Final Enrollment =====");
        for (Map.Entry<String, Integer> entry : enrollment.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " students");
        }
      System.out.println("Rejected operations: " + rejected);
    }    
}
