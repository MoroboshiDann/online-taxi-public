package org.moroboshidan.service;

import lombok.extern.slf4j.Slf4j;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.request.OrderRequest;
import org.moroboshidan.remote.ServiceOrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {
    @Autowired
    private ServiceOrderClient serviceOrderClient;


    public ResponseResult addOrder(OrderRequest orderRequest) {
        return serviceOrderClient.addOrder(orderRequest);
    }

    public ResponseResult dispatchOrder(Long orderId) {
        return serviceOrderClient.dispatchOrder(orderId);
    }
}
