package utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;

public class PasswordUtil {

	/**
	 * @return et tilfeldig generert 16 bytes salt ved ? bruke SHA1PRNG
	 */
	public synchronized static String generateRandomSalt() {
	    SecureRandom sr;
	    byte[] salt = new byte[16];
		try {
			sr = SecureRandom.getInstance("SHA1PRNG");
		    sr.nextBytes(salt);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	    return DatatypeConverter.printHexBinary(salt);
	}

	/**
	 * Genererer en kryptografisk hash for gitt passord og salt.
	 * 
	 * Algoritmen som brukes er PBKDF2WithHmacSHA1
	 * 	- PBKDF2: Password-Based-Key-Derivation-Function
	 *  - WithHmac: HMAC st?r for Keyed-Hash Message Authentication Code
	 *  - SHA1: SHA1 hashing-algoritme
	 * 
	 * Det itereres 1000 ganger.
	 * Output fra denne algoritmen er 256 bits, dvs. 32 bytes.
	 * 
	 * Se https://en.wikipedia.org/wiki/PBKDF2 for mer info om virkem?te.
	 * 
	 * Til slutt omgj?res byte-tabellen til en HEX-streng p? 64 tegn/siffer.
	 *  
	 * @param passord
	 * @param salt
	 * @return en 64 tegn lang HEX-streng som representerer en 32 byte/256 bit hash.
	 */
	public synchronized static String hashWithSalt(String passord, String salt) { 
		
		if (passord == null || salt == null) { //Burde validert skikkelig!!
			throw new IllegalArgumentException();
		}
		
		char[] passchar = passord.toCharArray();
		byte[] saltbytes = DatatypeConverter.parseHexBinary(salt);
		
		byte[] keyhash = null;
		try {
			PBEKeySpec pks = new PBEKeySpec(passchar, saltbytes, 1000, 256);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			keyhash = skf.generateSecret(pks).getEncoded();
			
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return DatatypeConverter.printHexBinary(keyhash);
	}
	
	/**
	 * Sjekker om et passord matcher en hash generert med korresponderende 
	 * hashMedSalt(). 
	 * 
	 * @param passord - Passord som skal sjekkes
	 * @param salt - Saltet som ble brukt ved generering av passordhash
	 * @param passordhash - Det "lagrete" passordet 
	 * @return om passordet matcher
	 */
	public synchronized static boolean validateWithSalt(
			String passord, String salt, String passwordhash) {
		
		if (passord == null || salt == null || passwordhash == null) { // Burde validert skikkelig!!
			throw new IllegalArgumentException();
		}
		
		return passwordhash.equals(hashWithSalt(passord, salt));
	}
}