package org.nak.systembanker.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "request")
public class Request implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "amount")
    @Positive
    @Min(1000)
    @Max(50000)
    private double amount;

    @Column(name = "duration")
    @Min(12)
    @Max(60)
    @Positive
    private int duration;

    @Column(name = "monthly")
    @Positive
    private double monthly;

    @Column(name = "project", length = 50)
    private String project;

    @Column(name = "profession", length = 50)
    private String profession;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "gender")
    private String gender;

    @Column(name = "last_name", length = 30)
    private String lastName;

    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "id_card_number", length = 30)
    private String idCardNumber;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(name = "total_revenue")
    @Positive
    private double totalRevenue;

    @Column(name = "credits")
    private String credits;

    @Column(name = "mortgage_credit")
    private String mortgageCredit;

    @Column(name = "other_credits")
    private String otherCredits;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RequestStatus> requestStatuses = new HashSet<>();

}