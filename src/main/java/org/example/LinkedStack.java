package org.example;

import lombok.AllArgsConstructor;

public class LinkedStack<V> implements MyStack<V> {

    Node<V> root;

    @Override
    public void push(V value) {
        root = new Node<>(value, root);
    }

    @Override
    public V pop() {
        Node<V> last = root;
        if(last == null){
            return null;
        }
        root = last.next;
        return last.value;
    }

    @AllArgsConstructor
    static class Node<V> {
        V value;
        Node<V> next;

    }
}
