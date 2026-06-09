public class NodeDriver {
    

    public static IntNode insertNode(IntNode head, int item) {
        IntNode newNode = new IntNode(item);
        if (head == null) {
            return newNode;
        }
        IntNode current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(newNode);
        return head;
    }

    public static void printList(IntNode head) {
        IntNode current = head;
        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }

    public static void main(String[] args) {
        IntNode head = null;
        head = insertNode(head, 15);
        head = insertNode(head, 42);
        head = insertNode(head, 37);
        head = insertNode(head, 25);

        System.out.println("Linked List contents:");
        printList(head);
    }
}