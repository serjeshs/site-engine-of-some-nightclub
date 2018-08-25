
package by.ladyka.bepaid.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "visible",
    "read_only"
})
@Data
public class CustomerFields {

    @JsonProperty("visible")
    public List<String> visible = null;
    @JsonProperty("read_only")
    public List<String> readOnly = null;
}
