public class FirstLetterUpperCase {


    public static String rtn(String fileName) {
        char ct = Character.toUpperCase(fileName.charAt(0));
        String sChat = String.valueOf(ct);

        String upedName = sChat + fileName.substring(1);

        return upedName;
    }

}
