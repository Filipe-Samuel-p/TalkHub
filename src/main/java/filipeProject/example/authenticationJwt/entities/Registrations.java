package filipeProject.example.authenticationJwt.entities;


import filipeProject.example.authenticationJwt.enums.Payment;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;


@Entity
@Table(name = "tb_registrations")
public class Registrations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant registrationDate;
    private Enum<Payment> paymentStatus;
    private Boolean isConfirmed;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "talk_id")
    private Talk talk;



}
