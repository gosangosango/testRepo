package kr.co.miracom.mes.wip.resource.simple.plan.controller;

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
import kr.co.miracom.mes.wip.resource.simple.plan.model.Plan;
import kr.co.miracom.mes.wip.resource.simple.plan.model.PlanDetail;
import kr.co.miracom.mes.wip.resource.simple.plan.service.get_list.GetPlanListIn;

/**
 * Setup Controller: Plan
 * @author myjung.jung
 * @since 2018. 06. 19.
 */
@Api(value = "Creating, Retrieving, Updating and Deleting Plans.")
@RequestMapping(path = MesConst.VER_PATH)
public interface PlanController {
    static final String RESOURCE = "wip/plans";

    @GetMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Get Plan")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "select", value = "Field Names of Plan for Select", allowMultiple = true),
                    @ApiImplicitParam(name = "unselect", value = "Field Names of Plan for Unselect", allowMultiple = true) })
    PlanDetail get(@PathVariable String id, SelectOptions options) throws Exception;

    @PutMapping(path = RESOURCE + "/{id}")
    @ApiOperation("Update Plan")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "ID", required = true),
                    @ApiImplicitParam(name = "data", value = "Plan Json", dataType = "PlanDetail", required = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Plan for Update", allowMultiple = true) })
    void put(@PathVariable String id, @RequestBody PlanDetail data, @RequestParam(required = false) String[] fieldName)
                    throws Exception;

    @GetMapping(path = RESOURCE)
    @ApiOperation("Get Plan List")
    GetListOut<Plan> getList(GetPlanListIn input) throws Exception;

    @PostMapping(path = RESOURCE + "/save")
    @ApiOperation("Save Plan List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "Plan List", dataType = "Plan", required = true, allowMultiple = true),
                    @ApiImplicitParam(name = "fieldName", value = "Field Names of Plan for Save", allowMultiple = true) })
    void saveList(@RequestBody List<Plan> list, @RequestParam(required = false) String[] fieldName) throws Exception;

    @DeleteMapping(path = RESOURCE)
    @ApiOperation("Delete Plan List")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "list", value = "Plan List", dataType = "Plan", required = true, allowMultiple = true) })
    void deleteList(@RequestBody List<Plan> list) throws Exception;

}
