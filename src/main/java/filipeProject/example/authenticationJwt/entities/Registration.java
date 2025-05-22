package filipeProject.example.authenticationJwt.entities;


import filipeProject.example.authenticationJwt.enums.Payment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;


@Entity
@Table(name = "tb_registration")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant registrationDate;

    @Enumerated(EnumType.STRING)
    private Payment paymentStatus;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "talk_id")
    private Talk talk;

}
