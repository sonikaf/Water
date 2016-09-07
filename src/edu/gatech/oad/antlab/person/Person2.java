package edu.gatech.oad.antlab.person;

/**
 *  A simple class for person 2
 *  returns their name and a
 *  modified string 
 *
 * @author Scott Wolfe
 * @version 1.1
 */
public class Person2 {
    /** Holds the persons real name */
    private String name;
	 	/**
	 * The constructor, takes in the persons
	 * name
	 * @param pname the person's real name
	 */
	 public Person2(String pname) {
	   name = pname;
	 }
	/**
	 * This method should take the string
	 * input and return its characters in
	 * random order.
	 * given "gtg123b" it should return
	 * something like "g3tb1g2".
	 *
	 * @param input the string to be modified
	 * @return the modified string
	 */
	private String calc(String input) {
	  java.util.Random rand = new java.util.Random();
	  java.util.ArrayList<Integer> list = new java.util.ArrayList<>(input.length());
	  for (int i = 0; i < input.length(); i++) {
	      int index;
	      do {
	          index = rand.nextInt(input.length());
	      }
	      while (list.contains(index));
	      list.add(index);
	  }
	  String output = "";
	  for (int i = 0; i < input.length(); i++) {
	      output = output + input.charAt(list.get(i));
	  }
	  return output;
	}
	/**
	 * Return a string rep of this object
	 * that varies with an input string
	 *
	 * @param input the varying string
	 * @return the string representing the 
	 *         object
	 */
	public String toString(String input) {
	  return name + calc(input);
	}

}
