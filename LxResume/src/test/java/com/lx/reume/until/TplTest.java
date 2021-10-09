package com.lx.reume.until;

import com.lx.reume.bean.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TplTest {

    private static final Class[] CLASSES = {
            Company.class,
            Project.class,
            Experience.class,
            Contact.class,
            User.class,
    };

    private static final Map<String, String> TPL_DIRS = new HashMap<>();
    private static final String BASE_DIR = "/Users/flyliu/Desktop/Java02/LxResume/src/main/java/com/lx/reume";

    static {
        TPL_DIRS.put("Dao","Dao");
        TPL_DIRS.put("DaoImpl", "dao/impl");
        TPL_DIRS.put("Service", "service");
        TPL_DIRS.put("ServiceImpl", "service/impl");
        TPL_DIRS.put("Servlet", "servlet");
    }

    public static void main(String[] args) throws IOException {
        for (Map.Entry<String,String> entry:TPL_DIRS.entrySet()){
            String suffix = entry.getKey();
            String dir = entry.getValue();
            //获取tpl路径
            String tpl = "tpl/" + suffix + ".tpl";
            String tplFilepath = TplTest.class.getClassLoader().getResource(tpl).getFile();
            String text = FileUtils.readFileToString(new File(tplFilepath), "UTF-8");

            for (Class cls:CLASSES){
                String clsName = cls.getSimpleName();
                String newText = text.replace("#0#",clsName);
                String filename = clsName + suffix + ".java";
                String filepath = BASE_DIR + "/" + dir + "/" + filename;

                File file = new File(filepath);
                if (file.exists()){
                    System.out.println("已经存在"+ file);
                    continue;
                }
                FileUtils.writeStringToFile(file,newText,"UTF-8");
                System.out.println("新生成：" + file);
            }



        }
    }


}
