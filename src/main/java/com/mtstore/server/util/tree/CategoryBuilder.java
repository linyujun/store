package com.mtstore.server.util.tree;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ww
 * @date 2021/6/29
 **/
public class CategoryBuilder {

    List<Node> nodes = new ArrayList<>();

    public List<Node> buildTree(List<Node> nodes) {
        CategoryBuilder treeBuilder = new CategoryBuilder(nodes);
        return treeBuilder.buildTree();
    }

    public CategoryBuilder() {
    }

    public CategoryBuilder(List<Node> nodes) {
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
        private String title;
        private String label;
        private String imgUrl;
        private String icon;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime createTime;
        private Integer sort;
        private Boolean enabled;
        private List<Node> children;
    }
}
