package com.zhuyaya.platform.common.entity;

import com.zhuyaya.platform.system.entity.Menu;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 菜单树
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-02-08 16:40
 */
@Data
public class MenuTree<T> implements Serializable {

    private static final long serialVersionUID = 5255381462459088027L;

    private String id;
    private String icon;
    private String href;
    private String title;
    private Map<String, Object> state;
    private boolean checked = false;
    private Map<String, Object> attributes;
    private List<MenuTree<T>> children = new ArrayList<>();
    private String parentId;
    private boolean hasParent = false;
    private boolean hasChild = false;

    private Menu data;
}