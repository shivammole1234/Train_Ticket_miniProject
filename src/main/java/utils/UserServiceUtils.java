package utils;

import org.mindrot.jbcrypt.BCrypt;

public class UserServiceUtils {

    public static  String hashPassword(String planePassword){
        return BCrypt.hashpw(planePassword,BCrypt.gensalt());
    }

    public static boolean checkPassword(String planePassword,String hashedPassword ){
        return BCrypt.checkpw(planePassword,hashedPassword);
    }

}
