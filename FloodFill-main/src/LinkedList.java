public class LinkedList<E> {
    Node<E> head;

    public LinkedList() {
        head = null;
    }

    public Node<E> getHead() {
        return head;
    }

    public Node<E> getFirst() {
        if (head == null) return null;
        Node<E> current = head;
        while (current.previous != null) {
            current = current.previous;
        }
        return current;
    }

    public void addNode(E value) {
        Node<E> n = new Node<>(value);
        if (head == null) {
            head = n;
        } else {
            n.previous = head;
            head.next = n;
            head = n;
        }
    }

    public void removeFirst() {
        Node<E> first = getFirst();
        if (first == null) return;
        if (first.next != null) {
            first.next.previous = null;
        } else {
            head = null;
        }
    }
}
