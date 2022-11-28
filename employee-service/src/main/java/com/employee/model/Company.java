package com.employee.model;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    private Integer id;

    @NotBlank(message = "Company name cannot be left blank.")
    private String companyName;
}
