package com.programboard.programboard.dtos;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public record FeatureRecordDto(@NotBlank String title, @NotBlank String hypothesis, @NotBlank String acceptanceCriteria, @NotNull Integer priority, Timestamp valueDate) {
}
