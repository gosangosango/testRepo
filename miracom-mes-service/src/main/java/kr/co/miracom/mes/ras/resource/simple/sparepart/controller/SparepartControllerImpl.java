package kr.co.miracom.mes.ras.resource.simple.sparepart.controller;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.ras.resource.simple.sparepart.model.Sparepart;
import kr.co.miracom.mes.ras.resource.simple.sparepart.model.SparepartDetail;
import kr.co.miracom.mes.ras.resource.simple.sparepart.service.SparepartService;
import kr.co.miracom.mes.ras.resource.simple.sparepart.service.get_list.GetSparepartList;
import kr.co.miracom.mes.ras.resource.simple.sparepart.service.get_list.GetSparepartListIn;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Setup Controller: Spt
 * @author hhk
 * @since 2018. 07. 02.
 */
@CrossOrigin
@RestController
public class SparepartControllerImpl implements SparepartController {

    @Override
    public SparepartDetail get(@PathVariable String id, SelectOptions options) throws Exception {
        return BeanUtils.get(SparepartService.class).get(id, options);
    }

    @Override
    public void put(@PathVariable String id, @RequestBody SparepartDetail data, @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(SparepartService.class).put(id, data, fieldName);
    }

    @Override
    public GetListOut<Sparepart> getList(GetSparepartListIn input) throws Exception {
        return BeanUtils.get(GetSparepartList.class).getList(input);
    }

    @Override
    public void saveList(@RequestBody List<Sparepart> list, @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(SparepartService.class).saveList(list, fieldName);
    }

    @Override
    public void deleteList(@RequestBody List<Sparepart> list) throws Exception {
        BeanUtils.get(SparepartService.class).deleteList(list);
    }

}
