package com.foxera.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;

/**
 * pdfת��ΪimageͼƬ:
 * @author lailai
 *
 */
public class pdfToImage {

	public static void pdfToImg(String pdfpath,String path,String type){
		Document document=new Document();
		document.setFile(pdfpath);
		float scale=2.5f;//���ű���
		float rotation =0f;//ѡ��Ƕ�
		int pages=document.getNumberOfPages();//pdfʵ��ҳ��
		List<BufferedImage> piclist=new ArrayList<>();
		for(int i=0;i<pages;i++){
			BufferedImage image=(BufferedImage)document.getPageImage(i, GraphicsRenderingHints.SCREEN, Page.BOUNDARY_CROPBOX, rotation, scale);
			piclist.add(image);
		}
		yPic(piclist, path,type);
		
	}
	/**
	 * ��������ͬ��ͼƬ�������һ�𣺿��ȱ�����ͬ
	 * @param piclist
	 * @param outpath
	 * @param type
	 */
	public static void yPic(List<BufferedImage> piclist,String outpath,String type){
		if(piclist==null || piclist.size()<=0){
			System.out.println("ͼƬΪ��");
			return;
		}
		try {
			int height = 0, // �ܸ߶�  
			        width = 0, // �ܿ���  
			        _height = 0, // ��ʱ�ĸ߶� , �򱣴�ƫ�Ƹ߶�  
			        h_height = 0, // ��ʱ�ĸ߶ȣ���Ҫ����ÿ���߶� 
			        picnum=piclist.size();
			File fileImg=null;
			int[] heightArray=new int[picnum];//����ÿ���ļ��ĸ߶�
			BufferedImage buffer=null;
			List<int[]> imgrgb=new ArrayList<>();//��������ͼƬ��rgb
			int[] _imgRGB;//����һ��ͼƬ�е�rgb����
			for(int i=0;i<picnum;i++){
				buffer=piclist.get(i);
				heightArray[i]=_height=buffer.getHeight();//ͼƬ�߶�
				if(i==0){
					width=buffer.getWidth();
				}
				height+=_height;//��ȡ�ܸ߶�
				_imgRGB=new int[width * _height];//��ͼƬ�ж�ȡrgb
				_imgRGB=buffer.getRGB(0, 0, width, _height, _imgRGB, 0, width);
				imgrgb.add(_imgRGB);
			}
			_height=0;//����ƫ�Ƹ߶�Ϊ0
			//������ͼƬ
			BufferedImage imageResult=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			for(int i=0;i<picnum;i++){
				h_height=heightArray[i];
				if(i!=0){
					_height+=h_height;//����ƫ�Ƹ߶�
				}
				imageResult.setRGB(0, _height, width, h_height, imgrgb.get(i), 0, width);
			}
			File outFile=new File(outpath);
			ImageIO.write(imageResult, type, outFile);//д������
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}