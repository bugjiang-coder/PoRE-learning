public class Checker {
    byte[] secret;

    public Checker() {
        super();
        this.secret = new byte[]{0x70, 0x64, 0x64, 0x44, 0x1F, 0x5, 0x72, 0x78};
    }

    private static byte charToByteAscii(char c) {
        return (byte)c;
    }

    private boolean checkStr1(String s) {
        for(int i=0;i<s.length();++i){
            int s_int = Checker.charToByteAscii(s.charAt(i));
            int key = s_int^(i*11);
            if(key!=this.secret[i]){
                return false;
            }
        }
        return true;
    }

    private boolean checkStr2(String s) {
        try {
            int key = Integer.parseInt(s);
            if(key < 1000) {
                return false;
            }

            if(key % 16 != 0 && key % 27 != 0) {
                if(key % 10 == 8) {
                    return true;
                }
                return false;
            }
        }
        catch(NumberFormatException e) {
            return false;
        }

        return true;
    }
    
    public boolean check(String s) {
        if(s.length() == 12) {
            String v1 = s.substring(0, 8);
            String v2 = s.substring(8, 12);
            if((this.checkStr1(v1)) && (this.checkStr2(v2))) {
                return true;
            }
        }

        return false;
    }
}

