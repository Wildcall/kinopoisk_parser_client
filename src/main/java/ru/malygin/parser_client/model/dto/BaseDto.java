package ru.malygin.parser_client.model.dto;

import ru.malygin.parser_client.model.entity.BaseEntity;

public interface BaseDto {
    BaseEntity toBaseEntity();
}
