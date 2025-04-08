public class Fila<T> {
    private LinkedList<T> list;

    public Fila() {
        list = new LinkedList<>();
    }

    public void enqueue(T value) {
        list.addNode(value);
    }

    public T dequeue() {
        Node<T> first = list.getFirst();
        if (first == null) {
            return null;
        }
        T value = first.value;
        list.removeFirst();
        return value;
    }

    public boolean isEmpty() {
        return list.getFirst() == null;
    }
}
