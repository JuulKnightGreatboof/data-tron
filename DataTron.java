import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.File;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;

public class DataTron {


    public ArrayList<String> readInput(int out) throws FileNotFoundException {
        ArrayList<String> total = new ArrayList<String>();
        //File path = new File("outputFolder/output_"+out+".txt");
        //boolean exists = path.exists();
        boolean bool = false;
        while (!bool) {
            out++;
            File f = new File("C:\\Users\\fatec\\Desktop\\ProgrammerHalfGamerHDRemaster\\outputFolder\\output_" + out + ".txt");
            if (!f.exists()) {
                bool = true;
            }
        }
        total.add(String.valueOf(out));
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("outputFolder/output_" + out + ".txt"));
            Scanner sc1 = null;
            Scanner sc2 = null;
            Scanner sc3 = null;
            try {
                sc1 = new Scanner(new File("data.txt"));
                sc2 = new Scanner(new File("data.txt"));
                sc3 = new Scanner(new File("data.txt"));
            } catch (FileNotFoundException e) {
                System.out.println("Did you forget the input file?");
                System.exit(1);
            }
            int last = 0;
            int current;
            int num;
            int x = 0;
            while (sc1.hasNext()) {
                if (sc1.next().compareTo("PAGE") == 0) {
                    num = sc1.nextInt();
                    if (sc1.nextInt() != 2) {
                        writer.write(num + "\n");
                        total.add(String.valueOf(num));
                        System.out.println(num);
                        x++;
                    }
                }
            }
            total.add("$");
            writer.write("-------------------------------\n");
            writer.write("Gods Rejected: " + x + "\n");
            writer.write("-------------------------------\n");
            //System.out.println("-------------------------------");
            //System.out.println("Gods Rejected: "+x);
            //System.out.println("-------------------------------");
            x = 0;
            while (sc2.hasNext()) {
                if (sc2.next().compareTo("SALES") == 0 && sc2.next().compareTo("REP") == 0) {
                    current = sc2.nextInt();
                    if (current != last) {
                        last = current;
                        x++;
                        String temp = sc2.next();
                        writer.write(temp + "\n");
                        total.add(temp);
                        //System.out.println(sc2.next());
                    }
                }
            }
            total.add("$");
            writer.write("-------------------------------\n");
            writer.write("Gods Rejected: " + x + "\n");
            writer.write("-------------------------------\n");
            //System.out.println("-------------------------------");
            //System.out.println("Gods Rejected: "+x);
            //System.out.println("-------------------------------");
            x = 0;
            while (sc3.hasNext()) {
                if (sc3.next().compareTo("DUE") == 0) {
                    // String thisText = sc.next();
                    //System.out.println(sc3.next());
                    String temp = sc3.next();
                    writer.write(temp + "\n");
                    total.add(temp);
                    x++;
                }
            }
            writer.write("-------------------------------\n");
            writer.write("Gods Rejected: " + x + "\n");
            writer.write("-------------------------------\n");
            //System.out.println("-------------------------------");
            //System.out.println("Gods Rejected: "+x);
            //System.out.println("-------------------------------");
            x = 0;
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return total;
    }
    public static void main(String[] args) throws FileNotFoundException {
        DataTron god = new DataTron();
        //System.out.print("Hello");
        ArrayList<String> temp = god.readInput(0);
        ArrayList<String> first = new ArrayList<String>();
        ArrayList<String> second = new ArrayList<String>();
        ArrayList<String> third = new ArrayList<String>();
        System.out.println(first.size());
        FileOutputStream fos = new FileOutputStream("C:\\Users\\fatec\\Desktop\\ProgrammerHalfGamerHDRemaster\\outputFolder\\DataStuff"+temp.get(0)+".csv", true);
        PrintWriter pw = new PrintWriter(fos);
        pw.println("Invoice Number, Vendor, Date, Amount, EXPENSE ACCOUNT");
        int times = 0;
        for(int i = 1; i < temp.size(); i++){
            if(times == 0){
                if(temp.get(i).compareTo("$") == 0){
                    times+=1;
                } else {
                    first.add(temp.get(i));
                }
            } else if(times == 1){
                if(temp.get(i).compareTo("$") == 0){
                    times+=1;
                } else {
                    second.add(temp.get(i));
                }
            } else if(times == 2){
                third.add(temp.get(i));
            }
        }
        for(int i = 0; i < first.size(); i++){
            pw.println(first.get(i)+",ANCIENT GRAFFITI,"+second.get(i)+","+third.get(i)+",Cost of Goods Sold:Ancient Graffiti Inc.");
        }
        pw.close();
    }
}
