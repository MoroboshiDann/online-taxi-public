package org.moroboshidan.internalcommon.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private Long orderId;
    private Long passengerId;
    private String passengerPhone;
    private String address;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderTime;
    private String departure;
    private String depLongitude;
    private String depLatitude;
    private String destination;
    private String destLongitude;
    private String destLatitude;
    private Integer encrypt;
    private String fareType;
    private Integer fareVersion;
    private String deviceCode;
    private String toPickUpPassengerLongitude;
    private String toPickUpPassengerLatitude;
    private String pickUpPassengerLongitude;
    private String pickUpPassengerLatitude;
    private String passengerGetoffLongitude;
    private String passengerGetoffLatitude;
}
