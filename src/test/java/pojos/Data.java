package pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.platform.commons.util.ToStringBuilder;

import java.util.List;

public class Data<Datum> {


    @JsonProperty("data")
    private List<Datum> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Data() {
    }

    /**
     *
     * @param data
     */
    public Data(List<Datum> data) {
        super();
        this.data = data;
    }

    @JsonProperty("data")
    public List<Datum> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<Datum> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("data", data).toString();
    }
}
