package edu.gatech.tbd.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import javax.swing.filechooser.FileFilter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class PersistenceManager {

	private static ArrayList<Object> objectList = new ArrayList<Object>();

	/**
	 * Add a persistant object to the data store
	 * 
	 * @param s
	 */
	public static void addObject(Object s) {
		objectList.add(s);
	}

	/**
	 * removes an object from the data store
	 * 
	 * @param s
	 */
	public static void removeObject(Object s) {
		objectList.remove(s);
	}

	public static void updateObject(Object o, int oldHash) {
		File file = new File("data/" + o.getClass().getTypeName() + "_" + oldHash + ".txt");

		if (file.exists()) {
			file.delete();
		}

		saveObject(o);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> getObjects(Class<T> theClass) {
		ArrayList<T> ret = new ArrayList<T>();
		for(Object o : objectList) {
			if(o.getClass().equals(theClass)) {
				ret.add((T)o);
			}
		}
		return ret;
	}

	/**
	 * writes the objects out to the disk
	 */
	public static void saveObjects() {
		File dataFolder = new File("data/");
		dataFolder.mkdir();
		for (Object o : objectList) {
			saveObject(o);
		}

	}

	private static void saveObject(Object o) {

		Gson g = new Gson();
		String s = g.toJson(o);
		
		System.out.printf("%x\n", o.hashCode());
		String fn = String.format("data/%s_%x.txt", o.getClass().getTypeName(), o.hashCode());
		System.out.println(fn);
		File file = new File(fn);
		
		// if file doesn't exists, then create it
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (IOException e) {
			System.err.println("There was an error writing object: " + o + " to the disk!");
			e.printStackTrace();
		}

		try (FileOutputStream fop = new FileOutputStream(file)) {

			// get the content in bytes
			byte[] contentInBytes = s.getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();
		} catch (IOException e) {
			System.err.println("There was an error writing object: " + o + " to the disk!");
			e.printStackTrace();
		}
		System.out.printf("Saved object " + o + " (%x)\n", o.hashCode());
	}

	/**
	 * loads the objects in from the disk
	 */
	public static void openObjects() {
		File folder = new File("data/");
		File[] listOfFiles = folder.listFiles((dir, fileName) -> { return fileName.endsWith(".txt"); });
		
		if(listOfFiles == null) return; // this happens when the data folder is missing
		// we just dont have anything to load here

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				addObject(openObject(listOfFiles[i]));
			}
		}
		
		System.out.printf("Successfully loaded %d objects\n", objectList.size());
	}

	private static Object openObject(File file) {
		Gson g = new Gson();
		
		byte[] contentInBytes = null;

		try (FileInputStream fip = new FileInputStream(file)) {
			contentInBytes = new byte[fip.available()];
			fip.read(contentInBytes);
			fip.close();
			
			String[] split = file.getName().split("_");

			Object ret = g.fromJson(
				new String(contentInBytes, "UTF-8"), 
				Class.forName(split[0]));
				// this extracts the class name from the filename and uses it to find the appropriate class object
				// if there's any kind of error then it will just throw an exception which we catch below
			
			String oldhash = split[1];
			System.out.printf("Loading object " + ret + " (%x/%s)\n", ret.hashCode(), oldhash);
			return ret;
			
		} catch (Exception e) {
			System.err.println("There was an error reading object from the disk!");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * initializes the persistance subsystem
	 */
	public static void setup() {
		openObjects(); // load objects from the disk

		// save the database every 5 minutes while the program is open
		/*ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		executor.scheduleAtFixedRate(() -> {
			saveObjects();
		}, 0, 5, TimeUnit.MINUTES);*/
	}
	
	public static int generateObjectHash(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		int ret = 1;		
		for(Field f : fields) {
			try {
				f.setAccessible(true);
				Object value = f.get(o);
				
				ret = 37 * ret + value.hashCode();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
}
