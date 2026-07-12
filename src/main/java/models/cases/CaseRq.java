package models.cases;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CaseRq {
    @Expose
    private String code;
    @Expose
    private String description;
    @Expose
    private String preconditions;
    @Expose
    private String postconditions;
    @Expose
    private String title;
    @Expose
    private Integer severity;
    @Expose
    private Integer priority;
    @Expose
    private Integer behavior;
    @Expose
    private Integer type;
    @Expose
    private Integer layer;
    @Expose
    private Integer is_flaky;
    @Expose
    private Integer suite_id;
    @Expose
    private Integer milestone_id;
    @Expose
    private Integer automation;
    @Expose
    private Integer isManual;
    @Expose
    private Integer isToBeAutomated;
    @Expose
    private Integer status;
}
