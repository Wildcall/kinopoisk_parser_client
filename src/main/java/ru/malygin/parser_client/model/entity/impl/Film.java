package ru.malygin.parser_client.model.entity.impl;

import lombok.*;
import ru.malygin.parser_client.model.dto.BaseDto;
import ru.malygin.parser_client.model.dto.impl.FilmDto;
import ru.malygin.parser_client.model.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "films")
public class Film implements BaseEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "pos", nullable = false)
    private Integer pos;

    @Column(name = "rating", nullable = false)
    private Double rating;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "prod_year", nullable = false)
    private Integer prodYear;

    @Column(name = "vote_count", nullable = false)
    private Integer voteCount;

    @Column(name = "insert_date", nullable = false)
    private LocalDate insertDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Film film = (Film) o;

        return id.equals(film.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public BaseDto toBaseDto() {
        //  @formatter:off
        return new FilmDto(this.pos,
                           this.rating,
                           this.title,
                           this.prodYear,
                           this.voteCount);
        //  @formatter:on
    }

    @Override
    public boolean hasRequiredField() {
        return false;
    }
}
