package hongik.project.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserForm {

    @NotEmpty(message = "이름은 필수")
    private String name;
    private String password;
    private int age;

}