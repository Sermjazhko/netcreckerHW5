package com.netcrecker.model;

import javax.persistence.*;
import java.util.Objects;
// что данный бин сущность
@Entity
@Table(name = "users") //имя таблицы, которая будет отображаться в этой сущности
public class User {
    @Id // первичный ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY) // указывает, что свойство будет создаваться согласно указанной стратегии
    private Integer id;

    private String name;
    private String surname;
    private String patronymic;

    private int age;
    private int salary;
    private String mail;
    private String workplace;


    public User(String name, String surname, String patronymic, int age, int salary, String email, String workplace) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.age = age;
        this.salary = salary;
        this.mail = email;
        this.workplace = workplace;
    }

    public User() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return mail;
    }

    public void setEmail(String email) {
        this.mail = email;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    /* @Override
     public boolean equals(Object o) {
         if (this == o) return true;
         if (o == null || getClass() != o.getClass()) return false;
         User user = (User) o;
         return age == user.age && salary == user.salary && name.equals(user.name)
                 && surname.equals(user.surname) && patronymic.equals(user.patronymic)
                 && mail.equals(user.mail) && workplace.equals(user.workplace);
     }

     @Override
     public int hashCode() {
         return Objects.hash(name, surname, patronymic, age, salary, mail, workplace);
     }
 */
    @Override
    public String toString() {
        return "User[name="+name+", sn="+surname+", patr="+patronymic
                +", age="+age+", salary="+ salary+", email="+mail
                +", wp="+workplace+"]";
    }


}
