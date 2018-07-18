package kr.co.miracom.framework.util;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class TreeNode {
    private String id;
    private String label;
    private String tooltip;
    private boolean leafFlag = true;
    private List<TreeNode> list;

    public String getType() {
        return getClass().getSimpleName();
    }

    public void add(TreeNode item) {
        if (this.list == null) {
            this.list = new ArrayList<>();
        }
        this.list.add(item);
    }
}
