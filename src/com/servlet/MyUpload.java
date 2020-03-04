package com.servlet;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.dao.TaskDAO;
import com.dao.impl.TaskDAOImpl;
import com.domain.Task;

/**
* @author �Ƴ�
* �ϴ�����������
*/
public class MyUpload extends HttpServlet {

private static final long serialVersionUID = 1L;

public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
	request.setCharacterEncoding("utf-8");//����ҳ����� 
	String id = (String)request.getSession().getAttribute("uploadtaskid");
	//System.out.println(id);
	
	int taskid = -1;
	try
	{
		taskid = Integer.parseInt(id);
	}catch (Exception e)
	{};
	//System.out.println("this is taskid"+taskid);
	TaskDAO dao = new TaskDAOImpl();
	Task task = new Task();
	task = dao.gettask(taskid);
	task.setFileCon(1);
	dao.updatetask(task);
	
        response.setContentType("text;charset=utf-8");//��������

        if ("status".equalsIgnoreCase(request.getParameter("status"))) {
        //ajax�����ϴ�����
            status(response,request);
        } else {
        //�ϴ��ļ�
            long totalSize = request.getContentLength(); 
            request.getSession().setAttribute("totalsize", String.valueOf(totalSize));//���������ļ����ϴ��ߴ�
            try {
                upload(request);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            //response.sendRedirect("/implpage/uploadresult.jsp");    
            request.getRequestDispatcher("/implpage/uploadresult.jsp").forward(request, response);
        }
        
    }

    /**@author �Ƴ�
     * @param request
     * @throws IOException
     * @throws FileUploadException 
     * void Nov 25, 2008 1:46:21 PM
     * �ϴ��ļ�
     */
    private void upload(HttpServletRequest request) throws IOException, FileUploadException {
    
   
    	
    	
    	long completedSize=0;//��ʼ��������ϴ�������Ϊ0
    initSession("completedsize","0",request);//��ʼ��session���Ѿ���ɵ��ϴ��ߴ�
    
        FileItemFactory factory = new DiskFileItemFactory();
        
        // ͨ���ù������󴴽�ServletFileUpload����
        ServletFileUpload upload = new ServletFileUpload(factory);
        
        List items = upload.parseRequest(request);
        
        String result="";//�ļ���ʾ��Ϣ
        
    HashMap map=new HashMap();
        
        for(Iterator i = items.iterator(); i.hasNext();){
        
            FileItem fileItem = (FileItem) i.next();
            
            // �����FileItem���Ǳ���
            if(!fileItem.isFormField()){
	
            if(fileItem.getName()!=null&&fileItem.getSize()>0){

               result="�����ϴ���"+fileItem.getFieldName()+"���ļ�,�벻Ҫˢ�»����뿪��ҳ��...<br />";
               initSession("result",result,request);
              
                    String fileName = fileItem.getName().substring(fileItem.getName().lastIndexOf("\\") + 1);

              
                   String id = (String)request.getSession().getAttribute("uploadtaskid");
                   //System.out.println(id);
                   String path = request.getRealPath("/");
                   path+="upload\\";
                   path+=id;
                   
                   //System.out.println(path);
                    
                    System.out.println("uploadpage1 "+fileName);
                    File d = new File(path);
                    if(!d.exists())
                    {
                    	d.mkdir();
                    	
                    }
                    
                   // String filePath = System.currentTimeMillis()+ "." + fileName.substring(fileName.lastIndexOf(".") + 1);;
                    //System.out.println("uploadpage "+filePath);
                    File file = new File(path, fileName);//�ϴ����ļ�
              
             //�ļ���
                 InputStream in = fileItem.getInputStream();
                 FileOutputStream out = new FileOutputStream(file);      
                 byte[] buffer = new byte[1024]; 

                 int n;
                 while((n = in.read(buffer))!= -1){ 
                 
                     out.write(buffer, 0, n);
                     completedSize += (long) n;//�ϴ����ļ��ߴ��ۼӣ����ڼ����ϴ��ٷֱ�
                     
                     request.getSession().setAttribute("completedsize", String.valueOf(completedSize));//����session����ajax��������

                     try{
        Thread.sleep(2);//�ý��̵ȴ������ڲ���
       }catch(InterruptedException e){
        e.printStackTrace();
       }
                 }
                 in.close();
                 out.close(); 
                 fileItem.delete();// �ڴ���ɾ���������� ɾ����ʱ�ļ�
             }   

            }else{
            
            String name=fileItem.getFieldName();
            String value=fileItem.getString("utf-8");
            //String value=fileItem.getString("GBK");//��������������ڽ���
            map.put(name, value);
            
            }
        }
       

        
    }

    /**@author �Ƴ�
     * @param response
     * @param request
     * @throws IOException 
     * void Nov 25, 2008 1:46:33 PM
     * ��ȡ�ϴ�����
     */
    private void status(HttpServletResponse response,HttpServletRequest request) throws IOException {
        int percent = (int) (getCompletedSize(request) * 100 / (getTotalSize(request) + 0.0001));
        String result=request.getSession().getAttribute("result")==null?"�ļ�":(String)request.getSession().getAttribute("result");
        response.getWriter().print(percent+"-"+result);
    }
    
    
    /**@author �Ƴ�
     * @param request
     * @return 
     * long Nov 25, 2008 1:46:44 PM
     * �ϴ��ļ��ܴ�С
     */
    private long getTotalSize(HttpServletRequest request){
    return request.getSession().getAttribute("totalsize")==null?0L:Long.parseLong((String)request.getSession().getAttribute("totalsize"));
    }
    
    /**@author �Ƴ�
     * @param request
     * @return 
     * long Nov 25, 2008 1:47:00 PM
     * �Ѿ��ϴ��Ĵ�С
     */
    private long getCompletedSize(HttpServletRequest request){
    return request.getSession().getAttribute("completedsize")==null?0L:Long.parseLong((String)request.getSession().getAttribute("completedsize"));

    }

    /**@author �Ƴ�
     * @param sessionName
     * @param sessionValue
     * @param request 
     * void Nov 25, 2008 1:47:19 PM
     * ��ʼ��session�е�ֵ
     */
    private void initSession(String sessionName,String sessionValue,HttpServletRequest request){
    request.getSession().setAttribute(sessionName, sessionValue);
    }

}

