package com.dev.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovieRequestDto {
    private String title;
    private String description;
}
