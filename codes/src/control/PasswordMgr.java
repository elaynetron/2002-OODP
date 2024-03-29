package control;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordMgr {

	  private static final Random RANDOM = new SecureRandom();
	  private static final int ITERATIONS = 10000;
	  private static final int KEY_LENGTH = 256;

	  /**
	   * static utility class
	   */
	  private PasswordMgr() { }

	  /**
	   * Returns a random salt to be used to hash a password.
	   *
	   * @return a 16 bytes random salt
	   */
	  public static byte[] getNextSalt() {
	    byte[] salt = new byte[16];
	    RANDOM.nextBytes(salt);
	    return salt;
	  }

	  /**
	   * Returns a salted and hashed password using the provided hash.<br>
	   * Note - side effect: the password is destroyed (the char[] is filled with zeros)
	   *
	   * @param password the password to be hashed
	   * @param salt     a 16 bytes salt, ideally obtained with the getNextSalt method
	   *
	   * @return the hashed password with a pinch of salt
	   */
	  public static byte[] hash(String password, byte[] salt) {
	    PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
	    Arrays.fill(password.toCharArray(), Character.MIN_VALUE);
	    try {
	      SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	      return skf.generateSecret(spec).getEncoded();
	    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
	      throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
	    } finally {
	      spec.clearPassword();
	    }
	  }

	  /**
	   * Returns true if the given password and salt match the hashed value, false otherwise.<br>
	   * Note - side effect: the password is destroyed (the char[] is filled with zeros)
	   *
	   * @param password     the password to check
	   * @param salt         the salt used to hash the password
	   * @param expectedHash the expected hashed value of the password
	   *
	   * @return true if the given password and salt match the hashed value, false otherwise
	   */
	  public static boolean isExpectedPassword(String password, byte[] salt, byte[] expectedHash) {
	    byte[] pwdHash = hash(password, salt);
	    Arrays.fill(password.toCharArray(), Character.MIN_VALUE);
	    if (pwdHash.length != expectedHash.length) return false;
	    for (int i = 0; i < pwdHash.length; i++) {
	      if (pwdHash[i] != expectedHash[i]) return false;
	    }
	    return true;
	    }
}
