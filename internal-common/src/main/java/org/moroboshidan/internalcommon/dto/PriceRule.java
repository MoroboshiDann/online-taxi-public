package org.moroboshidan.internalcommon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceRule implements Serializable {
    private String cityCode;
    private String vehicleType;
    private Double startFare;
    private Integer startMile;
    private Double unitPricePerMile;
    private Double unitPricePerMinute;
    private String fareType;
    private Integer fareVersion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceRule priceRule = (PriceRule) o;
        return Objects.equals(cityCode, priceRule.cityCode) && Objects.equals(vehicleType, priceRule.vehicleType) && Objects.equals(startFare, priceRule.startFare) && Objects.equals(startMile, priceRule.startMile) && Objects.equals(unitPricePerMile, priceRule.unitPricePerMile) && Objects.equals(unitPricePerMinute, priceRule.unitPricePerMinute);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityCode, vehicleType, startFare, startMile, unitPricePerMile, unitPricePerMinute);
    }
}
