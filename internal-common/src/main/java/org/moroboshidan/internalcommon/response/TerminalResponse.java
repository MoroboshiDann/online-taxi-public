package org.moroboshidan.internalcommon.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TerminalResponse {
    private String tid;
    private Long carId;
    private String longitude;
    private String latitude;
}
