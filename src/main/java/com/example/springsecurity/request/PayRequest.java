package com.example.springsecurity.request;

import com.example.springsecurity.models.DeliveryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PayRequest {
    private Long id;
    private DeliveryType deliveryType;
}
