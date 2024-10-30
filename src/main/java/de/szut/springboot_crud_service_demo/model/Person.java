package de.szut.springboot_crud_service_demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
public class Person {

    private final int id;
    private final String firstname;
    private final String lastname;
}
