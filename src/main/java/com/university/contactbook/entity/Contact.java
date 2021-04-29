package com.university.contactbook.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Обов'язкове поле!")
    @Size(max = 64, message = "Перевищено максимальну довжину тексту! (64 символа)")
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 64, message = "Перевищено максимальну довжину тексту! (64 символа)")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Обов'язкове поле!")
    @Size(max = 15, message = "Перевищено максимальну довжину тексту! (64 символа)")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Email(message = "Має бути відформатовано як еклетронна адреса")
    @Size(max = 256, message = "Перевищено максимальну довжину тексту! (256 символів)")
    @Column(name = "email")
    private String email;

    @Size(max = 256, message = "Перевищено максимальну довжину тексту! (256 символів)")
    @Column(name = "path_to_photo")
    private String pathToPhoto;

    @Column(name = "deleted")
    private boolean deleted;
}
