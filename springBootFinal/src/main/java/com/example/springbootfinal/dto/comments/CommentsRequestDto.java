package com.example.springbootfinal.dto.comments;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class CommentsRequestDto {
    @NotNull
    Integer customerOrderId;
    @NotNull
    Integer expertId;
    @NotNull
    String comments;
    @Max(5)
    @Min(0)
    @NotNull
    Integer star;
}
