package file;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import method.doFile;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
* @ClassName: UploadHandleServlet
* @Description: TODO(这里用一句话描述这个类的作用)
* @author: 孤傲苍狼
* @date: 2015-1-3 下午11:35:50
*
*/ 
public class UploadHandleServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    			doFile df = new doFile();
    			Map rm = df.upload(request, response) ;
                System.out.println(rm);
                request.setAttribute("message",rm);
                request.getRequestDispatcher("/message.jsp").forward(request, response);
    }
    
    /**
    * @Method: makeFileName
    * @Description: 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
    * @Anthor:孤傲苍狼
    * @param filename 文件的原始名称
    * @return uuid+"_"+文件的原始名称
    */ 
    private String makeFileName(String filename){  //2.jpg
        //为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
        return UUID.randomUUID().toString() + "_" + filename;
    }
    
    /**
     * 为防止一个目录下面出现太多文件，要使用hash算法打散存储
    * @Method: makePath
    * @Description: 
    * @Anthor:孤傲苍狼
    *
    * @param filename 文件名，要根据文件名生成存储目录
    * @param savePath 文件存储路径
    * @return 新的存储目录
    */ 
    private String makePath(String filename,String savePath){
        //得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
        int hashcode = filename.hashCode();
        int dir1 = hashcode&0xf;  //0--15
        int dir2 = (hashcode&0xf0)>>4;  //0-15
        //构造新的保存目录
        String dir = savePath + "/" + dir1 + "/" + dir2;  //upload\2\3  upload\3\5
        //File既可以代表文件也可以代表目录
        File file = new File(dir);
        //如果目录不存在
        if(!file.exists()){
            //创建目录
            file.mkdirs();
        }
        return dir;
    }
    //private String namePath(String filename,String savePath)
    //{
    	
    //}
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}