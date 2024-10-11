package com.vn.cake_store.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

import com.vn.cake_store.entity.constants.Role;

@Entity
@Table(name = "employee")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;  // Primary key

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private Double salary;

    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)  // Foreign key in employee table
    private Store store;

}

