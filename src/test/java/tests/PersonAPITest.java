package tests;

import controller.PersonController;
import model.Person;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;

public class PersonAPITest {

    @Test
    public void verifyPersonAPI() {

        PersonController controller = new PersonController();

        String startDate = "1990-01-01";
        String endDate = "2000-12-31";

        List<Person> persons =
                controller.getPersons(
                        10,
                        "male",
                        startDate,
                        endDate
                );

        Assert.assertEquals(
                persons.size(),
                10,
                "FAILED: Total data is not equal to 10"
        );

        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        for (Person person : persons) {

            Assert.assertEquals(
                    person.getGender(),
                    "male",
                    "FAILED: Gender validation failed for person: "
                    + person.getName()
            );

            LocalDate birthday =
                    LocalDate.parse(person.getBirthday());

            Assert.assertTrue(
                    (!birthday.isBefore(start))
                    &&
                    (!birthday.isAfter(end)),
                    "FAILED: Birthday is out of expected range for person: "
                    + person.getName()
            );
        }

        System.out.println(
                "\n========== TEST RESULT =========="
        );

        System.out.println(
                "All validations passed successfully."
        );
    }
}