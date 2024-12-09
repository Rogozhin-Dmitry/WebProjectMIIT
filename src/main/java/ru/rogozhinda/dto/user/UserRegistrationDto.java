package ru.rogozhinda.dto.user;

import jakarta.validation.constraints.*;


public class UserRegistrationDto {
    private String username;


    private String fullname;


    private String email;


    private int age;


    private String password;


    private String confirmPassword;

    public UserRegistrationDto() {
    }

    @NotEmpty(message = "Имя пользователя не может быть пустым")
    @Size(min = 5, max = 20, message = "Имя пользователя должно быть от 5 до 20 символов")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 5, max = 20, message = "Имя должно быть от 5 до 20 символов")
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @NotEmpty(message = "Email не может быть пустым")
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Min(value = 0, message = "Возраст должен быть больше 0")
    @Max(value = 90, message = "Возраст должен быть меньше 90")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @NotEmpty(message = "Пароль не должен быть пустым")
    @Size(min = 5, max = 20, message = "Пароль должен быть от 5 до 20 символов")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotEmpty(message = "Пароль не должен быть пустым")
    @Size(min = 5, max = 20, message = "Пароль должен быть от 5 до 20 символов")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "UserRegistrationDTO{" +
                "username='" + username + '\'' +
                ", fullName='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
