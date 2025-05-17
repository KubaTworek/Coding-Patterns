package pl.jakubtworek.easy.linked_list;

import java.util.ArrayList;
import java.util.List;

public class SinglyLinkedList<T> {

    public static class Node<T> {
        public T value;
        public Node<T> next;

        public Node(T value) {
            this.value = value;
            this.next = null;
        }
    }

    public Node<T> head;

    public SinglyLinkedList() {
        this.head = null;
    }

    public void append(T value) {
        if (head == null) {
            head = new Node<>(value);
            return;
        }
        Node<T> current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new Node<>(value);
    }

    public void reverse() {
        Node<T> prev = null;
        Node<T> current = head;

        while (current != null) {
            Node<T> nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }

        head = prev;
    }

    public List<T> toList() {
        List<T> result = new ArrayList<>();
        Node<T> current = head;
        while (current != null) {
            result.add(current.value);
            current = current.next;
        }
        return result;
    }

    @SafeVarargs
    public static <T> SinglyLinkedList<T> of(T... values) {
        SinglyLinkedList<T> list = new SinglyLinkedList<>();
        for (T value : values) {
            list.append(value);
        }
        return list;
    }
}
