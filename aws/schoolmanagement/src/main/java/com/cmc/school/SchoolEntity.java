package com.cmc.school;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchoolEntity {
    private Integer id;
    private String name;
    private Integer studentNumbers;
}
