package org.moroboshidan.internalcommon.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiDriverRequest {
    private Long carId;
    private PointDTO[] points;
}
