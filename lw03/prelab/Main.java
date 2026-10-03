package lw03.prelab;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("playlist.txt"));
        List<String> playlist = new ArrayList<>();

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String action = input.substring(0, input.indexOf(" "));

            if (action.equals("ADD")) {
                String song = input.substring(input.indexOf(" ") + 1);
                playlist.add(song);
            } else if (action.equals("REMOVE")) {
                String song = input.substring(input.indexOf(" ") + 1);
                playlist.remove(song);
            } else {
                String details = input.substring(input.indexOf(" ") + 1);
                int index = Integer.parseInt(details.substring(0, details.indexOf(" ")));
                String song = details.substring(details.indexOf(" ") + 1);
                playlist.add(index, song);
            }
        }

        System.out.println("=== Problem 1 ===");
        System.out.println("Jumlah lagu : " + playlist.size());
        int number = 1;
        for (String song : playlist) {
            System.out.println(number + ": " + song);
            number++;
        }

        Scanner read = new Scanner(Main.class.getResourceAsStream("participants.txt"));
        Set<String> participants = new HashSet<>();
        int duplikat = 0;

        while (read.hasNextLine()) {
            String name = read.nextLine();
            if (participants.contains(name)) {
                duplikat++;
            }
            participants.add(name);
        }

        System.out.println("=== Problem 2 ===");
        System.out.println("Unique Participants : " + participants.size());
        int nomor = 1;
        for (String abc : participants) {
            System.out.println(nomor + ": " + abc);
            nomor++;
        }
        System.out.println("Duplicate Registrations : " + duplikat);

        Scanner read2 = new Scanner(Main.class.getResourceAsStream("inventory.txt"));
        Map<String, Integer> inventory = new HashMap<>();
        int failed = 0;

        while (read2.hasNextLine()) {
            String in = read2.nextLine();
            String[] parts = in.split(" ");
            String action = parts[0];
            String item = parts[1];
            int quantity = Integer.parseInt(parts[2]);

            if (action.equals("ADD")) {
                if (inventory.containsKey(item)) {
                    inventory.put(item, inventory.get(item) + quantity);
                } else {
                    inventory.put(item, quantity);
                }
            } else {
                if (inventory.containsKey(item) && inventory.get(item) >= quantity) {
                    inventory.put(item, inventory.get(item) - quantity);
                } else {
                    failed++;
                }
            }
        }

        System.out.println("=== Problem 3 ===");
        for (String key : inventory.keySet()) {
            System.out.println(key + ": " + inventory.get(key));
        }
        System.out.println("Failed Sales : " + failed);
    }
}
