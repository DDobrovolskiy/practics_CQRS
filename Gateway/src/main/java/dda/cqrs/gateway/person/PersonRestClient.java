package dda.cqrs.gateway.person;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class PersonRestClient {

    public PersonInfo getPersonInfo(UUID personId) {
        // todo реализовать обращение к сервису
        return new PersonInfo(personId, "Пономарёв", "Вячеслав", "Алексеевич", LocalDate.now().minusYears(27));
    }

}
