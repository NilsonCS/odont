package com.odont.odont.models.dao;
// este bicho no se esta usando no importa si se borra despues
import com.odont.odont.models.entity.PersonEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
//// model del  user tiene su respectiva informacion de usuario

@Entity
@Data
@EqualsAndHashCode(of = {"id"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TelegramUser {
    @Id
    Integer id;
    LocalDateTime creationDate;
    String userName;
    Boolean bot;
    String lastName;
    String firstName;
    String languageCode;

    @ManyToOne
    PersonEntity person;
}
