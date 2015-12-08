package com.github.longqiany.fastdev.core.encry;

import java.security.MessageDigest;


/**
 * sha加密工具类
 *
 * @author chengzg
 */
public class SHA {
//	private static Logger logger = Logger.getLogger(SHA.class);

    /**
     * 对字符串进行sha加密
     *
     * @param str 需要加密的字符串
     * @return 如果加密字符串为null返回null，其他返回加密后字符串
     */
    public static String encodeBySHA(String str) {

        try {
            if (str == null) {
                return null;
            }
            String result = encrypt(str, "SHA-1");
            /*StringBuffer result = new StringBuffer();
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(str.getBytes("GBK"));
			for(byte b:md.digest()) {
				result.append(String.format("%02X",b));
				//result.append(String.format("%02X",b));
			}*/
            return result.toString();
        } catch (Exception e) {
        }
        return null;
    }

    public static String encodeBySHA(String str, String secretKey) {
        try {
            String encodeStr = "";
            if (str == null) {
                return null;
            }
            encodeStr = str;

            if (secretKey != null) {
                encodeStr = encodeStr + secretKey;
            }
            return encodeBySHA(encodeStr);
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {
        String str = "appType=1deviceInfo=iPhone Simulatorimei=<__NSConcreteUUID 0x795981e0> 7BA0CA49-4FC9-4EA2-9F30-957AAF82B7B3os=1source=1timestamp=1421382600000";
        //String result = encodeBySHA(str, "123");

        String result = encrypt(str, "SHA-1");
        System.out.println(result);
    }


    public static String SHA_256 = "SHA-256";
    public static String SHA_1 = "SHA-1";

    /**
     * @param strSrc
     * @param SHAtype
     * @return
     */
    public static String encrypt(String strSrc, String SHAtype) {
        if (strSrc == null) {
            return null;
        }
        MessageDigest md = null;
        String strDes = null;

        byte[] bt = strSrc.getBytes();
        try {
            md = MessageDigest.getInstance(SHAtype);
            md.update(bt);
            strDes = bytes2Hex(md.digest());
        } catch (Exception e) {
            return null;
        }
        return strDes;
    }

    public static String encrypt(String strSrc, String SHAtype, String secretKey) {
        if (strSrc == null) {
            return null;
        }
        MessageDigest md = null;
        String strDes = null;
        strSrc = strSrc + secretKey;
        byte[] bt = strSrc.getBytes();
        try {
            md = MessageDigest.getInstance(SHAtype);
            md.update(bt);
            strDes = bytes2Hex(md.digest());
        } catch (Exception e) {
            return null;
        }
        return strDes;
    }

    private static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = Integer.toHexString(bts[i] & 0xFF);
            if (tmp.length() == 1) {
                des = des + "0";
            }
            des = des + tmp;
        }
        return des;
    }
}
