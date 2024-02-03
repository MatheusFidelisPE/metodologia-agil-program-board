package com.programboard.programboard.dtos;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FeatureRecordDto(@NotBlank String title, @NotBlank String hypothesis, @NotBlank String acceptanceCriteria, @NotNull String priority) {
}
