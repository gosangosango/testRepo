package kr.co.miracom.mes.wip.resource.simple.component.service.get_tree;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import kr.co.miracom.dbist.dml.SelectOptions;
import kr.co.miracom.framework.util.BeanUtils;
import kr.co.miracom.framework.util.DbUtils;
import kr.co.miracom.framework.util.ValueUtils;
import kr.co.miracom.mes.wip.model.MWipMatDef;
import kr.co.miracom.mes.wip.resource.simple.component.model.CompTreeNode;
import kr.co.miracom.mes.wip.resource.simple.component.model.Component;
import kr.co.miracom.mes.wip.resource.simple.component.service.get_list.GetComponentList;
import kr.co.miracom.mes.wip.resource.simple.component.service.get_list.GetComponentListIn;

public class GetComponentTree {
    public GetComponentTreeOut getTree(GetComponentTreeIn input) throws Exception {
        ValueUtils.checkNotEmpty(input, "matCode");

        MWipMatDef mat = DbUtils.select(MWipMatDef.class, input,
                        new SelectOptions().addSelect("matCode", "matDesc", "matShortDesc"), true);

        GetComponentTreeOut output = new GetComponentTreeOut();

        output.setId(mat.getMatCode());
        output.setLabel(mat.getMatShortDesc());
        output.setTooltip(mat.getMatDesc());
        output.setMatCode(mat.getMatCode());
        Set<String> set = new HashSet<>();
        set.add(mat.getMatCode());
        addChildren(output, set, input.isApplyOnlyFlag());

        return output;
    }

    private void addChildren(CompTreeNode parent, Set<String> set, boolean applyOnlyFlag) throws Exception {
        List<Component> list;
        {
            GetComponentListIn input = new GetComponentListIn();
            input.setMatCode(parent.getMatCode());
            input.setApplyOnlyFlag(applyOnlyFlag);
            list = BeanUtils.get(GetComponentList.class).getList(input).getList();
        }
        for (Component item : list) {
            parent.setLeafFlag(false);

            CompTreeNode child = new CompTreeNode();
            child.setId(item.getId());
            child.setLabel(item.getChildMatShortDesc());
            child.setTooltip(item.getChildMatDesc());
            child.setMatCode(item.getChildMatCode());
            parent.add(child);

            if (set.contains(item.getChildMatCode())) {
                continue;
            }
            set.add(item.getChildMatCode());
            addChildren(child, set, applyOnlyFlag);
            set.remove(item.getChildMatCode());
        }
    }
}
