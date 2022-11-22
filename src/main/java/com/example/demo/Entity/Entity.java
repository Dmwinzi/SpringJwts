package com.example.demo.Entity;


import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@javax.persistence.Entity
@Table(name = "users")
public class Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   Long id;
    String username;
    String password;
    String email;


}
