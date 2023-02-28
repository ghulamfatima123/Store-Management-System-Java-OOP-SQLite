package com.company;

/**
 *
 * @author FA20-BECE-0001 / FA20-BECE-0011
 */
import java.util.Arrays;

import java.util.Scanner;
import java.sql.*;
import java.util.InputMismatchException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;



public class Main{
    public static void main(String[] args){


        System.out.println("\n                **************  *************  **************  *************  **************       "
                + "\n                **************  *************  **************  *************  **************       "
                + "\n                ****            *************  ***        ***  ***       ***  ****                 "
                + "\n                ****                 ****      ***        ***  ***       ***  ****                 "
                + "\n                **************       ****      ***        ***  *************  ***********          "
                + "\n                 **************       ****      ***        ***  *************  ***********          "
                + "\n                           ****      ****      ***        ***  *** ****       ****                 "
                + "\n                           ****      ****      ***        ***  ***    ****    ****                 "
                + "\n                 **************      ****      **************  ***      ****  **************       "
                + "\n                 **************      ****      **************  ***       **** **************       ");




//        Scanner sc = new Scanner(System.in);
//        profit_loss s=new profit_loss();
//        s.password();



        System.out.println(               ""+"   MAIN MENU  "+""
                + "\n Enter the number  you want to do from the list provided below"
                + "\n 1.  staff department"
                + "\n 2.  add Product"
                + "\n 3.  remover product"
                + "\n 4.  update product"
                + "\n 5.  customer "
                + "\n 6.  Hr department"
                + "\n 7.  exit" );





        while (true) {

            try{
                System.out.println("Enter the number what would you like to open");
                Scanner user_option=new Scanner (System.in);
                int user=user_option.nextInt();
                if(user==1){
                    System.out.println("enter the number you want thr details of");
                    System.out.println("1-staffmembers name \n 2-staff member salary");
                    int staff_choice=user_option.nextInt();
                    switch (staff_choice) {
                        case 1:

                            staff_department.stafmembers();
                            break;
                        case 2:
                            staff_department staff=new staff_department();
                            staff.stafsalary();
                            break;

                        default:
                            System.out.println("sorry wrong input try again ");
                            break;
                    }





                }
                if(user==2){
                    addProduct additem=new addProduct();
                    additem.addproduct();
                }
                if(user==4){
                    addProduct.updategoods updateitem=new addProduct.updategoods();
                    updateitem.updateproduct();
                }
                if(user==3){
                    addProduct.removeProduct removeprod=new addProduct.removeProduct();
                    removeprod.removeproduct();



                }
                if(user==5){


                    System.out.println("1-QUERY TO ADD \n 2-QUERY TO REMOVE \n 3-QUERY TO UPDATE");
                    int cust_choice=user_option.nextInt();
                    switch (cust_choice) {
                        case 1:
                            customer cust = new customer();
                            cust.addcustomer();
                            break;
                        case 2:
                            customer cust1=new customer();
                            cust1.removecustomer();
                            break;
                        case 3:
                            customer cust2=new customer();
                            cust2.updatecustomer();
                            break;

                        default:
                            System.out.println("sorry input valid input");
                            break;
                    }

                }

                if (user==7){

                    System.out.println("**************");

                    System.out.println("exiting...");
                    System.exit(0);

                    break;
                }



            } catch (InputMismatchException e) {
                System.out.println("Invalid input given please try again");
            }

        }

    }

}









interface  Product {
    abstract void addproduct();
    abstract void removeproduct();



}
class addProduct implements Product {
    @Override
    public void removeproduct() {}

