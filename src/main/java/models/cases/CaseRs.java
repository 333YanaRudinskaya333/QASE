package models.cases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class CaseRs {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("result")
    @Expose
    private Result result;
}
