public class Pilha<T> {
    private LinkedList<T> list;

    public Pilha() {
        list = new LinkedList<>();
    }

    public void push(T value) {
        list.addNode(value);
    }

    public T pop() {
        if (list.getHead() == null) {
            return null;
        }
        T value = list.getHead().value;
        list.head = list.getHead().previous;
        return value;
    }

    public boolean isEmpty() {
        return list.getHead() == null;
    }
}
