package com.example.firstspringapp.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDto {

    private Long id;
    private String fullName;

}
