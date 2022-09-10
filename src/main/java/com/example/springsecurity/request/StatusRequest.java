package com.example.springsecurity.request;

import com.example.springsecurity.models.PackageStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatusRequest {
    private Long id;
    private PackageStatus status;
}
