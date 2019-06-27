import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FetchColum {

    List<String> mColumName = new ArrayList();
    Map<String, List<String>> rst = new HashMap<>();

    public FetchColum(String... columName) {
        System.out.println(columName.getClass());
        for (String s : columName) {
            mColumName.add(s);
        }
    }


//    private Map<String, List<String>> gettt(){
//
//        int zdmT=-1, zdm = arrayList.indexOf("字段名");
//
//        int blmT=-1, blm = arrayList.indexOf("变量名");
//
//        int cxyqT=1, cxyq = arrayList.indexOf("出现要求");
////                System.out.println(cxyq);
//        if (!(zdm == -1 && blm == -1 && cxyq == -1)) {
//            zdmT=zdm;blmT=blm;cxyqT=cxyq;
//            System.out.println(zdm + "" + blm + cxyq);
//        } else {
//            String zdmS = "", blmS = "", cxyqS = "";
//
//            if (zdmT != -1) {
//                zdmS = (String) arrayList.get(zdmT);
//            }
//            if (blmT != -1) {
//                blmS = (String) arrayList.get(blmT);
//            }
//            if (cxyqT != -1) {
//                cxyqS = (String) arrayList.get(cxyqT);
//            }
//            System.out.println(zdmS+blmS+cxyqS);
//        }
//    }


}
