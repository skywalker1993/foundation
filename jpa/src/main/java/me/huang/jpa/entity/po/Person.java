package me.huang.jpa.entity.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "clan_id", foreignKey = @ForeignKey(name = "CLAN_ID_FK"))
    private Clan clan;
}
