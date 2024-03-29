package org.moroboshidan.internalcommon.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceRuleRequest {
    private String fareType;
    private Integer fareVersion;
}
