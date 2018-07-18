package kr.co.miracom.framework.service;

import lombok.Data;

@Data
public class GetListIn {
    private int pageNumber;
    private int pageSize;
    private String[] select;
    private String[] unselect;
}
