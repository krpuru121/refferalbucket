package com.vassarlabs.common.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
  * 
  * @param <T>
  *          Object's type in the tree.
*/
public class VLTree<T> {

  private T head;

  private ArrayList<VLTree<T>> leafs = new ArrayList<VLTree<T>>();

  private VLTree<T> parent = null;

  private HashMap<T, VLTree<T>> locate = new HashMap<T, VLTree<T>>();
  

  private  int indent = 2;
  
 
  public VLTree(T head) {
    this.head = head;
    locate.put(head, this);
  }
  
  //default indent is 2 , to make it 0 invoke this constructor

  public VLTree(T head,int indent) {
	    this.head = head;
	    this.indent = indent;
	    locate.put(head, this);
  }

  
  public void addLeaf(T root, T leaf) {
    if (locate.containsKey(root)) {
      locate.get(root).addLeaf(leaf);
    } else {
      addLeaf(root).addLeaf(leaf);
    }
  }

  public VLTree<T> addLeaf(T leaf) {
	  VLTree<T> t = new VLTree<T>(leaf);
    leafs.add(t);
    t.parent = this;
    t.locate = this.locate;
    locate.put(leaf, t);
    return t;
  }

  public VLTree<T> setAsParent(T parentRoot) {
	  VLTree<T> t = new VLTree<T>(parentRoot);
    t.leafs.add(this);
    this.parent = t;
    t.locate = this.locate;
    t.locate.put(head, this);
    t.locate.put(parentRoot, t);
    return t;
  }

  public T getHead() {
    return head;
  }

  public VLTree<T> getTree(T element) {
    return locate.get(element);
  }

  public VLTree<T> getParent() {
    return parent;
  }

  public Collection<T> getSuccessors(T root) {
    Collection<T> successors = new ArrayList<T>();
    VLTree<T> tree = getTree(root);
    if (null != tree) {
      for (VLTree<T> leaf : tree.leafs) {
        successors.add(leaf.head);
      }
    }
    return successors;
  }

  public Collection<VLTree<T>> getSubTrees() {
    return leafs;
  }

  public static <T> Collection<T> getSuccessors(T of, Collection<VLTree<T>> in) {
    for (VLTree<T> tree : in) {
      if (tree.locate.containsKey(of)) {
        return tree.getSuccessors(of);
      }
    }
    return new ArrayList<T>();
  }

 


  private String printTree(int increment) {
    String s = "";
    String inc = "";
    for (int i = 0; i < increment; ++i) {
      inc = inc + "\u00a0";
    }
  //  System.out.println(inc);
   
    s = inc + head;
    for (VLTree<T> child : leafs) {
    	System.out.println(this.indent);
    	child.indent = this.indent;
      s += "\n" + child.printTree(increment + this.indent);
    }
    return s;
  }
  
/*  public List<String> printTreeToList(int increment) { 
	    String s = "";
	    String inc = "";
	    List<String> lineList = new ArrayList<String>();
	    
	    for (int i = 0; i < increment; ++i) {
	      inc = inc + " ";
	    }
	    s = inc + head;
	    for (VLTree<T> child : leafs) {
	      s = child.printTreeToList(increment + indent);
	      lineList.add(s);
	    }
	    return lineList;
	  }*/
  
  public List<String> printTreeToList(int increment)
  {
	  List<String> treeStringLines = new ArrayList<String>();
	  String treeInString = printTree( increment);
	  StringTokenizer stringTokenizer = new StringTokenizer(treeInString, "\n");
	  while (stringTokenizer.hasMoreElements()) {
		  String object = (String) stringTokenizer.nextElement();
		  treeStringLines.add(object);
	}
	  return treeStringLines;
  }
  
  public static void main(String[] args){
	  String rootNodeValue1 = "Root";
	  VLTree<String> vlTree = new VLTree<String>(rootNodeValue1,0);
	  
	  String childNodeValue1 ="C1";
	  String childNodeValue2 ="C2";

	  
	  String childNodeValue11 ="C11";
	  String childNodeValue12 ="C12";
	  String childNodeValue21 ="C21";
	  String childNodeValue22 ="C22";
	  
	  vlTree.addLeaf(rootNodeValue1, childNodeValue1);
	  vlTree.addLeaf(rootNodeValue1, childNodeValue2);
	  
	  vlTree.addLeaf(childNodeValue1, childNodeValue11);
	  vlTree.addLeaf(childNodeValue1, childNodeValue12);
	  vlTree.addLeaf(childNodeValue2, childNodeValue21);
	  vlTree.addLeaf(childNodeValue2, childNodeValue22);
	
	  System.out.println(vlTree.printTree(0));
	  
  }
  
  
}