package view;

import model.Person;

import java.util.List;

public class PersonView {

    public void displayPersons(List<Person> persons) {

        for (Person person : persons) {

            System.out.println(
                    "Name: " + person.getName()
                    + " | Gender: " + person.getGender()
                    + " | Birthday: " + person.getBirthday()
            );
        }
    }
}