package com.ako.example.jdk;

import com.alibaba.fastjson.JSONObject;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by yanghuanqing@wdai.com on 11/08/2017.
 */
public class NodeTreeToString {
    //评测题目: 给定一集合Set<Node> nodes,
    //其中Node类中id和parentId用于表示其与其他Node对象的父子关系
    //parentId为0的是root节点，
    //要求，提供一个方法，将上述集合作为入参，返回值为json字符串，格式为树状
    //{id:1,parentId:0,code:"node1",children:[{id:2,parentId:1,code:"node2",children:[...]},{...}]}
    //没有任何限制，可以使用开源框架实现
    /**
     * 说明：该程序前置条件：必须只有一个各节点，且根节点的prerentId为0，且集合中不能出现游离节点，否则死循环
     *
     * 思路：构建一个树，且树的节点为NodeExt继承Node，利用递归进行构建。最后调用fastjson进行json转换
     *   <dependency>
         <groupId>com.alibaba</groupId>
         <artifactId>fastjson</artifactId>
         <version>1.2.36</version>
         </dependency>
     */
    static class Node {
        private int id;
        private int parentId;
        private String code;

        public Node(int id, int parentId, String code) {
            this.id = id;
            this.parentId = parentId;
            this.code = code;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
    public static void main(String[] args) {
//        NodeExt nodeExt =    new NodeExt(1, 0, "12");
//        nodeExt.getChildren().add(new NodeExt(2, 1, "13"));

        HashSet<Node> nodeSet = new HashSet<>();
        nodeSet.add(new Node(1, 0, "12"));
        nodeSet.add(new Node(2, 1, "12"));
        nodeSet.add(new Node(3, 1, "13"));
//
        nodeSet.add(new Node(6, 3, "13"));
        nodeSet.add(new Node(4, 2, "13"));
        nodeSet.add(new Node(7, 4, "13"));
        nodeSet.add(new Node(8, 7, "13"));
         String result =  nodesConvertJson(nodeSet);
        System.out.println(result);
    }
    static class NodeExt extends Node{

        public NodeExt(Node node) {
            super(node.getId(),node.getParentId(),node.getCode());
        }

        private LinkedList<NodeExt> children = new LinkedList<>();

        public LinkedList<NodeExt> getChildren() {
            return children;
        }

        public void setChildren(LinkedList<NodeExt> children) {
            this.children = children;
        }
        //{id:1,parentId:0,code:"node1",children:[{id:2,parentId:1,code:"node2",children:[...]},{...}]}
    }
    public static String nodesConvertJson(Set<Node> nodes) {
        Node[] arrNodes = new Node[nodes.size()];
        int[] flags = new int[nodes.size()];
        arrNodes =  nodes.toArray(arrNodes);
        Node root = null;
//        //遍历所有节点的子节点
        for(int i=0;i<arrNodes.length;i++){
            if(arrNodes[i].getParentId() == 0){
                root = arrNodes[i];
                flags[i]=1;
            }
        }
         int restNodesCount = arrNodes.length - 1;
        //构建树
        NodeExt nodeExtRoot = new NodeExt(root);

        for(;!isEmpty(flags);){
            for(int i=0;i<arrNodes.length;i++){
                if(flags[i] == 0){
                   NodeExt nodeExt = new  NodeExt(arrNodes[i]);
                    addNodeExt(nodeExtRoot,nodeExt,arrNodes,i,flags);
                }

            }
        }
        return JSONObject.toJSONString(nodeExtRoot);
    }
    static boolean isEmpty(int [] flags){
        for (int i:flags){
            if(i != 1){
                return  false;
            }
        }
        return true;
    }
    public static void addNodeExt(NodeExt root,NodeExt addExt,Node[] nodes,int flagIndex,int[] flag){
        if(root.getId() == addExt.getParentId()){
            root.getChildren().add(addExt);
            flag[flagIndex] = 1;
            return;
        }else{
            //不相等
            //查看是否有child
            if(root.getChildren().size() == 0){
                return ;
            }else {
                for (NodeExt nodeExt1:root.getChildren()){
                   addNodeExt(nodeExt1,addExt,nodes,flagIndex,flag);
                }
            }

        }
    }


}
