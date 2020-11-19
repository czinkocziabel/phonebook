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

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String fName) {
        firstName.set(fName);

    }

    public String getLastName() {

        return lastName.get();
    }

    public void setLastName(String lName) {
        lastName.set(lName);

    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String e) {
        email.set(e);

    }
}
