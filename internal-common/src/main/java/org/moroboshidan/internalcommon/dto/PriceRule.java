package org.moroboshidan.internalcommon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceRule {
    private String cityCode;
    private String vehicleType;
    private double startFare;
    private int startMile;
    private double unitPricePerMile;
    private double unitPricePerMinute;
}
