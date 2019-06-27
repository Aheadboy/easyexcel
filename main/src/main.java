import annotation.*;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {
    private static int i = 0;
    static String outputPath = "/Users/linjiejun/Downloads/FirstClass/";

    static int zdmT = -1, blmT = -1, cxyqT = 1;
    static String currClassName = "";
    static String mName = "linjiejun";
    static List<FieldSpec> mFieldSpecs;
    static JavaFile javaFile = null;
    static boolean Request = false;

    static Class annotationCxyq;

    public static void main(String[] args) throws FileNotFoundException {
        ExcelReader excelReader = new ExcelReader(new FileInputStream(new File("/Users/linjiejun/Documents/linwork/npt/doc/cloudposreArch/hehehe.xlsx")), null, new AnalysisEventListener() {
            @Override
            public void invoke(Object object, AnalysisContext context) {//表格一行invoke一次
                ArrayList arrayList = ((ArrayList) object);
//                System.out.println(context.getCurrentSheet().getSheetName());
//                System.out.println(i++ + ((ArrayList) object).toString());

                int zdm = arrayList.indexOf("字段名");

                int blm = arrayList.indexOf("变量名");

                int cxyq = arrayList.indexOf("出现要求");
//                System.out.println(cxyq);
                if (!(zdm == -1 && blm == -1 && cxyq == -1)) {//找到索引
                    zdmT = zdm;
                    blmT = blm;
                    cxyqT = cxyq;
                    System.out.println(zdm + "" + blm + cxyq);
                    Request = !Request;
                    mFieldSpecs = new ArrayList<>();
                } else {
                    String zdmS = "", blmS = "", cxyqS = "";

                    if (zdmT != -1) {
                        zdmS = (String) arrayList.get(zdmT);
                    }
                    if (blmT != -1) {
                        blmS = (String) arrayList.get(blmT);
                    }
                    if (cxyqT != -1) {
                        cxyqS = (String) arrayList.get(cxyqT);
                    }
                    System.out.println("1-" + zdmS + "2-" + blmS + "3-" + cxyqS);
                    if (cxyqS != null) {
                        annotationCxyq = cxyqS.equalsIgnoreCase("c") ? C.class : cxyqS.equalsIgnoreCase("m") ? M.class : cxyqS.equalsIgnoreCase("o") ? O.class : cxyqS.equalsIgnoreCase("R") ? R.class : null;
                    } else {
                        annotationCxyq = null;
                    }

                    FieldSpec.Builder fieldSpec = FieldSpec.builder(String.class, blmS, Modifier.PRIVATE).addJavadoc(zdmS + "\r\n" + AntiThief.getCopyRight(mName));
                    if (annotationCxyq != null) {
                        fieldSpec.addAnnotation(annotationCxyq);
                    }
                    fieldSpec.addAnnotation(Builder.class);
                    mFieldSpecs.add(fieldSpec.build());

                }
                TypeSpec typeSpec;
                if (Request) {


                    typeSpec = TypeSpec.classBuilder(FirstLetterUpperCase.rtn(currClassName) + "Request").addFields(mFieldSpecs).addJavadoc(AntiThief.getCopyRight(mName)).addGetterAndSetter().build();

                } else {
                    typeSpec = TypeSpec.classBuilder(FirstLetterUpperCase.rtn(currClassName) + "Response").addFields(mFieldSpecs).addJavadoc(AntiThief.getCopyRight(mName)).addGetterAndSetter().build();


                }

                javaFile = JavaFile.builder("com.npt.cloudpos.data", typeSpec).build();
                try {
                    javaFile.writeTo(new File(outputPath));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {

                System.out.println("doafter");
            }
        });


        List<Sheet> sheets = excelReader.getSheets();
//        excelReader.read(sheets.get(1));
        for (int j = 0; j < sheets.size(); j++) {
            currClassName = sheets.get(j).getSheetName();
            System.out.println(sheets.get(j).getSheetName() + "jjjj");
            excelReader.read(sheets.get(j));


        }
    }

}
