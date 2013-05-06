package com.gipermarket.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Logger;

public class CacheUtil {
	private static final Logger log = Logger.getLogger(CacheUtil.class.getName());
	private static final String CACHE_FILE = "cache.tmp";
	private String fileName;

	public CacheUtil() {
		this(CACHE_FILE);
	}

	public CacheUtil(String fileName) {
		this.fileName = fileName;
	}

	private File getFile() {
		return new File(System.getProperty("java.io.tmpdir"), fileName);
	}

	public void serialize(Object o) {
		serialize(o, getFile());
	}

	private void serialize(Object o, File f) {
		log.info("write object to " + f.getAbsolutePath());
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(o);
			oos.close();
		} catch (Exception e) {
			log.severe("Can't serialize object: " + o.toString() + "; " + e.getMessage() + "Chart is not supported");
		}
	}

	public <T> T deserialize(Class<T> c) {
		Object deserialize = deserialize(getFile());
		T cast = null;
		try {
			cast = c.cast(deserialize);
		} catch (Exception e) {
		}
		return cast;
	}

	public Object deserialize() {
		return deserialize(getFile());
	}

	private Object deserialize(File f) {
		log.info("read object from " + f.getAbsolutePath());
		try {
			if (f.exists()) {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				Object readObject = ois.readObject();
				ois.close();
				return readObject;
			}
		} catch (Exception e) {
			log.severe("Cant't load cached object " + e.getMessage());
			f.delete();
			log.info("File deleted: " + f.getAbsolutePath());
		}
		return null;

	}
}
