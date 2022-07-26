package com.example.firstspringapp.dto;

import com.example.firstspringapp.dao.SupportDao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FullSupportDto {

    private Long id;

    private String orderTitle;

    private String orderDescription;

    public FullSupportDto(SupportDao supportDao) {
        this.id = supportDao.getOrderId();
        this.orderTitle = supportDao.getOrderTitle();
        this.orderDescription = supportDao.getOrderDescription();
    }

}
