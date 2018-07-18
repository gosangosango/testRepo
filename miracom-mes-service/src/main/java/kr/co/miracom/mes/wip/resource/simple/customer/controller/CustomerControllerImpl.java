package kr.co.miracom.mes.wip.resource.simple.customer.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.mes.wip.resource.simple.customer.model.Customer;
import kr.co.miracom.mes.wip.resource.simple.customer.model.CustomerDetail;
import kr.co.miracom.mes.wip.resource.simple.customer.service.CustomerService;
import kr.co.miracom.mes.wip.resource.simple.customer.service.get_list.GetCustomerList;
import kr.co.miracom.mes.wip.resource.simple.customer.service.get_list.GetCustomerListIn;

/**
 * Setup Controller: Customer
 * @author myjung.jung
 * @since 2018. 06. 14.
 */
@CrossOrigin
@RestController
public class CustomerControllerImpl implements CustomerController {

    @Override
    public CustomerDetail get(@PathVariable String id, SelectOptions options) throws Exception {
        return BeanUtils.get(CustomerService.class).get(id, options);
    }

    @Override
    public void put(@PathVariable String id, @RequestBody CustomerDetail data,
                    @RequestParam(required = false) String[] fieldName) throws Exception {
        BeanUtils.get(CustomerService.class).put(id, data, fieldName);
    }

    @Override
    public GetListOut<Customer> getList(GetCustomerListIn input) throws Exception {
        return BeanUtils.get(GetCustomerList.class).getList(input);
    }

    @Override
    public void saveList(@RequestBody List<Customer> list, @RequestParam(required = false) String[] fieldName)
                    throws Exception {
        BeanUtils.get(CustomerService.class).saveList(list, fieldName);
    }

    @Override
    public void deleteList(@RequestBody List<Customer> list) throws Exception {
        BeanUtils.get(CustomerService.class).deleteList(list);
    }

}
