package com.quessionary.app.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AnswerDTO {
    private UUID userId;
    private String text;
}
