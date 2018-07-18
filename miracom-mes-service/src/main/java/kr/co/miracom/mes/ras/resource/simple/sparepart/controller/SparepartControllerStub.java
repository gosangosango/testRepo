package kr.co.miracom.mes.ras.resource.simple.sparepart.controller;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.ras.resource.simple.sparepart.model.Sparepart;
import kr.co.miracom.mes.ras.resource.simple.sparepart.model.SparepartDetail;
import kr.co.miracom.mes.ras.resource.simple.sparepart.service.get_list.GetSparepartListIn;

/**
 * Setup Controller: Spt
 * @author hhk
 * @since 2018. 07. 02.
 */
public class SparepartControllerStub implements SparepartController {

    @Override
    public SparepartDetail get(String id, SelectOptions options) throws Exception {
        return null;
    }

    @Override
    public void put(String id, SparepartDetail data, String[] fieldName) throws Exception {

    }

    @Override
    public GetListOut<Sparepart> getList(GetSparepartListIn input) throws Exception {
        return null;
    }

    @Override
    public void saveList(List<Sparepart> list, String[] fieldName) throws Exception {

    }

    @Override
    public void deleteList(List<Sparepart> list) throws Exception {

    }

}