    public void addproduct() {

        Scanner sc = new Scanner(System.in);
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        //System.out.println("Opened database successfully");
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            //System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS prod" +
                    " (ID          KEY     NOT NULL,"+
                    "NAME           TEXT    NOT NULL, " +
                    " Price            INT     NOT NULL, " +
                    " Stock         REAL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        //System.out.println("Table created successfully");
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            //System.out.println("Opened database successfully");

            stmt = c.createStatement();

            int i = 1;
            System.out.println("Enter the number of PRODUCTS you want to enter ");
            try{

                int a = sc.nextInt();
                while (i <= a) {
                    System.out.println("Enter the ID of prod"+i);
                    int id=sc.nextInt();
                    System.out.println("Enter the name of prod"+i);
                    String name = sc.next();
                    System.out.println("Enter the price of prod"+i);
                    int price = sc.nextInt();
                    System.out.println("Enter the stocks of prod"+i);
                    double stock = sc.nextDouble();

                    String sql = "INSERT INTO prod (ID,NAME,Price,Stock) " +
                            "VALUES ( "+ id +",'" + name + "' , " + price + ", " + stock + ");";
                    stmt.executeUpdate(sql);
                    System.out.println("item succesfully added"+i);

                    i = i + 1;


                }
                if(i==a) {
                    System.out.println("FOR FURTHER OPERATIONS \n" + "press 2- For removing a product from data base \n" + "press 3-Update price of data base");
                }


            }
            catch(InputMismatchException e){
                System.out.println("Invalid input please try again ");
            }




            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        // System.out.println("Records created successfully");
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            // System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM prod;");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                float stock = rs.getFloat("stock");

                System.out.println( "ID = " + id  );
                System.out.println("NAME = " + name);
                System.out.println("PRICE = " + price);
                System.out.println("STOCK = " + stock+"\n");

            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        //System.out.println("Operation done successfully");


    }
    static  class removeProduct   {
        Scanner sc = new Scanner(System.in);
        public void addproduct(){}
        public void updateproduct(){}
        public void removeproduct() {
            Connection c = null;
            Statement stmt = null;

            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:test.db");
                c.setAutoCommit(false);
                // System.out.println("Opened database successfully");

                stmt = c.createStatement();
                System.out.println("Enter the ID of the products you want to remove ");
                int id = sc.nextInt();
                String sql = "DELETE from prod where ID=" + id + ";";
                stmt.executeUpdate(sql);
                c.commit();

                ResultSet rs = stmt.executeQuery("SELECT * FROM prod;");

                while (rs.next()) {
                    id = rs.getInt("id");
                    String name = rs.getString("name");
                    int price = rs.getInt("price");
                    double stock = rs.getFloat("stock");

                    System.out.println( "ID = " + id  );
                    System.out.println("NAME = " + name);
                    System.out.println("PRICE = " + price);
                    System.out.println("STOCK = " + stock+"\n");
                    System.out.println();
                }
                rs.close();
                stmt.close();
                c.close();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
            // System.out.println("Operation done successfully");
        }
    }





    static class updategoods  {



        public void addproduct() {

        }


        public void removeproduct() {

        }
        public void updateproduct(){
            Connection c = null;
            Statement stmt = null;
            Scanner updatesc = new Scanner(System.in);
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:test.db");
                c.setAutoCommit(false);
                //System.out.println("Opened database successfully");

                stmt = c.createStatement();
                System.out.println(" Enter the price you want to change ");
                int price  =updatesc.nextInt();
                System.out.println(" Enter the ID you want to change to price in  ");
                int id =updatesc.nextInt();

                String sql = "UPDATE prod set price = " + price + " where "+id+";";
                stmt.executeUpdate(sql);
                c.commit();

                ResultSet rs = stmt.executeQuery( "SELECT * FROM prod;" );

                while ( rs.next() ) {
                    id = rs.getInt("id");
                    String name = rs.getString("name");
                    price = rs.getInt("price");
                    double stock = rs.getFloat("stock");

                    System.out.println( "ID = " + id  );
                    System.out.println("NAME = " + name);
                    System.out.println("PRICE = " + price);
                    System.out.println("STOCK = " + stock+"\n");
                    System.out.println();
                }
                rs.close();
                stmt.close();
                c.close();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            // System.out.println("Operation done successfully");
        }

    }



}



class customer {


