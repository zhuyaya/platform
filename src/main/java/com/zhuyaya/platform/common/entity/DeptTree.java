package com.zhuyaya.platform.common.entity;

import com.zhuyaya.platform.system.entity.Dept;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 部门树形展开
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-02-08 16:14
 */
@Data
public class DeptTree<T> implements Serializable {

    private static final long serialVersionUID = -8058706128125133107L;

    private String id;
    private String icon;
    private String href;
    private String name;
    private Map<String, Object> state;
    private boolean checked = false;
    private Map<String, Object> attributes;
    private List<DeptTree<T>> children;
    private String parentId;
    private boolean hasParent = false;
    private boolean hasChild = false;

    private Dept data;

    public void initChildren() {
        this.children = new ArrayList<>();
    }
}