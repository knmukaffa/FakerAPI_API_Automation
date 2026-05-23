package controller;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PersonController {

    private final String BASE_URL = "https://fakerapi.it/api/v2/persons";

    public List<Person> getPersons(
            int quantity,
            String gender,
            String birthdayStart,
            String birthdayEnd
    ) {

        String endpoint =
                BASE_URL
                + "?_quantity=" + quantity
                + "&_gender=" + gender
                + "&_birthday_start=" + birthdayStart
                + "&_birthday_end=" + birthdayEnd;

        System.out.println("\n========== HIT ENDPOINT ==========");
        System.out.println(endpoint);

        Response response = RestAssured
                .given()
                .queryParam("_quantity", quantity)
                .queryParam("_gender", gender)
                .queryParam("_birthday_start", birthdayStart)
                .queryParam("_birthday_end", birthdayEnd)
                .when()
                .get(BASE_URL);

        System.out.println("\n========== RESPONSE ==========");
        System.out.println(response.getBody().asPrettyString());

        response.then().statusCode(200);

        List<Person> persons = new ArrayList<>();

        List<Object> data = response.jsonPath().getList("data");

        for (Object obj : data) {

            Map<String, Object> map = (Map<String, Object>) obj;

            Person person = new Person();

            person.setName((String) map.get("firstname"));
            person.setGender((String) map.get("gender"));
            person.setBirthday((String) map.get("birthday"));

            persons.add(person);
        }

        return persons;
    }
}