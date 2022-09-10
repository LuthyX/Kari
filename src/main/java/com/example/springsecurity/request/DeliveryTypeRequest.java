package com.example.springsecurity.request;

import com.example.springsecurity.models.DeliveryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryTypeRequest {
    private Long id;
    private DeliveryType deliveryType;
}
