package com.Easton.motordesk_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "work_order")

@Entity
public class WorkOrder {

    //Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_order_id")
    private Long id;

    //Foreign Keys
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "created_by_user_id", nullable = false)
    private AppUser createdBy;

    @ManyToOne
    @JoinColumn(name = "assigned_technician_id")  // no nullable=false — matches your SQL, nullable on purpose (not assigned yet)
    private AppUser assignedTechnician;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    //Other attributes
    @Column(name = "description", length = 250)
    private String description;

    public enum Status {
        CHECKED_IN,
        DIAGNOSING,
        WAITING_APPROVAL,
        WAITING_PARTS,
        IN_PROGRESS,
        COMPLETED,
        READY_FOR_PICKUP
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    //runs once when row is first created
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    //runs everytime an existing row is modified and saved
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
