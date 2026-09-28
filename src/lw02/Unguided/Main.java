package lw02.Unguided;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        // Your code here
        int MAX_BORROW = 2;
        
        LinkedList<String[]> requests = new LinkedList<>();
        LinkedList<String[]> books = new LinkedList<>();
        LinkedList<String[]> members = new LinkedList<>();
        LinkedList<String[]> success = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        String[] buku1 = {"kalkulus", "3"};
        String[] buku2 = {"fisika", "2"};
        String[] buku3 = {"Statistika", "2"};
        books.add(buku1);
        books.add(buku2);
        books.add(buku3);
        
        Scanner reader = new Scanner(Main.class.getResourceAsStream("borrowing.txt"));
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] data = line.split(" ");
            requests.add(data);

            boolean sudahAda = false;
            for (String[] m : members) {
                if (m[0].equals(data[0])) {
                    sudahAda = true;
                }
            }
            if (sudahAda == false) {
                String[] memberBaru = {data[0], "0"};
                members.add(memberBaru);
            }

        
    }
    reader.close();
    
    Queue<String[]> queue = new LinkedList<>();
        for (String[] r : requests) {
            queue.add(r);
        }

    while (queue.isEmpty() == false) {
            String[] req = queue.poll();

            String[] book = null;
            for (String[] b : books) {
                if (b[0].equals(req[1])) {
                    book = b;
                    break;
                }
            }
            String[] member = null;
            for (String[] m : members) {
                if (m[0].equals(req[0])) {
                    member = m;
                    break;
                }
            }
            int stock = Integer.parseInt(book[1]);
            int borrowed = Integer.parseInt(member[1]);

            if (stock > 0 && borrowed < MAX_BORROW) {
                // berhasil
                book[1] = String.valueOf(stock - 1);
                member[1] = String.valueOf(borrowed + 1);
                success.add(req);
            } else {
                // gagal
                failed.push(req);
            }
        }
        System.out.println("=== Successfully Processed Requests ===");
        for (String[] s : success) {
            System.out.println(s[0] + " " + s[1]);
        }

        System.out.println();
        System.out.println("=== Remaining Book Stock ===");
        for (String[] b : books) {
            System.out.println(b[0] + " : " + b[1]);
        }

        System.out.println();
        System.out.println("=== Failed Requests ===");
        while (failed.isEmpty() == false) {
            String[] f = failed.pop();
            System.out.println(f[0] + " " + f[1]);
        }
    }

}
