package ua.goit.java.dao.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "employees")
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
public class Employee {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int ID;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "dateOfBirth")
    private String dateOfBirth;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private Position position;

    @Column(name = "salary")
    private double salary;

    @Column(name = "photo_url")
    private String photoURL;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "ID=" + ID +  "\t" +
                " lastName=" + "\t" + lastName +
                " firstName=" +  "\t" + firstName +
                " dateOfBirth=" +  "\t" + dateOfBirth +
                " phoneNumber=" +  "\t" + phoneNumber +
                " position=" +  "\t" + position + "\t" +
                " salary= " + salary +
                '}';
    }
}
