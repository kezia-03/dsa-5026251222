import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Banktransaction {
    public static void main(String[] args) {

        
        LinkedList<String[]> transactions = new LinkedList<>();

        
        LinkedList<String[]> customers = new LinkedList<>();

        
        try {
            File file = new File("transactions.txt");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] data = line.split(" "); 

                transactions.add(data);

                
                boolean sudahAda = false;
                for (String[] c : customers) {
                    if (c[0].equals(data[0])) {
                        sudahAda = true;
                        break;
                    }
                }

                
                if (!sudahAda) {
                    customers.add(new String[]{data[0], "0"});
                }
            }
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("File transactions.txt tidak ditemukan!");
            return;
        }

        
        Queue<String[]> antrianTransaksi = new LinkedList<>();
        for (String[] t : transactions) {
            antrianTransaksi.add(t);
        }

        
        Stack<String[]> transaksiGagal = new Stack<>();

        
        while (!antrianTransaksi.isEmpty()) {
            String[] t = antrianTransaksi.poll();

            String nama = t[0];
            String tipe = t[1];
            int jumlah = Integer.parseInt(t[2]);

            
            for (String[] c : customers) {
                if (c[0].equals(nama)) {
                    int saldo = Integer.parseInt(c[1]);

                    if (tipe.equals("DEPOSIT")) {
                        saldo = saldo + jumlah;
                        c[1] = String.valueOf(saldo);

                    } else if (tipe.equals("WITHDRAW")) {
                        if (jumlah > saldo) {
                            // saldo tidak cukup, transaksi gagal
                            transaksiGagal.push(t);
                        } else {
                            saldo = saldo - jumlah;
                            c[1] = String.valueOf(saldo);
                        }
                    }
                    break; 
                }
            }
        }

        
        System.out.println("=== Final Balances ===");
        for (String[] c : customers) {
            System.out.println(c[0] + " : " + c[1]);
        }

        System.out.println();

        // 6. Tampilkan transaksi gagal (Stack otomatis LIFO saat di-pop)
        System.out.println("=== Failed Transactions ===");
        while (!transaksiGagal.isEmpty()) {
            String[] f = transaksiGagal.pop();
            System.out.println(f[0] + " " + f[1] + " " + f[2]);
        }
    }
}
    

