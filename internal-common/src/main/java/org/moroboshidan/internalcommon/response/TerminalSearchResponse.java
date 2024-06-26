package org.moroboshidan.internalcommon.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TerminalSearchResponse {
    private Long driveMile;
    private Long driveTime;
}
