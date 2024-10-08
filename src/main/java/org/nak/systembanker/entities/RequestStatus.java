package org.nak.systembanker.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "requestStatus")
public class RequestStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "demande_id")
    private Request request;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @Column(name = "date_insert")
    private LocalDateTime dateInsert;
}
