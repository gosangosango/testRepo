package kr.co.miracom.mes.wip.resource.simple.flow.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Detail V O: Flow
 * @author myjung.jung
 * @since 2018. 06. 05.
 */
@Getter
@Setter
public class FlowDetail extends Flow {
    private String firstOperCode;
    private String firstOperDesc;
    private String lastOperCode;
    private String lastOperDesc;
}
