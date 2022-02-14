package com.zhuyaya.platform.common.utils;

import com.zhuyaya.platform.common.entity.DeptTree;
import com.zhuyaya.platform.common.entity.MenuTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 树形 处理工具类
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-02-09 10:25
 */
public class TreeUtil {
    protected TreeUtil() {
    }
    private static final String ZERO = "0";
    public static <T> MenuTree<T> buildMenuTree(List<MenuTree<T>> nodes) {
        if (nodes == null) {
            return null;
        }
        List<MenuTree<T>> topNodes = new ArrayList<>();

        nodes.forEach(child -> {
            String pid = child.getParentId();
            if (pid == null || ZERO.equals(pid)) {
                topNodes.add(child);
                return;
            }
            for (MenuTree<T> parent : nodes) {
                String id = parent.getId();
                if (id != null && id.equals(pid)) {
                    parent.getChildren().add(child);
                    child.setHasParent(true);
                    parent.setHasChild(true);
                    return;
                }
            }
        });

        MenuTree<T> root = new MenuTree<>();
        root.setId(ZERO);
        root.setParentId("");
        root.setHasParent(false);
        root.setHasChild(true);
        root.setChecked(true);
        root.setChildren(topNodes);
        Map<String, Object> state = new HashMap<>(16);
        root.setState(state);
        return root;
    }

    public static <T> List<DeptTree<T>> buildDeptTree(List<DeptTree<T>> nodes) {
        if (nodes == null) {
            return null;
        }
        List<DeptTree<T>> result = new ArrayList<>();
        nodes.forEach(child -> {
            String pid = child.getParentId();
            if (pid == null || ZERO.equals(pid)) {
                result.add(child);
                return;
            }
            for (DeptTree<T> parent : nodes) {
                String id = parent.getId();
                if (id != null && id.equals(pid)) {
                    if (parent.getChildren() == null) {
                        parent.initChildren();
                    }
                    parent.getChildren().add(child);
                    child.setHasParent(true);
                    parent.setHasChild(true);
                    return;
                }
            }
        });
        return result;
    }

    public static <T> List<MenuTree<T>> buildList(List<MenuTree<T>> nodes, String idParam) {
        if (nodes == null) {
            return new ArrayList<>();
        }
        List<MenuTree<T>> topNodes = new ArrayList<>();
        nodes.forEach(child -> {
            String pid = child.getParentId();
            if (pid == null || idParam.equals(pid)) {
                topNodes.add(child);
                return;
            }
            nodes.forEach(parent -> {
                String id = parent.getId();
                if (id != null && id.equals(pid)) {
                    parent.getChildren().add(child);
                    child.setHasParent(true);
                    parent.setHasChild(true);
                }
            });
        });
        return topNodes;
    }
}