package org.moroboshidan.internalcommon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverUser {
    private Integer id;
    private String address;
    private String driverName;
    private String driverPhone;
    private LocalDateTime driverBirthday;
    private String driverNation;
    private String driverContactAddress;
    private String licenseId;
    private LocalDateTime getDriverLicenseDate;
    private LocalDateTime driverLicenseOn;
    private LocalDateTime driverLicenseOff;
    private Integer taxiDriver;
    private String certificateNo;
    private String networkCarIssueOrganization;
    private LocalDateTime networkCarIssueDate;
    private LocalDateTime getNetworkCarProofDate;
    private LocalDateTime networkCarProofOn;
    private LocalDateTime networkCarProofOff;
    private Integer commercialType;
    private String contractCompany;
    private LocalDateTime contractOn;
    private LocalDateTime contractOff;
    private Integer state;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtUpdate;
}
