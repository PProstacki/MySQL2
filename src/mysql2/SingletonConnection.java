package mysql2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class SingletonConnection {

    private static SingletonConnection instance = null;
    String ConnectionURL = "jdbc:mysql://127.0.0.1/person?user=root&password=haslo";
    Connection connection;
    Statement statement;

    private SingletonConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(ConnectionURL);
            statement = connection.createStatement();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
    }

    public static SingletonConnection getInstance() {
        if (instance == null) {
            instance = new SingletonConnection();
        }
        return instance;
    }

    void addToDB() {
        Scanner sc = new Scanner(System.in);
        int id;
        String firstName;
        String lastName;
        String adress;
        int phoneNr;

        System.out.print("Podaj ID: ");
        id = sc.nextInt();
        System.out.print("Podaj imie: ");
        firstName = sc.nextLine();
        System.out.print("Podaj nazwisko: ");
        lastName = sc.nextLine();
        System.out.print("Podaj adres: ");
        adress = sc.nextLine();
        System.out.print("Podaj numer telefonu: ");
        phoneNr = sc.nextInt();
        System.out.println();

        try {
            statement.executeUpdate("INSERT INTO `addressbook`.`person` (`id`, `imie`, `nazwisko`, `adres`, `nr_tel`) VALUES (" + id + ", '" + firstName + "', '" + lastName + "', '" + adress + "', '" + phoneNr + "')");
            System.out.print("Dodano człowieka do bazy danych");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    ResultSet readFromDB() {
        try {
            return statement.executeQuery("Select * from person");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
