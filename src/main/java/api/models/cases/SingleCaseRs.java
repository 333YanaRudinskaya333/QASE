package api.models.cases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class SingleCaseRs {
    @SerializedName("status")
    @Expose
    private Boolean status;

    @SerializedName("result")
    @Expose
    private CaseData result;

    @Data
    public static class CaseData {
        @Expose
        private Integer id;
        @Expose
        private Integer position;
        @Expose
        private String title;
        @Expose
        private String description;
        @Expose
        private String preconditions;
        @Expose
        private String postconditions;
        @Expose
        private Integer severity;
        @Expose
        private Integer priority;
        @Expose
        private Integer type;
        @Expose
        private Integer layer;
        @Expose
        private Integer is_flaky;
        @Expose
        private Boolean is_muted;
        @Expose
        private Integer behavior;
        @Expose
        private Integer automation;
        @Expose
        private Boolean isManual;
        @Expose
        private Boolean isToBeAutomated;
        @Expose
        private Integer status;
        @Expose
        private Integer milestone_id;
        @Expose
        private Integer suite_id;
        @Expose
        private Integer member_id;
        @Expose
        private Integer author_id;
        @Expose
        private String author_uuid;
        @Expose
        private String created;
        @Expose
        private String updated;
        @Expose
        private String created_at;
        @Expose
        private String updated_at;
        @Expose
        private List<Object> links;
        @Expose
        private List<Object> custom_fields;
        @Expose
        private List<Object> attachments;
        @Expose
        private List<Object> steps;
        @Expose
        private List<Object> params;
        @Expose
        private List<Object> parameters;
        @Expose
        private List<Object> tags;
        @Expose
        private String steps_type;
        @Expose
        private String deleted;
    }
}