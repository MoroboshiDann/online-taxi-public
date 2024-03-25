package org.moroboshidan.internalcommon.constant;

public class OrderConstants {
    /**
     * 订单状态：1订单开始，2司机接单，3去接乘客，4司机到达上车点，5乘客上车，开始行程，6达到目的地，7发起收款，8支付完成，9订单取消
     */
    public static final int ORDER_START = 1;
    public static final int DRIVER_RECEIVE_ORDER = 2;
    public static final int DRIVER_TO_PICK_UP_PASSENGER = 3;
    public static final int DRIVER_ARRIVE_DEPARTURE = 4;
    public static final int PICK_UP_PASSENGER = 5;
    public static final int PASSENGER_GETOFF = 6;
    public static final int TO_START_PAY = 7;
    public static final int PAY_COMPLETE = 8;
    public static final int CANCEL = 9;

}
