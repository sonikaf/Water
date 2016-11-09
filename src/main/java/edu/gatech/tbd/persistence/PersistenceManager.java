package edu.gatech.tbd.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;

public class PersistenceManager {

	private static ArrayList<Object> objectList = new ArrayList<>();

	/**
	 * Add a persistant object to the data store
	 * 
	 * @param o the object to save
	 * @param save save the object to disk when adding
	 */
	private static void addObject(Object o, boolean save) {
		objectList.add(o);
		if(save) saveObject(o);
	}
	
	/**
	 * Add a persistant object to the data store
	 * 
	 * @param o the object to save
	 */
	public static void addObject(Object o) {
		addObject(o, true);
	}

	/**
	 * removes an object from the data store
	 * 
	 * @param o object to be removed
	 */
	public static void removeObject(Object o) {
		objectList.remove(o);
		
		String fn = String.format("data/%s_%x.txt", o.getClass().getTypeName(), o.hashCode());
		File file = new File(fn);
			
		if (file.exists()) {
			file.delete();
		}
	}

	public static void updateObject(Object o, int oldHash) {
		String fn = String.format("data/%s_%x.txt", o.getClass().getTypeName(), oldHash);
		File file = new File(fn);
			
		if (file.exists()) {
			file.delete();
		} else {
			System.err.println("Missing old object file: " + file.getName());
		}

		saveObject(o);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> getObjects(Class<T> theClass) {
		return objectList.stream().filter(o -> o.getClass().equals(theClass)).map(o -> (T) o).collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * writes the objects out to the disk
	 */
	/*
	public static void saveObjects() {
		File dataFolder = new File("data/");
		dataFolder.mkdir();
		for (Object o : objectList) {
			saveObject(o);
		}
	}
	*/

	private static void saveObject(Object o) {
		
		File dataFolder = new File("data/");
		dataFolder.mkdir();

		Gson g = new Gson();
		String s = g.toJson(o);
		
		String fn = String.format("data/%s_%x.txt", o.getClass().getTypeName(), o.hashCode());
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
			System.out.printf("Saved object " + o + " (%x)\n", o.hashCode());
		} catch (IOException e) {
			System.err.println("There was an error writing object: " + o + " to the disk!");
			e.printStackTrace();
		}
		
	}

	/**
	 * loads the objects in from the disk
	 */
	private static void openObjects() {
		File folder = new File("data/");
		File[] listOfFiles = folder.listFiles((dir, fileName) -> fileName.endsWith(".txt"));
		
		if(listOfFiles == null) return; // this happens when the data folder is missing
		// we just dont have anything to load here

		for(File f : listOfFiles) {
			if (f.isFile()) {
				addObject(openObject(f), false);
			}
		}
		
		System.out.printf("Successfully loaded %d objects\n", objectList.size());
	}

	private static Object openObject(File file) {
		Gson g = new Gson();
		
		byte[] contentInBytes;

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
			
			String oldhash = split[1].substring(0, split[1].length() - 4);
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
		ArrayList<Field> fields = new ArrayList<>();
		Class<?> cur = o.getClass();
		// iterate through all the way up to the top
		while(cur != Object.class) {
			fields.addAll(Arrays.asList(cur.getDeclaredFields()));
			cur = cur.getSuperclass();
		}
		int ret = 1;		
		for(Field f : fields) {
			try {
				f.setAccessible(true);
				Object value = f.get(o);
				
				// strings and primitives are the only ones that produce 
				// consistent hashcodes across runs
				if(!(value.getClass().equals(String.class) ||
					 value.getClass().equals(Integer.class) ||
					 value.getClass().equals(Float.class) ||
					 value.getClass().equals(Double.class)
					)) {
					continue;
				}
				
				ret = 37 * ret + value.hashCode();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
}
