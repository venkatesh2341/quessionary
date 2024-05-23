package com.quessionary.app.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class QuestionDTO {

    private UUID userId;
    private String title;
    private String body;

}
