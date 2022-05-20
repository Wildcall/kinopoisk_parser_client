package ru.malygin.parser_client.model.dto.impl;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.malygin.parser_client.model.dto.BaseDto;
import ru.malygin.parser_client.model.dto.view.View;
import ru.malygin.parser_client.model.entity.BaseEntity;
import ru.malygin.parser_client.model.entity.impl.Film;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmDto implements BaseDto {

    @JsonView(View.Response.class)
    private Integer pos;

    @JsonView(View.Response.class)
    private Double rating;

    @JsonView(View.Response.class)
    private String title;

    @JsonView(View.Response.class)
    private Integer prodYear;

    @JsonView(View.Response.class)
    private Integer voteCount;

    @Override
    public BaseEntity toBaseEntity() {
        //  @formatter:off
        return new Film(null,
                           this.pos,
                           this.rating,
                           this.title,
                           this.prodYear,
                           this.voteCount,
                           null);
        //  @formatter:on
    }
}
