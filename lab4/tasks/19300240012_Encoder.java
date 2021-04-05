import java.util.Random;

public class Encoder {
    public Encoder() {
        super();
    }

    private String convertHexToString(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < s.length() - 1; i += 2) {
            stringBuilder.append(((char)(Integer.parseInt(s.substring(i, i + 2), 16) ^ 0xFF)));
        }
        return stringBuilder.toString();
    }

    private String convertStringToHex(String s) {
        char[] s1 = s.toCharArray();
        StringBuffer stringBuilder = new StringBuffer();
        for(int i = 0; i < s1.length; ++i) {
            stringBuilder.append(Integer.toHexString(s1[i] ^ 0xFF));
        }
        return stringBuilder.toString();
    }

    private byte[] getSalt() {
        byte[] buf = new byte[]{0, 0, 0, 0, 0, 0};
        Random random = new Random();
        for(int i = 0; i < buf.length; ++i) {
            buf[i] = ((byte)random.nextInt(0xF));
        }
        return buf;
    }

    public String decode(String s) {
        StringBuffer stringBuffer = new StringBuffer();
        if(s.length() == 0) {
            return "";
        }
        else {            
            for(int i = 0; i < s.length(); i += 5) {
                int div = 4 - Integer.parseInt(s.substring(i, i + 1), 16) % 4;
                stringBuffer.append(s.substring(i + 1 + div, i + 5) + s.substring(i + 1, div + i + 1));
            } 
        }

        return this.convertHexToString(stringBuffer.toString()).substring(0, 11);
    }

    public String encode(String s) {
        StringBuffer stringBuffer = new StringBuffer();
        if(s.length() != 11) {
            System.out.println("input error!");
            return "";
        }
        else {
            byte[] salt = this.getSalt();
            String hexStr = this.convertStringToHex(s + "a");            
            for(int i = 0; i < hexStr.length(); i += 4) {
                int div = salt[i / 4] % 4;
                stringBuffer.append(Integer.toHexString(salt[i / 4]));
                stringBuffer.append(hexStr.substring(i + div, i + 4) + hexStr.substring(i, div + i));
            }//交换顺序
        }

        return stringBuffer.toString();
    }
}

