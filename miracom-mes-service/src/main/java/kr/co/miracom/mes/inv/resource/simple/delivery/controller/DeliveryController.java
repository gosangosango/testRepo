package kr.co.miracom.mes.inv.resource.simple.delivery.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
import kr.co.miracom.mes.inv.resource.simple.delivery.model.Delivery;
import kr.co.miracom.mes.inv.resource.simple.delivery.model.DeliveryDetail;
import kr.co.miracom.mes.inv.resource.simple.delivery.model.DeliveryDlvDetail;
import kr.co.miracom.mes.inv.resource.simple.delivery.service.get_list.GetDeliveryListIn;
import kr.co.miracom.mes.inv.resource.simple.delivery.service.inspect_list.InspectDeliveryListIn;
import kr.co.miracom.mes.inv.resource.simple.delivery.service.inspect_list.InspectDeliveryListOut;

/**
 * Setup Controller: Delivery
 * @author myjung.jung
 * @since 2018. 06. 25.
 */
@Api(value = "Creating, Retrieving, Updating and Deleting Deliveries.")
@RequestMapping(path = MesConst.VER_PATH)
public interface DeliveryController {
    static final String RESOURCE = "inv/deliveries";

    @GetMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Get Delivery")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "select", value = "Field Names of Delivery for Select", allowMultiple = true),
                    @ApiImplicitParam(name = "unselect", value = "Field Names of Delivery for Unselect", allowMultiple = true) })
    DeliveryDetail get(@PathVariable String id, SelectOptions options) throws Exception;

    // @PutMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Update Delivery")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "data", value = "Delivery Json", dataType = "DeliveryDetail", required = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Delivery for Update", allowMultiple = true) })
    void put(@PathVariable String id, @RequestBody DeliveryDetail data,
                    @RequestParam(required = false) String[] fieldName) throws Exception;

    @GetMapping(path = RESOURCE)
    @ApiOperation("Get Delivery List")
    GetListOut<Delivery> getList(GetDeliveryListIn input) throws Exception;

    // @PostMapping(path = RESOURCE + "/save")
    @ApiOperation("Save Delivery List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "Delivery List", dataType = "Delivery", required = true, allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Delivery for Save", allowMultiple = true) })
    void saveList(@RequestBody List<Delivery> list, @RequestParam(required = false) String[] fieldName)
                    throws Exception;

    // @DeleteMapping(path = RESOURCE)
    @ApiOperation("Delete Delivery List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "Delivery List", dataType = "Delivery", required = true, allowMultiple = true) })
    void deleteList(@RequestBody List<Delivery> list) throws Exception;

    @PostMapping(path = RESOURCE + "/inspect")
    @ApiOperation("Inspect Delivery List")
    InspectDeliveryListOut inspectList(@RequestBody InspectDeliveryListIn input) throws Exception;

    @GetMapping(path = RESOURCE + "/{id}/dlvDetails")
    @ApiOperation("Get Delivery DlvDetail List")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID of Delivery", required = true) })
    GetListOut<DeliveryDlvDetail> getDlvDetailList(@PathVariable String id) throws Exception;

    // @PostMapping(path = RESOURCE + "/{id}/dlvDetails/save")
    @ApiOperation("Save Delivery DlvDetail List")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID of Delivery", required = true),
                    @ApiImplicitParam(name = "list", value = "Delivery DlvDetail List", dataType = "DeliveryDlvDetail", allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of DlvDetail for Save", allowMultiple = true) })
    void saveDlvDetailList(@PathVariable String id, @RequestBody List<DeliveryDlvDetail> list,
                    @RequestParam(required = false) String[] fieldName) throws Exception;

}
