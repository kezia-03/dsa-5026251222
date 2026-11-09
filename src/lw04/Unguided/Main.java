package lw04.Unguided;

import java.util.Scanner;

public class Main {
    static void SelectionSort(int[] arr, int passes) {
        for (int i = 0; i < passes && i < arr.length - 1; i++) {
            int idx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[idx]) {
                    idx = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[idx];
            arr[idx] = temp;
        }
    }
 
    static int LinearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("cards.txt"));
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int[] cards = new int[n];
            for (int i = 0; i < n; i++) {
                cards[i] = sc.nextInt();
            }
 
            int m = sc.nextInt();
            int[] targets = new int[m];
            for (int i = 0; i < m; i++) {
                targets[i] = sc.nextInt();
            }
 
            SelectionSort(cards, 2);
 
            for (int i = 0; i < n; i++) {
                System.out.print(cards[i]);
                if (i < n - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
 
            for (int t : targets) {
                int idx = LinearSearch(cards, t);
                if (idx != -1) {
                    System.out.println(t + ": FOUND at index " + idx);
                } else {
                    System.out.println(t + ": NOT FOUND");
                }
            }
        }
        sc.close();
    }
}
