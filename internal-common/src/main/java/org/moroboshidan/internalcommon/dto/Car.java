package org.moroboshidan.internalcommon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
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
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 车辆所在城市
     */
    private String address;

    /**
     * 车牌号
     */
    private String vehicleNo;

    /**
     * 车牌颜色，1蓝色，2黄色，3黑色，4白色，5绿色，9其他
     */
    private String plateColor;

    /**
     * 核定载客位
     */
    private Integer seats;

    /**
     * 车辆品牌
     */
    private String brand;

    /**
     * 车辆型号
     */
    private String model;

    /**
     * 车辆类型
     */
    private String vehicleType;

    /**
     * 车主姓名
     */
    private String ownerName;

    /**
     * 车辆颜色
     */
    private String vehicleColor;

    /**
     * 发动机号
     */
    private String engineId;

    /**
     * 车辆VIN号，以行驶证为准
     */
    private String vin;

    /**
     * 车辆注册日期
     */
    private LocalDate certifyDateA;

    /**
     * 燃料类型
     */
    private String fuelType;

    /**
     * 发动机排量
     */
    private String engineDisplace;

    /**
     * 运输证发证机构
     */
    private String transAgency;

    /**
     * 运输证发证地
     */
    private String transArea;

    private LocalDate transDateStart;

    private LocalDate transDateEnd;

    /**
     * 车辆初次登记日期
     */
    private LocalDate certifyDateB;

    /**
     * 车辆检修状态，0未检修，1已检修，2未知
     */
    private String fixState;

    /**
     * 下次检修日期
     */
    private LocalDate nextFixDate;

    /**
     * 年审状态：0未年审，1年审合格，2年审不合格
     */
    private String checkState;

    /**
     * 发票打印设备号
     */
    private String feePrintId;

    private String gpsBrand;

    private String gpsModel;

    private LocalDate gpsInstallDate;

    /**
     * 报备日期
     */
    private LocalDate registerDate;

    private Integer commercialType;

    /**
     * 运价类型编码
     */
    private String fareType;

    /**
     * 0有效，1无效
     */
    private Integer state;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtUpdate;
}
