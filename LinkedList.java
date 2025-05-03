public class LinkedList {
    private  Node head;

    // Node class
    protected static class Node { // changed to protected to allow access in registrationSystem class
        int ID;
        Node next;
        LinkedList list;

        Node(int ID) {
            this.ID = ID;
            this.next = null;
            this.list = new LinkedList();
        }
    }

    // Add a new element to the end of the list
    public void add(Node newNode) {
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }
    
    public void enroll(int ID,Node node) {
        Node current = head;
        while (current.ID != ID) {
            System.out.print(current.ID + " ");
            current = current.next;
        }
        current.list.add(node);
    }
    // Print all elements in the list
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.ID + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Remove the first occurrence of a given value
    public void remove(int ID) {
        if (head == null) return;

        if (head.ID == ID) {
            head = head.next;
            return;
        }

        Node current = head;
        while (current.next != null && current.next.ID != ID) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    // Check if the list contains a specific value
    public boolean contains(int ID) {
        Node current = head;
        while (current != null) {
            if (current.ID == ID) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public Node findNode(int ID) {
        Node current = head;
        while (current != null) {
            if (current.ID == ID) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

     public Node getHead() {
        return head;
    }

    public void setHead(Node newHead) {
        this.head = newHead;
    }
}
