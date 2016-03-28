/**
 * Created by silin qin on 22-Mar-16.
 */
import java.io.*;
import java.util.Arrays;

public class MyDlist extends DList{
    public MyDlist(){
        super();
    }

    public MyDlist(String f){
        size = 0;
        header = new DNode(null, null, null);
        trailer = new DNode(null, header, null);
        header.setNext(trailer);

        if (f.equals("stdin")){
            String inputline = null;
            try {
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                while((inputline = stdIn.readLine()).length() != 0){
                    String new_node = inputline;
                    DNode tem = new DNode(new_node,null,null);
                    addBefore(trailer,tem);
                }
            }
            catch(IOException e){System.out.println("Error!");}
        }else {
            String inputline = null;
            try {
                BufferedReader txtFile = new BufferedReader(new FileReader(f));
                while((inputline = txtFile.readLine()) != null){
                    String[] news = inputline.split(" ");
                    for(int i = 0; i < news.length; i++){
                        if (news[i].length() > 0){
                            DNode tempnode = new DNode(news[i],null,null);
                            addBefore(trailer, tempnode);
                        }
                    }
                }
            }
            catch(IOException e){System.out.println("Error!");}
        }
    }

    public static MyDlist cloneList(MyDlist u){
        MyDlist v = new MyDlist();
        if (!u.isEmpty()){
            DNode node = u.getFirst();
            DNode node_copy = new DNode(node.getElement(),null,null);
            v.addFirst(node_copy);
            node = node.getNext();
            if (u.size() > 1) {
                while (node.getElement() != null) {
                    DNode temp = new DNode(node.getElement(), null, null);
                    v.addAfter(node_copy, temp);
                    node_copy = node_copy.getNext();
                    node = node.getNext();
                }
                DNode temp = new DNode(node.getElement(), null, null);
                v.addAfter(node_copy, temp);
            }
        }
        return v;
    }

    public static MyDlist union(MyDlist u, MyDlist v){
        MyDlist w = cloneList(u);
        String[] list_u = new String[u.size()];
        DNode temp = u.header;
        int i = 0;
        while (temp.getNext().getElement() != null) {
            temp = temp.getNext();
            list_u[i] = temp.getElement();
            i++;
        }

        DNode temp_new = v.header;
        while (temp_new.getNext().getElement() != null){
            temp_new = temp_new.getNext();
            if (!Arrays.asList(list_u).contains(temp_new.getElement())){
                DNode tempnode = new DNode(temp_new.getElement(),null,null);
                w.addLast(tempnode);
            }
        }

        return w;
    }

    public static MyDlist intersection(MyDlist u, MyDlist v){
        String []list_u = new String[u.size()];
        String []list_v = new String[v.size()];
        MyDlist w = new MyDlist();
        DNode temp_u = u.header;
        int i = 0;
        while (temp_u.getNext().getElement() != null) {
            temp_u = temp_u.getNext();
            list_u[i] = temp_u.getElement();
            i++;
        }
        DNode temp_v = v.header;
        int j = 0;
        while (temp_v.getNext().getElement() != null) {
            temp_v = temp_v.getNext();
            list_v[j] = temp_v.getElement();
            j++;
        }
        for (int x = 0;x < list_v.length;x++){
            if (Arrays.asList(list_u).contains(list_v[x])){
                DNode temp = new DNode(list_v[x],null,null);
                w.addLast(temp);
            }
        }
        return w;
    }

    public void printList(){
        DNode current = header;
        while (current != null){
            if (current.getElement() != null) {
                System.out.println(current.getElement());
            }
            current = current.next;
        }
    }

    public static void main(String[] args) throws Exception{

        System.out.println("please type some strings, one string each line and an empty line for the end of input:");
        /** Create the first doubly linked list
         by reading all the strings from the standard input. */
        MyDlist firstList = new MyDlist("stdin");

        /** Print all elememts in firstList */
        firstList.printList();

        /** Create the second doubly linked list
         by reading all the strings from the file myfile that contains some strings. */

        /** Replace the argument by the full path name of the text file */
        MyDlist secondList=new MyDlist("C:/Users/slinq/Desktop/abc.txt");

        /** Print all elememts in secondList */
        secondList.printList();

        /** Clone firstList */
        MyDlist thirdList = cloneList(firstList);

        /** Print all elements in thirdList. */
        thirdList.printList();

        /** Clone secondList */
        MyDlist fourthList = cloneList(secondList);

        /** Print all elements in fourthList. */
        fourthList.printList();

        /** Compute the union of firstList and secondList */
        MyDlist fifthList = union(firstList, secondList);

        /** Print all elements in thirdList. */
        fifthList.printList();

        /** Compute the intersection of thirdList and fourthList */
        MyDlist sixthList = intersection(thirdList, fourthList);

        /** Print all elements in fourthList. */
        sixthList.printList();
    }
}

