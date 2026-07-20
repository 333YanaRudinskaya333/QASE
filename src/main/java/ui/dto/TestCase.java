package ui.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestCase {

    private String title;
    private String status;
    private String suite;
    private String severity;
    private String priority;
    private String type;
    private String layer;
    private String isFlaky;
    private String milestone;
    private String behavior;
    private String automation;
    private String description;
    private String preConditions;
    private String postConditions;
    private boolean isMuted;
}