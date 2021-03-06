import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class MyDlist extends DList {
	
	protected int size;
	protected DNode header,trailer;
	
	public MyDlist() {
		size = 0;
		header = new DNode(null,null,null);
		trailer = new DNode(null,header,null);
		header.setNext(trailer);
	}
	
	public MyDlist(String f) {
		
		if(f=="stdin") {
			Scanner inputScanner = new Scanner(System.in);
			String input;
			String stdin = "";
			String inputList[],resultList[];
		    while(!(input = inputScanner.nextLine()).equals("")) {
		    	stdin += input + "@@";
		    }
     
		    inputList = stdin.split("@@");
		    size = inputList.length;
		    constructDList(inputList);
            
		}else {
			
			String line = "",content = "";
			String nodeList[] = null,resultList[] = null;

			try {
				
				BufferedReader br = new BufferedReader(new FileReader(f));
				
				while((line = br.readLine()) != null) {

					content += line + " ";
				}
			    
				br.close();
				
				nodeList = content.split(" ");
				content = "";
				
				for(int i=0;i<nodeList.length;i++){

					if(!nodeList[i].equals("")) content += nodeList[i] + " ";
				}
				
				resultList = content.split(" ");
				size = resultList.length;
				constructDList(resultList);
				
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void constructDList(String nodeList[]) {
		if(nodeList.length==1){
	    	
	    	header = new DNode(nodeList[0],null,null);
	    	trailer = new DNode(nodeList[0],header,null);
	    	header.setNext(trailer);
	    			    	
	    }else{

	    	header = new DNode(nodeList[0],null,null);
            trailer = new DNode(nodeList[0],null,null);
		    for(int j=1;j<nodeList.length;j++) {
		        DNode temp = new DNode(nodeList[j],trailer,null);
		        trailer.setNext(temp);
		        if(j==1) header.setNext(trailer);

		        trailer = trailer.next;
		        
		    }
	    }
	}
	
	
	public static String[] constructArrList(MyDlist l) {
		
		String listArray[] = null;
		String nodeElement = "";
		
		DNode currentNode = l.header.next;
		
		while(currentNode != null) {
			nodeElement += currentNode.element +"@@";
			currentNode = currentNode.next;
		}
		
		listArray = nodeElement.split("@@");
		
		return listArray;
		
	}
	
	public void printList() {

			DNode currentNode = header.next;

			while(currentNode != null) {
				System.out.println(currentNode.element);
				currentNode = currentNode.next;
			}
		

	}
	
	public static MyDlist cloneList(MyDlist u) {
		
		MyDlist cloneList = new MyDlist();
		
		DNode currentNode = u.header;
		DNode nodeCopy = new DNode(currentNode.element,null,null);
		cloneList.header = nodeCopy;
		currentNode = currentNode.next;
		
		while(currentNode != null) {
			nodeCopy.next = new DNode(currentNode.element,nodeCopy,null);
			nodeCopy = nodeCopy.next;
			currentNode = currentNode.next;
		}
		cloneList.size = u.size;
		return cloneList;
		
	}
	
	public static MyDlist union(MyDlist u, MyDlist v) {

		MyDlist unionList = new MyDlist();
		
		String uArr[] = constructArrList(u),unionArr[];
		String nodeElement = "";
		
		for(int i=0;i<uArr.length;i++) {
			nodeElement += uArr[i] + "@@";
		}

		DNode vNode = v.header.next;
		boolean same = false;

        while(vNode != null) {
        	
        	for(int i=0;i<uArr.length;i++) {
        		if(vNode.element.equals(uArr[i])) {
        			same = true;
        			break;
        		}
        	}
        	
        	if(!same) nodeElement += vNode.element + "@@";
        	vNode = vNode.next;
        	same = false;
        }
		
        unionArr = nodeElement.split("@@");
        unionList.constructDList(unionArr);
        
		return unionList;
		
	}
	
	public static MyDlist intersection(MyDlist u, MyDlist v) {
		
        MyDlist intersectionList = new MyDlist();
		
        String uArr[] = constructArrList(u),intersectionArr[];
		String nodeElement = "";

		DNode vNode = v.header.next;
		boolean same = false;
        
        while(vNode != null) {
        	
        	for(int i=0;i<uArr.length;i++) {
        		if(vNode.element.equals(uArr[i])) {
        			same = true;
        			break;
        		}
        	}
        	
        	if(same) nodeElement += vNode.element + "@@";
        	vNode = vNode.next;
        	same = false;
        }
		
        intersectionArr = nodeElement.split("@@");
        intersectionList.constructDList(intersectionArr);
                
		return intersectionList;
		
	}
	
	public static void main(String[] args) throws Exception{
		  
		   System.out.println("please type some strings, one string each line and an empty line for the end of input:");
		   
			//MyDlist emptyList = new MyDlist();
			//emptyList.printList();
		   
		   /** Create the first doubly linked list
		    by reading all the strings from the standard input. */		   
		    MyDlist firstList = new MyDlist("stdin");
		    
		   /** Print all elememts in firstList */
		   // firstList.printList();
		   
		   /** Create the second doubly linked list                         
		    by reading all the strings from the file myfile that contains some strings. */
		  
		   /** Replace the argument by the full path name of the text file */  
		    MyDlist secondList=new MyDlist("C:/Users/��ɫ���/Desktop/9024/assignment1/myfile.txt");

		   /** Print all elememts in secondList */                     
		   // secondList.printList();
		    
		   /** Clone firstList */
		    MyDlist thirdList = cloneList(firstList);
         
		   /** Print all elements in thirdList. */
		   // thirdList.printList();

		  /** Clone secondList */
		    MyDlist fourthList = cloneList(secondList);

		   /** Print all elements in fourthList. */
		   // fourthList.printList();
		    
		   /** Compute the union of firstList and secondList */
		    MyDlist fifthList = union(firstList, secondList);

		   /** Print all elements in thirdList. */ 
		    fifthList.printList(); 

		   /** Compute the intersection of thirdList and fourthList */
		    MyDlist sixthList = intersection(thirdList, fourthList);

		   /** Print all elements in fourthList. */
		   // sixthList.printList();
		  }
}
