package com.example.demo.Util;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JwtRequest {

    String username;
    String password;

}