    void addcustomer() {
        Scanner sc = new Scanner(System.in);
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        //System.out.println("Opened database successfully");
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            //System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS custom" +
                    " (ID          KEY     NOT NULL," +
                    "NAME           TEXT    NOT NULL, " +
                    " PRODBUY         TEXT)";

            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        //System.out.println("Table created successfully");
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            //System.out.println("Opened database successfully");

            stmt = c.createStatement();

            int i = 1;
            System.out.println("Enter the number of CUSTOMERS you want to enter ");
            try {

                int a = sc.nextInt();
                while (i <= a) {
                    System.out.println("Enter the ID of customer" + i);
                    int id = sc.nextInt();
                    System.out.println("Enter the name of customer" + i);
                    String name = sc.next();
                    System.out.println("Enter the products  of customer" + i);
                    String prodbuy = sc.next();


                    String sql = "INSERT INTO custom (ID,NAME,PRODBUY) " +
                            "VALUES ( " + id + ",'" + name + "' , '" + prodbuy + "');";
                    stmt.executeUpdate(sql);
                    System.out.println("customer succesfully added" + i);

                    i = i + 1;


                }



            } catch (InputMismatchException e) {
                System.out.println("Invalid input please try again ");
            }


            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        // System.out.println("Records created successfully");
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            // System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM custom;");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String prodbuy = rs.getString("prodbuy");


                System.out.println("ID = " + id);
                System.out.println("NAME = " + name );
                System.out.println("PRODUCTS BOUGHT = " +prodbuy+"\n" );

            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        //System.out.println("Operation done successfully");
    }

    void removecustomer() {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            Scanner sc=new Scanner(System.in);
            // System.out.println("Opened database successfully");

            stmt = c.createStatement();
            System.out.println("Enter the ID of the customer you want to remove ");
            int id = sc.nextInt();
            String sql = "DELETE from custom where ID=" + id + ";";
            stmt.executeUpdate(sql);
            c.commit();

            ResultSet rs = stmt.executeQuery("SELECT * FROM custom;");

            while (rs.next()) {
                id = rs.getInt("id");
                String name = rs.getString("name");
                String prodbuy = rs.getString("prodbuy");


                System.out.println( "ID = " + id  );
                System.out.println("NAME = " + name);
                System.out.println("PRODUCTS BOUGHT = " +prodbuy );

                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        // System.out.println("Operation done successfully");
    }



    void updatecustomer() {
        Connection c = null;
        Statement stmt = null;
        Scanner updatesc = new Scanner(System.in);
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            //System.out.println("Opened database successfully");

            stmt = c.createStatement();
            System.out.println(" Enter the name you want to change ");
            String name  =updatesc.next();
            System.out.println(" Enter the ID you want to change name in");
            int id =updatesc.nextInt();

            String sql = "UPDATE custom set name = '" +name + "' where "+id+";";
            stmt.executeUpdate(sql);
            c.commit();

            ResultSet rs = stmt.executeQuery( "SELECT * FROM custom;" );

            while ( rs.next() ) {
                id = rs.getInt("id");
                name = rs.getString("name");
                String prodbuy = rs.getString("prodbuy");

                System.out.println( "ID = " + id  );
                System.out.println("NAME = " + name);
                System.out.println("PRODUCTS BOUGHT = " + prodbuy);

                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        // System.out.println("Operation done successfully");
    }

}



class Hr {
    void Hr_people() {
        String names[] = {"abbas", "fatima ", "habib"};

        for (int i = 0; i < names.length; i++) {


        }
        System.out.println(Arrays.toString(names));


    }


}
class staff_department {
    static void stafmembers() {
        String securtity_members_names[] = {"mahad", "hamza  ", "afzal"};

        for (int i = 0; i < securtity_members_names.length; i++) {


        }
        System.out.println(Arrays.toString(securtity_members_names));

    }

    void stafsalary() {
        //   void password() {

        String sec_salary[] = {"mahad:45000", "hamza:6800  ", "afzal:34500"};
        //  String sec_salary="hj";

        for (int i = 0; i < sec_salary.length; i++) {


        }
        System.out.println(Arrays.toString(sec_salary));


    }
}