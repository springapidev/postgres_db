package com.coderbd.entity;

import com.coderbd.annotation.CourseTypeValidation;
import com.coderbd.annotation.PastLocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Not Null allowed")
    private String notNull;
    @NotEmpty(message = "Not Empty allowed")
    private String notEmpty;

    @NotBlank(message = "Not Null & Not Empty are allowed")
    private String notBlank;
    @Pattern(regexp = "^[0-9]{11}$")
    private String mobileNo;
    @NotBlank(message = "Email Can not be null or empty")
    @Email(message = "Email Should be valid")
    private String email;

    @Min(value = 5, message = "price At least 5 ")
    @Max(value = 10, message = "price At Most 10 ")
    private double price;

    @Length(min = 5, max = 10, message = "Description Length should be between 5 and 10")
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Past(message = "Start Date can be before of current date")
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @PastLocalDate
    private LocalDate birthDate;

    @CourseTypeValidation
    private String courseType;

}
