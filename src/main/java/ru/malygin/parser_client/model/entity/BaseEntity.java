package ru.malygin.parser_client.model.entity;

import ru.malygin.parser_client.model.dto.BaseDto;

public interface BaseEntity {
    BaseDto toBaseDto();

    boolean hasRequiredField();
}
