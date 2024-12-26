package com.social.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Reels {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;

    private String title;

    private String video;

    @ManyToOne
    private User user;




}
