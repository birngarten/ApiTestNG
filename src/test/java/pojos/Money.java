package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.platform.commons.util.ToStringBuilder;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Money {
    @JsonProperty("rates")
    private Rates rates;
    @JsonProperty("base")
    private String base;
    @JsonProperty("date")
    private String date;
    public Money() {
    }

    /**
     *
     * @param date
     * @param rates
     * @param base
     */
    public Money(Rates rates, String base, String date) {
        super();
        this.rates = rates;
        this.base = base;
        this.date = date;
    }

    @JsonProperty("rates")
    public Rates getRates() {
        return rates;
    }

    @JsonProperty("rates")
    public void setRates(Rates rates) {
        this.rates = rates;
    }

    @JsonProperty("base")
    public String getBase() {
        return base;
    }

    @JsonProperty("base")
    public void setBase(String base) {
        this.base = base;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("rates", rates).append("base", base).append("date", date).toString();
    }

}