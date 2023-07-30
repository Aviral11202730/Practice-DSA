class LinkedList{
    //Creating a Node class
    public static class Node{
        int data;
        Node next;
        public Node(int data){
            this.data=data;
            this.next=null;
        }
    }
    public static Node head;
    public static Node tail;
    public static int size;
    //Adding Node at First of LL
    public void addFirst(int data){                        //Time Complexity:-O(1)
        Node newNode=new Node(data);
        size++;
        if(head==null){
            head=tail=newNode;
            return;
        }
        newNode.next=head;
        head=newNode;
    }
    //Adding Node at last of LL
    public void addLast(int data){                          //Time Complexity:-O(1)
       Node newNode=new Node(data);
        size++;
        if(head==null){
            head=tail=newNode;
            return;
        }
        tail.next=newNode;
        tail=newNode;
    }
    // Adding node at the middle of the ll
    public void add(int idx,int data){                                 //Time Complexity:-O(n)
        if(idx==0){
            addFirst(data);
            return;
        }
        Node newNode=new Node(data);
        size++;
        Node temp=head;
        int i=0;
        while(i<idx-1){
            temp=temp.next;
            i++;
        }
        newNode.next=temp.next;
        temp.next=newNode;
    }
    //Removing First node of LL
    public int removeFirst(){
        if(size==0){
            System.out.println("List is empty");
            return Integer.MIN_VALUE;
        }else if(size==1){
            int val=head.data;
            head=tail=null;
            size=0;
            return val;
        }
        int val=head.data;
        head=head.next;
        size--;
        return val;
    }
    //Removing Last Node of LL
    public int removeLast(){
        if(size==0){
            System.out.println("List is Empty");
            return Integer.MIN_VALUE;
        }else if(size==1){
            int val=head.data;
            head=tail=null;
            return val;
        }
    
    Node temp=head;
    for(int i=0;i<size-2;i++){
        temp=temp.next;
    }
    int val=tail.data;
    temp.next=null;
    tail=temp;
    size--;
    return val;
    }
    //For printing a linked list
    public void print(){
        if(head==null){
            System.out.println("List is Empty");
            return;
        }
        Node temp=head;
        while(temp!=null){
            System.out.print(temp.data+"->");
            temp=temp.next;
        }
        System.out.println("Null");
    }
    // Reversing a LinkedList
    public void reverseList(){
        Node prev=null;
        Node curr=head;
        Node next;
        while(curr!=null){
            next=curr.next;
            curr.next=prev; 
            prev=curr;
            curr=next;
        }
        head=prev;
    }
    // Searching a Node in a Linked List iteratively
    public int itrSearch(int key){
      Node temp=head;
      int i=0;
      while(temp!=null){
        if(temp.data==key){
            return i+1;
        }
        temp=temp.next;
        i++;
      }
      return -1;

    }
    // Deleting nth Node from  End of the Linked List
    public void deleteNthEnd(int n){
        int sz=0;
        Node temp=head;
        while(temp!=null){
            temp=temp.next;                        //This is we calculate the size;
            sz++;
        }
       Node prev=head;
       for(int i=1;i<sz-n;i++){
        prev=prev.next;
       }
       prev.next=prev.next.next;
    //    return;
    }
    public Node findMid(Node head){
        Node slow=head;
        Node fast=head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }
    public boolean isPalidrome(){
     if(head==null || head.next==null){
        return true;
     }
     Node mid=findMid(head);/* dout*/
      Node prev=null;
      Node curr=mid;
      Node next=curr.next;
      while(curr!=null){
        next=curr.next;
        curr.next=prev;
        prev=curr;
        curr=next;
      }
      Node rigth=prev;
      Node left=head;
      while(rigth!=null){
        if(left.data!=rigth.data){
            return false;
        }
        left=left.next;
        rigth=rigth.next;
      }
      return true;
}
//Dectecting a cycle in a linkedList
public static boolean isCycle(){
    Node slow=head;
    Node fast=head;
    while(fast!=null && fast.next!=null){
        slow=slow.next;
        fast=fast.next.next;
        if(fast==slow){
            return true;
        }
    }
    return false;

}
//Removing a cycle in a linked list
public static void removeCycle(){
    Node slow=head;
    Node fast=head;
    boolean cycle=false;
    while(fast!=null && fast.next!=null){
        slow=slow.next;
        fast=fast.next.next;
        if(fast==slow){
          cycle=true;
          break;
        }
    }
    if(cycle==false){
        return;
    }
    Node prev=null;
    slow=head;
    while(slow!=fast){
        prev=fast;
        slow=slow.next;
        head=head.next;
    }
    prev.next=null;
}
private Node merge(Node head1,Node head2){
    Node mergedLL=new Node(-1);
    Node temp=mergedLL;
    while(head1!=null && head2!=null){
        if(head1.data<=head2.data){
            temp.next=head1;
            head1=head1.next;
            temp=temp.next;
        }
        else{
            temp.next=head2;
            head2=head2.next;
            temp=temp.next;
        }
    }
    while(head1!=null){
        temp.next=head1;
        head1=head1.next;
        temp=temp.next;
   }
   while(head2!=null){
    temp.next=head2;
    head2=head2.next;
    temp=temp.next;
   }
   return mergedLL.next;
}
private Node getMid(Node head){
    Node slow=head;
    Node fast=head.next;

    while(fast!=null && fast.next!=null){
        slow=slow.next;
        fast=fast.next.next;
    }
    return slow;
}
public Node mergeSort(Node head){
    if(head==null || head.next==null){
        return head;
    }
    //find Mid
    Node mid=getMid(head);
    Node rigthHead=mid.next;
    mid.next=null;
    Node newLeft=mergeSort(head);
    Node newRight=mergeSort(rigthHead);
    return merge(newLeft,newRight);

}
// Converting a LinkedList into a zig-zag fashion
public void zigzag(){
    Node slow=head;
    Node fast=head.next;
    while(fast!=null && fast.next!=null){
        slow=slow.next;
        fast=fast.next.next;
    }
    Node mid=slow;
    Node curr=mid.next;
    mid.next=null;
    Node prev=null;
    // Node curr=mid.next;
    // mid.next=null;
    Node next;
    while(curr!=null){
        next=curr.next;
        curr.next=prev;
        prev=curr;
        curr=next;
    }
    Node left=head;
    Node rigth=prev;
    Node nextL,nextR;
    while(left!=null && rigth!=null){
        nextL=left.next;
        left.next=rigth;
      
        nextR=rigth.next;
        rigth.next=nextL;
        // rigth=nextR;
        left=nextL;
        rigth=nextR;
     }
}
public int deleteNth(int n){
    int idx=0;
    Node temp=head;
    int val=head.data;
    while(idx!=n-1){
        temp=temp.next;
        idx++;
    }
    // int val=head.data;
    temp=temp.next.next;
    return val;
}
    public static void main(String args[]){
    LinkedList ll=new LinkedList();
    ll.addFirst(1);
    ll.addFirst(2);
    ll.addLast(3);
    // ll.addLast(4);
    // ll.addLast(5);
    // ll.add(2,100);
    // ll.print();
    // ll.removeLast();
    ll.print();
    // ll.print();
    // ll.print();
    // System.out.print(ll.size);
    // ll.head=ll.mergeSort(ll.head);
    // ll.zigzag();
    // ll.deleteNth(2);
    // ll.print();
    

    }
}