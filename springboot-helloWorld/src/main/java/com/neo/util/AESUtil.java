package com.neo.util;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.util.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * AES加密
 * 
 * @author Jeffrey
 * @since 2017年9月22日 下午6:02:05
 */
@SuppressWarnings("restriction")
public class AESUtil {

	/**
	 * 生成AES密钥
	 * 
	 * @param strkey
	 * @return
	 * @throws Exception
	 */
	public static String createKeyPairs() throws Exception {
		String skey = null;
		try {
			KeyGenerator kg = KeyGenerator.getInstance("AES");
			kg.init(128); // 要生成多少位，只需要修改这里即可128, 192或256
			SecretKey sk = kg.generateKey();
			byte[] b = sk.getEncoded();
			skey = byteToHexString(b);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return skey;
	}

	/**
	 * byte数组转化为16进制字符串
	 * 
	 * @param bytes
	 * @return
	 */
	public static String byteToHexString(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			String strHex = Integer.toHexString(bytes[i]);
			if (strHex.length() > 3) {
				sb.append(strHex.substring(6));
			} else {
				if (strHex.length() < 2) {
					sb.append("0" + strHex);
				} else {
					sb.append(strHex);
				}
			}
		}
		return sb.toString();
	}

	/**
	 * base 64 encode
	 * 
	 * @param bytes
	 *            待编码的byte[]
	 * @return 编码后的base 64 code
	 */
	public static String base64Encode(byte[] bytes) {
		return new BASE64Encoder().encode(bytes);
	}

	/**
	 * base 64 decode
	 * 
	 * @param base64Code
	 *            待解码的base 64 code
	 * @return 解码后的byte[]
	 * @throws Exception
	 */
	public static byte[] base64Decode(String base64Code) throws Exception {
		return StringUtils.isEmpty(base64Code) ? null : new BASE64Decoder().decodeBuffer(base64Code);
	}

	/**
	 * AES加密
	 * 
	 * @param content
	 *            待加密的内容
	 * @param encryptKey
	 *            加密密钥
	 * @return 加密后的byte[]
	 * @throws Exception
	 */
	public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		
		// 防止linux下 随机生成key
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		secureRandom.setSeed(encryptKey.getBytes());
		// 根据密钥初始化密钥生成器
		kgen.init(128, secureRandom);
		//kgen.init(128, new SecureRandom(encryptKey.getBytes("utf-8")));

		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));

		return cipher.doFinal(content.getBytes("utf-8"));
	}

	/**
	 * AES加密为base 64 code
	 * 
	 * @param content
	 *            待加密的内容
	 * @param encryptKey
	 *            加密密钥
	 * @return 加密后的base 64 code
	 * @throws Exception
	 */
	public static String aesEncrypt(String content, String encryptKey) throws Exception {
		return base64Encode(aesEncryptToBytes(content, encryptKey));
	}

	/**
	 * AES解密
	 * 
	 * @param encryptBytes
	 *            待解密的byte[]
	 * @param decryptKey
	 *            解密密钥
	 * @return 解密后的String
	 * @throws Exception
	 */
	public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		// 防止linux下 随机生成key
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		secureRandom.setSeed(decryptKey.getBytes());
		// 根据密钥初始化密钥生成器
		kgen.init(128, secureRandom);
		//kgen.init(128, new SecureRandom(decryptKey.getBytes("utf-8")));

		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
		byte[] decryptBytes = cipher.doFinal(encryptBytes);

		return new String(decryptBytes, "utf-8");
	}

	/**
	 * 将base 64 code AES解密
	 * 
	 * @param encryptStr
	 *            待解密的base 64 code
	 * @param decryptKey
	 *            解密密钥
	 * @return 解密后的string
	 * @throws Exception
	 */
	public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
		return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
	}
	
	public static void main(String[] args) throws Exception {

		String content = "我爱你";
		System.out.println("加密前：" + content);

		String key = createKeyPairs();
		System.out.println("加密密钥和解密密钥：" + key);

		String encrypt = aesEncrypt(content, key);
		System.out.println("加密后：" + encrypt);

		String decrypt = aesDecrypt(encrypt, key);
		System.out.println("解密后：" + decrypt);
	}

}
