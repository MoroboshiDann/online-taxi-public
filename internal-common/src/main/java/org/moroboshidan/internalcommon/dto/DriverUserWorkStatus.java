package org.moroboshidan.internalcommon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author MoroboshiDan
 * @since 2024-03-23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverUserWorkStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long driverId;

    private Integer workStatus;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;
}
