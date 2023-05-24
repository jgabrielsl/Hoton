package br.com.hoton.services.imp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;

@Service
public class EncryptService {

	public String simpleEncrypt(String a, String b) throws Exception {
		try {
			if(a==null)a = "chaveanull";
			if(b==null)b = "chaveanull";
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update((a+randomAlphanumeric()+b).getBytes());
		    byte[] digest = md.digest();
		    String encript = DatatypeConverter.printHexBinary(digest).toUpperCase();
		    return encript.substring(0, (encript.length()<20?encript.length()-1:19));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Erro ao criptografar");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Erro ao criptografar");
		}
	}
	
	public String randomAlphanumeric() {
		
		int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 15;
	    Random random = new Random();

	   return random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	}
	
}
