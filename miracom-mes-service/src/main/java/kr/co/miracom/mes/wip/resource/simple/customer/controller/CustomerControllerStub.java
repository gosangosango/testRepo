package kr.co.miracom.mes.wip.resource.simple.customer.controller;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.GetListOut;
import kr.co.miracom.mes.wip.resource.simple.customer.model.Customer;
import kr.co.miracom.mes.wip.resource.simple.customer.model.CustomerDetail;
import kr.co.miracom.mes.wip.resource.simple.customer.service.get_list.GetCustomerListIn;

/**
 * Setup Controller: Customer
 * @author myjung.jung
 * @since 2018. 06. 14.
 */
public class CustomerControllerStub implements CustomerController {

    @Override
    public CustomerDetail get(String id, SelectOptions options) throws Exception {
        return null;
    }

    @Override
    public void put(String id, CustomerDetail data, String[] fieldName) throws Exception {

    }

    @Override
    public GetListOut<Customer> getList(GetCustomerListIn input) throws Exception {
        return null;
    }

    @Override
    public void saveList(List<Customer> list, String[] fieldName) throws Exception {

    }

    @Override
    public void deleteList(List<Customer> list) throws Exception {

    }

}
