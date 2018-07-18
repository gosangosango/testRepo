package kr.co.miracom.mes.wip.resource.simple.flow.controller;

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
import kr.co.miracom.mes.wip.resource.simple.flow.model.Flow;
import kr.co.miracom.mes.wip.resource.simple.flow.model.FlowDetail;
import kr.co.miracom.mes.wip.resource.simple.flow.model.FlowOper;
import kr.co.miracom.mes.wip.resource.simple.flow.service.get_list.GetFlowListIn;

/**
 * Setup Controller: Flow
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
@Api(value = "Creating, Retrieving, Updating and Deleting Flows.")
@RequestMapping(path = MesConst.VER_PATH)
public interface FlowController {
    static final String RESOURCE = "wip/flows";

    @GetMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Get Flow")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "select", value = "Field Names of Flow for Select", allowMultiple = true),
                    @ApiImplicitParam(name = "unselect", value = "Field Names of Flow for Unselect", allowMultiple = true) })
    FlowDetail get(@PathVariable String id, SelectOptions options) throws Exception;

    @PutMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Update Flow")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "data", value = "Flow Json", dataType = "FlowDetail", required = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Flow for Update", allowMultiple = true) })
    void put(@PathVariable String id, @RequestBody FlowDetail data, @RequestParam(required = false) String[] fieldName)
                    throws Exception;

    @GetMapping(path = RESOURCE)
    @ApiOperation("Get Flow List")
    GetListOut<Flow> getList(GetFlowListIn input) throws Exception;

    @PostMapping(path = RESOURCE + "/save")
    @ApiOperation("Save Flow List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "Flow List", dataType = "Flow", required = true, allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Flow for Save", allowMultiple = true) })
    void saveList(@RequestBody List<Flow> list, @RequestParam(required = false) String[] fieldName) throws Exception;

    @DeleteMapping(path = RESOURCE)
    @ApiOperation("Delete Flow List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "Flow List", dataType = "Flow", required = true, allowMultiple = true) })
    void deleteList(@RequestBody List<Flow> list) throws Exception;

    @GetMapping(path = RESOURCE + "/{id}/opers")
    @ApiOperation("Get Flow Oper List")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "Flow ID", required = true) })
    GetListOut<FlowOper> getOperList(@PathVariable String id) throws Exception;

    @PostMapping(path = RESOURCE + "/{id}/opers/save")
    @ApiOperation("Save Flow Oper List")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "Flow ID", required = true),
                    @ApiImplicitParam(name = "list", value = "Flow Oper List", dataType = "FlowOper", allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Oper for Save", allowMultiple = true) })
    void saveOperList(@PathVariable String id, @RequestBody List<FlowOper> list,
                    @RequestParam(required = false) String[] fieldName) throws Exception;

}
