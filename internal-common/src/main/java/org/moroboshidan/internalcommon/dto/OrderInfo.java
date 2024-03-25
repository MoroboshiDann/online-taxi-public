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
 * @since 2024-03-25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long passengerId;

    private String passengerPhone;

    private Long driverId;

    private String dirverPhone;

    private Long carId;

    /**
     * 订单所在地
     */
    private String address;

    /**
     * 订单发起时间
     */
    private LocalDateTime orderTime;

    /**
     * 预计用车时间
     */
    private LocalDateTime departTime;

    /**
     * 出发地具体地址
     */
    private String departure;

    /**
     * 出发地经纬度
     */
    private String depLongitude;

    private String depLatitude;

    /**
     * 目的地具体地址
     */
    private String destination;

    /**
     * 目的地经纬度
     */
    private String destLongitude;

    private String destLatitude;

    /**
     * 加密方式
     */
    private Integer encrypt;

    /**
     * 收费类型
     */
    private String fareType;
    private Integer fareVersion;

    /**
     * 接到订单时，司机初始位置经纬度
     */
    private String receiveOrderCarLongitude;

    private String receiveOrderCarLatitude;

    private LocalDateTime receiveOrderTime;

    /**
     * 驾照编号
     */
    private String licenseId;

    /**
     * 车牌号
     */
    private String vehicleNo;

    /**
     * 司机出发去接乘客的时间
     */
    private LocalDateTime toPickUpPassengerTime;

    /**
     * 位置
     */
    private String toPickUpPassengerLongitude;

    private String toPickUpPassengerLatitude;

    /**
     * 出发去接乘客时，司机的地点
     */
    private String toPickUpPassengerAddress;

    /**
     * 司机到达上车地点的时间
     */
    private LocalDateTime drverAddrivedDepartureTime;

    /**
     * 乘客上车时间
     */
    private LocalDateTime pickUpPassengerTime;

    private String pickUpPassengerLongitude;

    private String pickUpPassengerLatitude;

    private LocalDateTime passengerGetoffTime;

    private String passengerGetoffLongitude;

    private String passengerGetoffLatitude;

    private LocalDateTime cancelTime;

    /**
     * 订单撤销发起者
     */
    private Integer cancelOperator;

    /**
     * 撤销类型代码：1乘客提前撤销，2驾驶员提前撤销，3平台公司撤销，4乘客违约撤销，5驾驶员违约撤销
     */
    private Integer cancelTypeCode;

    private Long driveMile;

    private Long driveTime;

    /**
     * 订单状态：1订单开始，2司机接单，3去接乘客，4司机到达上车点，5乘客上车，开始行程，6达到目的地，7发起收款，8支付完成，9订单取消
     */
    private Integer orderStatus;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtUpdate;

}
