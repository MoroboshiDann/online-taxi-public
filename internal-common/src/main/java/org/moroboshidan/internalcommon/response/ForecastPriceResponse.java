package org.moroboshidan.internalcommon.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForecastPriceResponse {
    private String cityCode;
    private String vehicleType;
    private double price;
    private String fareType;
    private Integer fareVersion;
}
