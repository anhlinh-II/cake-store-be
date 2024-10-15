package com.vn.cake_store.entity;

import jakarta.persistence.*;
import java.time.Instant;
import lombok.Data;

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
    private Instant hireDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = true)  // Foreign key in employee table
    private Store store;

    @PrePersist
    protected void beforeCreate() {
        this.hireDate = Instant.now();
    }

}

