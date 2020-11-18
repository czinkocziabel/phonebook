package phonebook;

import javafx.beans.property.SimpleStringProperty;

public class Person {

    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty email;

    public Person(String fName, String lName, String email) {
        this.firstName = new SimpleStringProperty(fName);
        this.lastName = new SimpleStringProperty(lName);
        this.email = new SimpleStringProperty(email);
    }

    public SimpleStringProperty getFirstName() {
        return firstName;
    }

    public void setFirstName(String fName) {
        firstName.set(fName);

    }

    public SimpleStringProperty getLastName() {

        return lastName;
    }

    public void setLastName(String lName) {
        lastName.set(lName);

    }

    public SimpleStringProperty getEmail() {
        return email;
    }

    public void setEmail(String e) {
        email.set(e);

    }
}
