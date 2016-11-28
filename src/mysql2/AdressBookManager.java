package mysql2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AdressBookManager{
    List<Person> addressBook = new ArrayList<Person>();
    SingletonConnection singletonConnection = SingletonConnection.getInstance();

    public AdressBookManager() {
        menu();
    }
    
    public void addPerson(){
        Scanner sc = new Scanner(System.in);
        String firstName;
        String lastName;
        String adress;
        int phoneNr;
        
        System.out.print("Podaj imie: ");
        firstName = sc.nextLine();
        System.out.print("Podaj nazwisko: ");
        lastName = sc.nextLine();
        System.out.print("Podaj adres: ");
        adress = sc.nextLine();
        System.out.print("Podaj numer telefonu: ");
        phoneNr = sc.nextInt();
        System.out.println();
        
        addressBook.add(new Person(firstName, lastName, adress, phoneNr));
    }

    void menu() {
        Scanner sc = new Scanner(System.in);
        int wybor = 0;
        
        do{
        System.out.println("1. Dodaj osobe");
        System.out.println("2. Wypisz osoby");
        System.out.println("3. Dodaj osobe do bazy danych");
        System.out.println("4. Wyswietl osoby z bazy danych");
        System.out.println("5. Koniec");
        
        do{
            System.out.print("Wybor: ");
            wybor = sc.nextInt();
            System.out.println();
            
            if(wybor == 1){
                addPerson();
            }else if(wybor == 2){
                Collections.sort(addressBook);
                listPerson();
            }else if(wybor == 3){
                singletonConnection.addToDB();
            }else if(wybor == 4){
                ResultSet resultSet = singletonConnection.readFromDB();
                 try {
                     while(resultSet.next()){
                         System.out.println("ID: " + resultSet.getString(1) + "\nImie: " + resultSet.getString(2) + "\nNazwisko: " + resultSet.getString(3) + "\nAdres: : " + resultSet.getString(4) + "\nNumer telefonu: : " + resultSet.getString(5));
                         System.out.println();
                     }
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
            }else if(wybor == 5){
                System.exit(0);
            }
        }while(wybor != 1 && wybor != 2 && wybor != 3 && wybor != 4 && wybor != 5);
        }while(wybor != 5);
    }

    private void listPerson() {
        for(int i = 0;i < addressBook.size();i++){
            System.out.println("Imie: " + addressBook.get(i).getFirstName() + "\nNazwisko: " + addressBook.get(i).getLastName() + "\nAdres: : " + addressBook.get(i).getAdress() + "\nNumer telefonu: : " + addressBook.get(i).getPhoneNr());
            System.out.println();
        }
    }
}
