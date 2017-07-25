package com.foxera.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;

import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;

/**
 * pdf转换为image图片:
 * @author lailai
 *
 */
public class pdfToImage {

	public static void pdfToImg(String pdfpath,final String path,String type){
		Document document=new Document();
		document.setFile(pdfpath);
		float scale=2.5f;//缩放比例
		float rotation =0f;//选择角度
		int pages=document.getNumberOfPages();//pdf实际页数
		final List<BufferedImage> piclist=new ArrayList<>();
		for(int i=0;i<pages;i++){
			BufferedImage image=(BufferedImage)document.getPageImage(i, GraphicsRenderingHints.SCREEN, Page.BOUNDARY_CROPBOX, rotation, scale);
			piclist.add(image);
		}
		/*ExecutorService fixedThreadpool=Executors.newFixedThreadPool(3);
		try {
			fixedThreadpool.execute(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					yPic(piclist, path,type);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		yPic(piclist, path,type);
	}
	/**
	 * 将宽度相同的图片竖向加在一起：宽度必须相同
	 * @param piclist
	 * @param outpath
	 * @param type
	 */
	public static void yPic(List<BufferedImage> piclist,String outpath,String type){
		if(piclist==null || piclist.size()<=0){
			System.out.println("图片为空");
			return;
		}
		try {
			int height = 0, // 总高度  
			        width = 0, // 总宽度  
			        _height = 0, // 临时的高度 , 或保存偏移高度  
			        h_height = 0, // 临时的高度，主要保存每个高度 
			        picnum=piclist.size();
			File fileImg=null;
			int[] heightArray=new int[picnum];//保存每个文件的高度
			BufferedImage buffer=null;
			List<int[]> imgrgb=new ArrayList<>();//保存所有图片的rgb
			int[] _imgRGB;//保存一张图片中的rgb数据
			for(int i=0;i<picnum;i++){
				buffer=piclist.get(i);
				heightArray[i]=_height=buffer.getHeight();//图片高度
				if(i==0){
					width=buffer.getWidth();
				}
				height+=_height;//获取总高度
				_imgRGB=new int[width * _height];//从图片中读取rgb
				_imgRGB=buffer.getRGB(0, 0, width, _height, _imgRGB, 0, width);
				imgrgb.add(_imgRGB);
			}
			_height=0;//设置偏移高度为0
			//生成新图片
			BufferedImage imageResult=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			for(int i=0;i<picnum;i++){
				h_height=heightArray[i];
				if(i!=0){
					_height+=h_height;//计算偏移高度
				}
				imageResult.setRGB(0, _height, width, h_height, imgrgb.get(i), 0, width);
			}
			File outFile=new File(outpath);
			ImageIO.write(imageResult, type, outFile);//写入流中
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static <T> void unmap(final Object buffer){
		AccessController.doPrivileged(new PrivilegedAction<T>() {

			@Override
			public T run() {
				// TODO Auto-generated method stub
				try {
					Method getCleanerMethod=buffer.getClass().getMethod("cleaner",new Class[0]);
					getCleanerMethod.setAccessible(true);//内存中的可见性，不是字节码的
					//Cleaner cleaner=(Cleaner)getCleanerMethod.invoke(buffer, new Object[0]);
					// cleaner.clean();  //Cleaner找不到这个类？
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		});
	}
}
