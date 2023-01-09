import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import hashMap.*;


public class App {

    static boolean flag = false; // writer append flag

    public static void main(String[] args) throws Exception {
        
        String accountNum = "0";

        // changing the values of rehash threshold to see which has the least
        // collisions
        ArrayList<Map<String,ArrayList<String>>> bankList = new 
        ArrayList<Map<String,ArrayList<String>>>();
        for (double i = 0.7; i > 0.5 ; i -=0.05) {
            bankList.add(setUpMap
                        ('L', 1.5, i));
        }

        for (Map<String,ArrayList<String>> bank_i : bankList){
            bank_i = readTable(bank_i);
            printTable(bank_i);
        }


        // print the table before additions and deletions
        Map<String,ArrayList<String>> bank = 
            setUpMap('L', 2,
                     0.5);
        bank = readTable(bank);
        printTable(bank);

        // additions and deletions
        ArrayList<String> accountInfo = new ArrayList<>();
        accountNum = "03891";
        accountInfo.add("chequing");
        accountInfo.add("Jane");
        accountInfo.add("Walter");
        accountInfo.add("20");
        bank.put(accountNum, accountInfo);
        printTable(bank);

        ArrayList<String> accountInfo1 = new ArrayList<>();
        accountNum = "53794";
        accountInfo1.add("saving");
        accountInfo1.add("Michel");
        accountInfo1.add("Roy");
        accountInfo1.add("3500");
        bank.put(accountNum, accountInfo1);
        printTable(bank);

        bank.remove("81514");
        bank.remove("12421");
        bank.remove("83830");
        printTable(bank);

        ArrayList<String> accountInfo2 = new ArrayList<>();
        accountNum = "87532";
        accountInfo2.add("credit");
        accountInfo2.add("Xi");
        accountInfo2.add("Liu");
        accountInfo2.add("500");
        bank.put(accountNum, accountInfo2);
        printTable(bank);

        // get the results for 3 account numbers and print to console 
        System.out.println("\nwith linear probing:\n");
        ArrayList<String> accountInfo3 = new ArrayList<>();
        System.out.println("\nEntry with accountNum 03891:");
        if ((bank.get("03891") != null)){
            accountInfo3 = bank.get("03891");
            System.out.print(accountInfo3.toString() + "\n");
        } else System.out.println("No such Entry:\n");

        ArrayList<String> accountInfo4 = new ArrayList<>();
        System.out.println("\nEntry with accountNum 87532:");
        if ((bank.get("87532") != null)){
            accountInfo4 = bank.get("87532");
            System.out.print(accountInfo4.toString() + "\n");
        } else System.out.println("No such Entry:\n");

        ArrayList<String> accountInfo5 = new ArrayList<>();
        System.out.println("\nEntry with accountNum 81514:");
        if ((bank.get("81514") != null)){
            accountInfo5 = bank.get("81514");
            System.out.print(accountInfo5.toString() + "\n");
        } else System.out.println("No such Entry:\n");

        // doing all the above but for double hashing open addressing
        // instead of linear probing
        Map<String,ArrayList<String>> bank2 = 
            setUpMap('D', 2,
                     0.5);
        bank2 = readTable(bank2);
        printTable(bank2);

        ArrayList<String> accountInfo6 = new ArrayList<>();
        accountNum = "03891";
        accountInfo6.add("chequing");
        accountInfo6.add("Jane");
        accountInfo6.add("Walter");
        accountInfo6.add("20");
        bank2.put(accountNum, accountInfo6);
        printTable(bank2);

        ArrayList<String> accountInfo7 = new ArrayList<>();
        accountNum = "53794";
        accountInfo7.add("saving");
        accountInfo7.add("Michel");
        accountInfo7.add("Roy");
        accountInfo7.add("3500");
        bank2.put(accountNum, accountInfo7);
        printTable(bank2);

        bank2.remove("81514");
        bank2.remove("12421");
        bank2.remove("83830");
        printTable(bank2);

        ArrayList<String> accountInfo8 = new ArrayList<>();
        accountNum = "87532";
        accountInfo8.add("credit");
        accountInfo8.add("Xi");
        accountInfo8.add("Liu");
        accountInfo8.add("500");
        bank2.put(accountNum, accountInfo8);
        printTable(bank2);


        System.out.println("\nwith double hashing:\n");
        ArrayList<String> accountInfo9 = new ArrayList<>();
        System.out.println("\nEntry with accountNum 03891:");
        if ((bank2.get("03891") != null)){
            accountInfo9 = bank2.get("03891");
            System.out.print(accountInfo9.toString() + "\n");
        } else System.out.println("No such Entry:\n");

        ArrayList<String> accountInfo10 = new ArrayList<>();
        System.out.println("\nEntry with accountNum 87532:");
        if ((bank2.get("87532") != null)){
            accountInfo10 = bank2.get("87532");
            System.out.print(accountInfo10.toString() + "\n");
        } else System.out.println("No such Entry:\n");

        ArrayList<String> accountInfo11 = new ArrayList<>();
        System.out.println("\nEntry with accountNum 81514:");
        if ((bank2.get("81514") != null)){
            accountInfo11 = bank2.get("81514");
            System.out.print(accountInfo11.toString() + "\n");
        } else System.out.println("No such Entry:\n");

        System.out.println("\nProgram Done!");
    }

    // change the map parameters
    static Map<String,ArrayList<String>> setUpMap(char collisionHandlingType, 
                                                    double rehashFactor, 
                                                    double RehashThreshold) {
        Map<String,ArrayList<String>> bank = new FlexibleHashMap<>(71);
        bank.setRehashFactor(rehashFactor);
        bank.setRehashThreshold(RehashThreshold);
        bank.setCollisionHandling(collisionHandlingType);
        return bank;
    }

    // prints the map and required parameters to a file
    static void printTable(Map<String,ArrayList<String>> bank) {
        try{
            BufferedWriter writer = new 
                 BufferedWriter(new FileWriter("map.txt", flag));

                writer.write("\ncollision handling type: ");
                writer.write(bank.collisionHandlingType());
                
                writer.write("\nTotal collisions: ");
                writer.write(String.valueOf(bank.totalCollisionCounter()));

                writer.write("\nTotal probes: ");
                writer.write(String.valueOf(bank.totalProbeCounter()));

                writer.write("\ntable capacity: ");
                writer.write(String.valueOf(bank.tableCapacity()));

                writer.write("\ntable rehash factor: ");
                writer.write(String.valueOf(bank.rehashFactor()));

                writer.write("\ntable rehash threshold: ");
                writer.write(String.valueOf(bank.RehashThreshold()));

                writer.write("\ntable load factor: ");
                writer.write(String.format("%.2f",bank.loadFactor()));

                writer.write("\nmap:\n");
                writer.write("account number, first name, last name, account balance\n");
            

                for (Entry<String, ArrayList<String>> e : bank.entrySet()){
                    writer.write(e.toList().toString());
                    writer.write("\n");
                }

                flag = true;
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    
    // reads values from a text file and makes a map from them
    static Map<String,ArrayList<String>> readTable(
                                        Map<String,ArrayList<String>> bank) {
        String accountNum = "0";
        String line = "";
        try{
            BufferedReader reader = new 
                BufferedReader(new FileReader("account.txt"));
            while ((line = reader.readLine()) != null){

                ArrayList<String> accountInfo = new ArrayList<>();

                String[] values = line.split(" ");

                accountNum = values[0];
                for (int i = 1; i < 5; i++){
                    accountInfo.add(values[i]);
                }

                bank.put(accountNum, accountInfo);
            }
            reader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return bank;
    }
}
