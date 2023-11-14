package com.fredson.datastructures.tree;

import com.fredson.datastructures.iterator.Iterator;
import com.fredson.datastructures.list.ArrayList;
import com.fredson.datastructures.list.List;
import com.fredson.models.DoublyNode;
import com.fredson.models.Node;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedBinaryTree<T> implements Tree<T> {

    private DoublyNode<T> root;

    //TODO -> Alterar a implementação do HashMap para estrutura de dados própria
    private final Map<T, DoublyNode<T>> nodes = new LinkedHashMap<>();

    private int size;

    @Override
    public T replace(Node<T> node, T element) {
        return null;
    }

    @Override
    public T root() {
        if (isEmpty()) {
            throw new Error("Tree is empty");
        }
        return root.getElement();
    }

    @Override
    public Node<T> parent(Node<T> node) {
        return null;
    }

    @Override
    public List<T> nodes() {
        List<T> nodesList = new ArrayList<>();
        //TODO -> Alterar a implementação para transformar o hashMap em lista
        for (Map.Entry<T, DoublyNode<T>> tDoublyNodeEntry : nodes.entrySet()) {
            nodesList.push(tDoublyNodeEntry.getKey());
        }
        return nodesList;
    }

    @Override
    public List<T> children(T node) {
        List<T> nodesList = new ArrayList<>();
        DoublyNode<T> doublyNode = nodes.get(node);
        if (doublyNode != null && doublyNode.getPrevNode() != null) {
            nodesList.push(doublyNode.getPrevNode().getElement());
        }
        if (doublyNode != null && doublyNode.getNextNode() != null) {
            nodesList.push(doublyNode.getNextNode().getElement());
        }
        return nodesList;
    }

    @Override
    public boolean isInternal(Node<T> node) {
        return false;
    }

    @Override
    public boolean isExternal(Node<T> node) {
        return false;
    }

    @Override
    public boolean isRoot(Node<T> node) {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public void insert(T element) {
        if (isEmpty()) {
            root = new DoublyNode<>(element);
            nodes.put(root.getElement(), root);
            size ++;
        } else {
            insert(root.getElement(), element);
        }
    }

    @Override
    public void insert(T nodeElement, T element) {
        if (isEmpty()) {
            insert(element);
        }
        DoublyNode<T> node = nodes.get(nodeElement);
        if (node == null) {
            throw new Error("Node " + nodeElement + " not found in tree");
        }
        if (node.getPrevNode() == null) {
            DoublyNode<T> newNode = new DoublyNode<>(element);
            node.setPrevNode(newNode);
            nodes.put(newNode.getElement(), newNode);
            size ++;
        } else if (node.getNextNode() == null) {
            DoublyNode<T> newNode = new DoublyNode<>(element);
            node.setNextNode(newNode);
            nodes.put(newNode.getElement(), newNode);
            size++;
        } else if (node.getPrevNode() != null && node.getPrevNode().getNextNode() == null) {
            insert(node.getPrevNode().getElement(), element);
        } else if (node.getPrevNode() != null && node.getPrevNode().getNextNode() != null && node.getNextNode() != null) {
            insert(node.getNextNode().getElement(), element);
        } else {
            insert(node.getElement(), element);
        }
    }

    @Override
    public T min() {
        if (isEmpty()) {
            return null;
        }
        return minNode(root);
    }

    private T minNode(DoublyNode<T> node) {
        DoublyNode<T> currentNode = node;
        while (currentNode != null && currentNode.getPrevNode() != null) {
            currentNode = (DoublyNode<T>) currentNode.getPrevNode();
        }
        return currentNode.getElement();
    }

    @Override
    public T max() {
        if (isEmpty()) {
            return null;
        }
        return maxNode(root);
    }

    @Override
    public boolean search(T element) {
        if (isEmpty()) {
            return false;
        }
        return nodes.containsKey(element);
    }

    private T maxNode(DoublyNode<T> node) {
        DoublyNode<T> currentNode = node;
        while (currentNode != null && currentNode.getNextNode() != null) {
            currentNode = (DoublyNode<T>) currentNode.getNextNode();
        }
        return currentNode.getElement();
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        String treeString = root.getElement() + " -> (";
        treeString = toString(root.getElement(), treeString);
        return treeString;
    }

    private String toString(T nodeElement, String treeString) {
        List<T> children = this.children(nodeElement);
        while (children.iterator().hasNext()) {
            T next = children.iterator().next();
            treeString += next;
            if (this.children(next).iterator().hasNext()) {
                treeString += " -> (";
            }
            treeString = toString(next, treeString);
            if (children.iterator().hasNext()) {
                treeString += ", ";
            } else {
                treeString += ")";
            }
        }
        return treeString;
    }
}
