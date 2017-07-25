package com.foxera.controller;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.foxera.util.HttpUtil;

/**
 * 将pdf转换为图片，不管pdf多少页都拼接为一张图片
 * @author lailai
 *
 */
@Controller
public class pdfToImageController {

	@RequestMapping(value="/pdfToImage.html",method=RequestMethod.GET)
	public String pdfToImage(HttpServletRequest request,HttpServletResponse response) throws Exception{
		return "pdfToImage";
	}
	@RequestMapping(value="/pdfToImageSave.action",method=RequestMethod.POST)
	public void pdfToImageSave(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String path=HttpUtil.getString("files").replace("/", File.separator);
		String absoluteDirectory=request.getSession().getServletContext().getRealPath(path);
		final String absolutePath=(absoluteDirectory+"/").replace("/", File.separator)+"111.pdf";
		File file1=new File(absolutePath);
		String name=file1.getName();
		name=name.substring(0, name.lastIndexOf("."));
		String path2=HttpUtil.getString("pdfToImage").replace("/", File.separator);
		String absoluteDirectory2=request.getSession().getServletContext().getRealPath(path2);
		final String absolutePath2=(absoluteDirectory2+"/").replace("/", File.separator)+name+".jpg";
		File file=new File(absolutePath2);
		if(file.exists()){
			file.delete();
		}
		//创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
		ExecutorService cachedthreadpool=Executors.newCachedThreadPool();
		cachedthreadpool.execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				com.foxera.util.pdfToImage.pdfToImg(absolutePath, absolutePath2,"jpg");
			}
		});
		
		if(!file.exists()){
			cachedthreadpool.execute(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					com.foxera.util.pdfToImage.pdfToImg(absolutePath, absolutePath2,"jpg");
				}
			});
		}
		response.setContentType("text/html;charset=utf-8");
		String abpath=request.getContextPath()+"/images/"+name+".jpg";
		response.getWriter().write(abpath);
	}
}
