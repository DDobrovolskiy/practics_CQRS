package dda.cqrs.gateway.person;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.UUID;

@ApiModel(description = "Персональные данные по участнику")
public class PersonInfo {

    @ApiModelProperty("Уникальный идентификатор участника")
    private UUID personId;

    @ApiModelProperty("Фамилия")
    private String familyName;

    @ApiModelProperty("Имя")
    private String firstName;

    @ApiModelProperty("Отчество")
    private String patronymic;

    @ApiModelProperty("Дата рождения")
    private LocalDate birthDate;

    public PersonInfo(UUID personId, String familyName, String firstName, String patronymic, LocalDate birthDate) {
        this.personId = personId;
        this.familyName = familyName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
    }

    public UUID getPersonId() {
        return personId;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
