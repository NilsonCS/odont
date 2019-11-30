package com.odont.odont.models.dto;

import com.odont.odont.models.entity.AddressEntity;

import java.math.BigDecimal;

public class AddressDto {
    private Integer addressId;
    //private BigDecimal latitute;
    private BigDecimal longitud;

    public AddressDto() {
    }

    public AddressDto(AddressEntity cpAddress) {
        //this.addressId = cpAddress.getAddressId();
        //this.latitute = cpAddress.getLatitute();
        //this.longitud = cpAddress.getLongitud();
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }
//
//    public BigDecimal getLatitute() {
//        return latitute;
//    }
//
//    public void setLatitute(BigDecimal latitute) {
//        this.latitute = latitute;
//    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }
}
