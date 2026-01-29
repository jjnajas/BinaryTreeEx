public class Task<E extends Comparable<E>> {
    public static class Student implements Comparable<Student> {
        private int badge;
        private String name;
        public Student(int badge,String name){
            this.badge=badge;
            this.name=name;
        }

        @Override
        public int compareTo(Student o) {
            return Integer.compare(this.badge, o.badge);
        }

        @Override
        public String toString() {
            return "Student{" +
                    "badge=" + badge +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
    private class Node {
        private E data;
        private Node left;
        private Node right;

        public Node(E data) {
            this.data = data;
        }
    }
    private Node root;

    public void add(E e){
        root = add(e, root);
    }

    public Node add(E e, Node currentNode){
        if (currentNode == null){
            return new Node(e);
        }
        int cmp = e.compareTo(currentNode.data);
        if (cmp < 0){
            currentNode.left = add(e,currentNode.left);
        }else if (cmp > 0){
            currentNode.right = add(e, currentNode.right);
        }
        return currentNode;
    }

    public void inorderTraversal(){
        if (root == null){
            System.out.println( "Tree is empty");
        }
        inOrderTraversal(root);
    }
    private void inOrderTraversal(Node node){
        if (node == null){
            return;
        }
        inOrderTraversal(node.left);
        IO.print(node.data + " ");
        inOrderTraversal(node.right);
    }


    public E find(E target){
        if(root == null){
            return null;
        }
        return find(target,root);
    }
    private E find(E target,Node currentNode){
        int cpm = target.compareTo(currentNode.data);
        if(cpm==0){
            return currentNode.data;
        } else if (cpm<0) {
            return find(target,currentNode.left);
        }else {
            return find(target,currentNode.right);
        }
    }
    private E deleteReturn;
    public E delete(E target){
        deleteReturn = null;
        root = delete(root, target);
        return deleteReturn;
    }

    private Node delete(Node currentNode, E target){
        if (currentNode == null){
            return null;
        }
        int cmp = target.compareTo(currentNode.data);
        if (cmp < 0){
            currentNode.left = delete(currentNode.left, target);
        }else if (cmp > 0){
            currentNode.right = delete(currentNode.right, target);
        }else {
            deleteReturn = currentNode.data;
            if (currentNode.left == null){
                return currentNode.right;
            }
            if (currentNode.right == null){
                return currentNode.left;
            }
            Node successor = getSuccessor(currentNode.right);
            currentNode.data = successor.data;
            currentNode.right = delete(currentNode.right, successor.data);
        }
        return currentNode;
    }

    private Node getSuccessor(Node currentNode) {
        if (currentNode.left == null){
            return currentNode;
        }
        return getSuccessor(currentNode.left);
    }

}

class Main1 {
    public static void main(String[] args) {
         Task<Student> tree = new Task<>();
        tree.add(new Student(50,"Alice"));
        tree.add(new Student(30,"Bob"));
        tree.add(new Student(70,"Charlie"));
        tree.add(new Student(20,"Diana"));
        tree.add(new Student(40,"Evan"));
        tree.add(new Student(60,"Fiona"));
        tree.add(new Student(80,"George"));
        tree.inorderTraversal();
        tree.delete(new Student(20,"Diana"));
        System.out.println(tree);
        tree.delete(new Student(30,"Bob"));
        System.out.println(tree);
        tree.delete(new Student(50,"Alice"));
        System.out.println(tree);
        System.out.println();
    }
}