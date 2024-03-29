package org.moroboshidan.internalcommon.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDriverResponse {
    private Long driverId;
    private String driverPhone;
    private Long carId;
    private String licenseId;
    private String vehicleNo;
}
