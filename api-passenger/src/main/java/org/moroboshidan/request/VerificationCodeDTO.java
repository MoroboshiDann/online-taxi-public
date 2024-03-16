package org.moroboshidan.request;

public class VerificationCodeDTO {
    private String passengerPhone;

    public VerificationCodeDTO() {
    }

    public VerificationCodeDTO(String passengerPhone) {
        this.passengerPhone = passengerPhone;
    }

    /**
     * 获取
     * @return passengerPhone
     */
    public String getPassengerPhone() {
        return passengerPhone;
    }

    /**
     * 设置
     * @param passengerPhone
     */
    public void setPassengerPhone(String passengerPhone) {
        this.passengerPhone = passengerPhone;
    }

    public String toString() {
        return "VerificationCodeDTO{passengerPhone = " + passengerPhone + "}";
    }
}
