package kr.co.miracom.mes.wip.resource.simple.order_bom.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.config.MesConst;
import kr.co.miracom.mes.wip.resource.simple.order_bom.model.OrderBom;
import kr.co.miracom.mes.wip.resource.simple.order_bom.model.OrderBomComp;
import kr.co.miracom.mes.wip.resource.simple.order_bom.model.OrderBomDetail;
import kr.co.miracom.mes.wip.resource.simple.order_bom.service.get_list.GetOrderBomListIn;

/**
 * Setup Controller: OrderBom
 * @author myjung.jung
 * @since 2018. 06. 26.
 */
@Api(value = "Creating, Retrieving, Updating and Deleting OrderBoms.")
@RequestMapping(path = MesConst.VER_PATH)
public interface OrderBomController {
    static final String RESOURCE = "wip/orderBoms";

    @GetMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Get OrderBom")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "select", value = "Field Names of OrderBom for Select", allowMultiple = true),
                    @ApiImplicitParam(name = "unselect", value = "Field Names of OrderBom for Unselect", allowMultiple = true) })
    OrderBomDetail get(@PathVariable String id, SelectOptions options) throws Exception;

    @PostMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Insert OrderBom")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "data", value = "OrderBom Json", dataType = "OrderBomDetail", required = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of OrderBom for Update", allowMultiple = true) })
    void post(@PathVariable String id, @RequestBody OrderBomDetail data,
                    @RequestParam(required = false) String[] fieldName) throws Exception;

    @PutMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Update OrderBom")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "data", value = "OrderBom Json", dataType = "OrderBomDetail", required = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of OrderBom for Update", allowMultiple = true) })
    void put(@PathVariable String id, @RequestBody OrderBomDetail data,
                    @RequestParam(required = false) String[] fieldName) throws Exception;

    @GetMapping(path = RESOURCE)
    @ApiOperation("Get OrderBom List")
    GetListOut<OrderBom> getList(GetOrderBomListIn input) throws Exception;

    @PostMapping(path = RESOURCE + "/save")
    @ApiOperation("Save OrderBom List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "OrderBom List", dataType = "OrderBom", required = true, allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of OrderBom for Save", allowMultiple = true) })
    void saveList(@RequestBody List<OrderBom> list, @RequestParam(required = false) String[] fieldName)
                    throws Exception;

    @DeleteMapping(path = RESOURCE)
    @ApiOperation("Delete OrderBom List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "OrderBom List", dataType = "OrderBom", required = true, allowMultiple = true) })
    void deleteList(@RequestBody List<OrderBom> list) throws Exception;

    @GetMapping(path = RESOURCE + "/{id}/comps")
    @ApiOperation("Get OrderBom Comp List")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID of OrderBom", required = true) })
    GetListOut<OrderBomComp> getCompList(@PathVariable String id) throws Exception;

    @PostMapping(path = RESOURCE + "/{id}/comps/save")
    @ApiOperation("Save OrderBom Comp List")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID of OrderBom", required = true),
                    @ApiImplicitParam(name = "list", value = "OrderBom Comp List", dataType = "OrderBomComp", allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Comp for Save", allowMultiple = true) })
    void saveCompList(@PathVariable String id, @RequestBody List<OrderBomComp> list,
                    @RequestParam(required = false) String[] fieldName) throws Exception;

}
