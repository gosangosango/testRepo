package kr.co.miracom.dbist.dml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import kr.co.miracom.framework.util.ValueUtils;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@SuppressWarnings("serial")
public class SelectOptions implements Serializable {
    private List<String> select;
    private List<String> unselect;

    public String[] getSelect() {
        if (ValueUtils.isEmpty(select))
            return null;
        return select.toArray(new String[select.size()]);
    }

    public void setSelect(List<String> select) {
        this.select = select;
    }

    public SelectOptions addSelect(String... select) {
        if (ValueUtils.isEmpty(select)) {
            return this;
        }
        if (this.select == null) {
            this.select = new ArrayList<String>(select.length);
        }
        for (String f : select) {
            this.select.add(f);
        }
        return this;
    }

    public String[] getUnselect() {
        if (ValueUtils.isEmpty(unselect))
            return null;
        return unselect.toArray(new String[unselect.size()]);
    }

    public void setUnselect(List<String> unselect) {
        this.unselect = unselect;
    }

    public SelectOptions addUnselect(String... unselect) {
        if (ValueUtils.isEmpty(unselect)) {
            return this;
        }
        if (this.unselect == null) {
            this.unselect = new ArrayList<String>(unselect.length);
        }
        for (String f : unselect) {
            this.unselect.add(f);
        }
        return this;
    }
}
