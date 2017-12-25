package com.senla.rakickaya.courseplanner.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

public class Decoder {
	@SuppressWarnings("unchecked")
	public <T extends Serializable> T decode(String mes) {
		T obj = null;
		byte[] data = Base64.getDecoder().decode(mes);
		try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
			obj = (T) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			// log
		}
		return obj;
	}

	public String encode(Serializable obj) {

		String mes = null;

		try (ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
				ObjectOutputStream objectStream = new ObjectOutputStream(byteStream)) {
			objectStream.writeObject(obj);
			mes = Base64.getEncoder().encodeToString(byteStream.toByteArray());
		} catch (IOException e) {
			// log
		}
		return mes;
	}

}
