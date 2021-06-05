import java.util.Scanner;
import java.io.*;

public class JavaATM {
public static Scanner in = new Scanner(System.in);
public static int chs; 
public static String email; 
public static String pass; 
public static String data; 
    public static void main(String[] args) throws Exception {
        run();
        System.exit(0);
    }
    public static void run() throws Exception{
        System.out.println("[1]Login [2]Sign-Up");
        chs = in.nextInt();
        switch(chs){
            case 1:
                login();
                break;
            case 2: 
                signup();
                break;
            default:
                System.out.println("Invalid output please try again");
                run();        
        }
    }
    public static void signup() throws Exception{
        System.out.println("Enter Email: ");
        email = in.next();
        File f = new File("DataOfUser/"+email+".txt");
            if(f.exists() && !f.isDirectory()){
                System.out.println("Account already exist");
                run();
            }
            else if(f.createNewFile()){
                System.out.println("Enter Password: ");
                pass = in.next();
                FileWriter fw = new FileWriter(f);
                fw.write(pass);
                fw.close();
            }
        File ad = new File("DataOfUser/accData/"+email+pass+".txt");        
            if(ad.createNewFile()){
                System.out.println("Account Box Created");     
            }
        FileWriter fw = new FileWriter("DataOfUser/accData/"+email+pass+".txt"); 
        fw.write("0");   
        fw.close();    
        System.out.print("Do you want to make transaction?\n[1]yes [2]no: ");
        chs = in.nextInt();
        switch(chs){
            case 1:
                login();
                break;
            case 2:
                System.exit(0);    
        }
    }
    public static void login() throws Exception{                   // login area 
        System.out.print("email: ");
        email = in.next();
        File f = new File("DataOfUser/"+email+".txt");
        if(f.exists() && !f.isDirectory()){
            Scanner sc = new Scanner(f);
            while(sc.hasNextLine()){
                data = sc.nextLine();
                
                System.out.println("password: ");
                pass = in.next();
                if(data.contains(pass)){
                    System.out.println("File Accessed");
                    System.out.print("[1]depost [2]withdraw: ");
                    chs = in.nextInt();
                    switch(chs){
                        case 1:
                            deposit();
                            break;
                        case 2: 
                            withdraw();
                            break;
                        default:
                            System.out.println("invalid choice");
                            System.exit(0);        
                    }
                }
                else{
                    System.out.print("\npassword invalid\nTry again[1]yes [2]no:");
                    chs = in.nextInt();
                    switch(chs){
                        case 1:
                            login();
                            break;
                        case 2:
                            System.exit(0);    
                    }
                }
            }
            sc.close();
        }
        else{
            System.out.print("\nAccount doesn't exist\nTry again[1]yes [2]no:");
            chs = in.nextInt();
            switch(chs){
                case 1:
                    login();
                    break;
                case 2:
                    System.exit(0);    
            }
        }
    }
    public static void deposit() throws Exception{
        File f = new File("DataOfUser/accData/"+email+pass+".txt"); //locate the account box
        int amount = 0; 
        String currAmount="";  
        System.out.print("Amount to deposit: ");      //get the amount to deposit
        amount = in.nextInt();
        Scanner sc = new Scanner(f);              //scan the amount inside the box
        while(sc.hasNextLine()){
            currAmount = sc.nextLine();
            System.out.println("Box current account amount: "+ currAmount);  //show the current amount inside the box
            amount = amount + Integer.valueOf(currAmount);           //add the inputed amount to the current amount
            System.out.println("Box total account amount: " + amount);    //show the current amount including the added amount
        }
        FileWriter fw = new FileWriter("DataOfUser/accData/"+email+pass+".txt");
        fw.write(""+amount);    //write the total new amount
        System.out.println("Process done");
        fw.close();
        sc.close();
        System.out.println("Thanks for Transacting");
        System.exit(0);
    }
    public static void withdraw() throws Exception {//locate the account box
        int amount = 0; 
        String currAmount="";  
        File f = new File("DataOfUser/accData/"+email+pass+".txt");  //locate the file
        Scanner sc = new Scanner(f);     //get the current amount
        while(sc.hasNextLine()){   
            currAmount = sc.nextLine();
            System.out.println("Box current account amount: "+ currAmount);  //show the current box amount 
        }    
        System.out.print("Amount to withdraw: ");    //get the amount to withdraw
        amount = in.nextInt();
        amount = Integer.valueOf(currAmount) - amount;  // process the withdraw of amount.
        System.out.println("The current account box amount: " + amount);  //show the total current amount
        FileWriter fw = new FileWriter("DataOfUser/accData/"+email+pass+".txt");
        fw.write(""+amount);
        fw.close();
        sc.close();
        System.out.println("Thanks for Transacting");
        System.exit(0);

    }
} 
