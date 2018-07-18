package kr.co.miracom.mes.wip.resource.simple.customer.service;

import java.util.List;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.service.RestService;
import kr.co.miracom.mes.wip.model.MWipCusDef;
import kr.co.miracom.mes.wip.resource.simple.customer.model.Customer;
import kr.co.miracom.mes.wip.resource.simple.customer.model.CustomerDetail;

/**
 * Setup Service: Customer
 * @author myjung.jung
 * @since 2018. 06. 14.
 */
public class CustomerService {
    private RestService<MWipCusDef, Customer, CustomerDetail> restService = new RestService<>(MWipCusDef.class,
                    Customer.class, CustomerDetail.class);

    public CustomerDetail get(String id, SelectOptions options) throws Exception {
        return restService.get(id, options);
    }

    public void post(String id, CustomerDetail data, String[] fieldName) throws Exception {
        restService.post(id, data, fieldName);
    }

    public void put(String id, CustomerDetail data, String[] fieldName) throws Exception {
        restService.put(id, data, fieldName);
    }

    public void delete(String id, CustomerDetail data) throws Exception {
        restService.delete(id, data);
    }

    public void postList(List<Customer> list, String[] fieldName) throws Exception {
        restService.postList(list, fieldName);
    }

    public void putList(List<Customer> list, String[] fieldName) throws Exception {
        restService.putList(list, fieldName);
    }

    public void saveList(List<Customer> list, String[] fieldName) throws Exception {
        restService.saveList(list, fieldName);
    }

    public void deleteList(List<Customer> list) throws Exception {
        restService.deleteList(list);
    }

}
