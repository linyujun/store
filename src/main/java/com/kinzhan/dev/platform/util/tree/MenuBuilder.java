package com.kinzhan.dev.platform.util.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ww
 * @date 2021/6/29
 **/
public class MenuBuilder {

    List<Node> nodes = new ArrayList<>();

    public List<Node> buildTree(List<Node> nodes) {

        MenuBuilder treeBuilder = new MenuBuilder(nodes);

        return treeBuilder.buildTree();
    }

    public MenuBuilder() {
    }

    public MenuBuilder(List<Node> nodes) {
        super();
        this.nodes = nodes;
    }

    // 构建树形结构
    public List<Node> buildTree() {
        List<Node> treeNodes = new ArrayList<>();
        List<Node> rootNodes = getRootNodes();
        for (Node rootNode : rootNodes) {
            buildChildNodes(rootNode);
            treeNodes.add(rootNode);
        }
        return treeNodes;
    }

    // 递归子节点
    public void buildChildNodes(Node node) {
        List<Node> children = getChildNodes(node);
        if (!children.isEmpty()) {
            for (Node child : children) {
                buildChildNodes(child);
            }
            node.setChildren(children);
        }
    }

    // 获取父节点下所有的子节点
    public List<Node> getChildNodes(Node pnode) {
        List<Node> childNodes = new ArrayList<>();
        for (Node n : nodes) {
            if (pnode.getId().equals(n.getParentId())) {
                childNodes.add(n);
            }
        }
        return childNodes;
    }

    // 判断是否为根节点
    public boolean rootNode(Node node) {
        boolean isRootNode = true;
        for (Node n : nodes) {
            if (node.getParentId().equals(n.getId())) {
                isRootNode = false;
                break;
            }
        }
        return isRootNode;
    }

    // 获取集合中所有的根节点
    public List<Node> getRootNodes() {
        List<Node> rootNodes = new ArrayList<>();
        for (Node n : nodes) {
            if (rootNode(n)) {
                rootNodes.add(n);
            }
        }
        return rootNodes;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Node {
        private Integer id;
        private Integer parentId;
        private String name;
        private String title;
        private String path;
        private String target;
        private Integer type;
        private Integer sort;
        private Boolean enabled;
        private Boolean hidden;
        private String component;
        private Meta meta;
        private List<Node> children;
        public List<Node> getChildren() {
            return children;
        }

        public void setChildren(List<Node> children) {
            this.children = children;
        }
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Meta {
        private Boolean hidden = false;
        private Boolean levelHidden = false;
        private String title;
        private String target;
        private Guard guard;
        private String icon;
        private Integer noKeepAlive;
        private Boolean dynamicNewTab = false;
        private Boolean tabHidden = false;
        private Boolean noColumn;
        private String permission;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Guard {
        List<String> role;
        String mode = "allOf";
    }
}
