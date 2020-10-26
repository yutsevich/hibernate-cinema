package com.dev.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovieResponseDto {
    private Long id;
    private String title;
    private String description;
}
