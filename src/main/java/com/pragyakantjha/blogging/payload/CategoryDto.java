package com.pragyakantjha.blogging.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Integer categoryId;
    @NotBlank
    @Size(min = 3)
    private String categoryTitle;
    @NotBlank
    @Size(min = 10, max = 200)
    private String categoryDescription;
}
