package mysql2;

public class Person implements Comparable{
    private String firstName;
    private String lastName;
    private String adress;
    private int phoneNr;

    Person(String firstName, String lastName, String adress, int phoneNr) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.adress = adress;
        this.phoneNr = phoneNr;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(int phoneNr) {
        this.phoneNr = phoneNr;
    }

    @Override
    public int compareTo(Object o) {
        String firstName = ((Person)o).getFirstName();
        return (this.firstName.compareTo(firstName));
    }
    
}
