package kr.co.miracom.framework.service;

import java.util.List;

import lombok.Data;

@Data
public class GetListOut<V> {
    private long totalSize;
    private List<V> list;
}
