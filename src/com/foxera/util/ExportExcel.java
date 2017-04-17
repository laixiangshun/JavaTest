package com.foxera.util;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * @author fox
 * @date 2017-03-07
 * @description ������excel������
 */
public class ExportExcel {
	/**
	 * 
	 * @param fileName
	 *            EXCEL�ļ�����
	 * @param Title
	 *            EXCEL�ļ���һ���б��⼯��
	 * @param Field
	 *            ������Ӧ��model�����Լ���
	 * @param listContent
	 *            EXCEL�ļ��������ݼ���
	 * @param tagerClass
	 *            List�е���������
	 * @param response
	 * @return ���ز��������ʾ
	 * @description ����ָ������������ָ�����Ե����ݣ������е�����Ҳ���Ե�����
	 */
	public final static <T> String exportExcel(String fileName, String[] Title,
			String[] Field, List<T> listContent, T tagerClass,
			HttpServletResponse response) {
		String result = "ϵͳ��ʾ��Excel�ļ������ɹ���";
		// ���¿�ʼ�����EXCEL
		try {
			// ������������Ա�򿪱���Ի���______________________begin
			OutputStream os = response.getOutputStream();// ȡ�������
			response.reset();// ��������
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// �趨����ļ�ͷ
			response.setContentType("application/msexcel");// �����������
			// ������������Ա�򿪱���Ի���_______________________end

			/** **********����������************ */
			WritableWorkbook workbook = Workbook.createWorkbook(os);

			/** **********����������************ */

			WritableSheet sheet = workbook.createSheet("Sheet1", 0);

			/** **********�����ݺ��ӡ��Ĭ��Ϊ�ݴ򣩡���ӡֽ***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			/** ************���õ�Ԫ������************** */
			WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
			WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.BOLD);

			/** ************�����������ֵ�Ԫ����ʽ������************ */
			// ���ڱ������
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // ����
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // ���ִ�ֱ����
			wcf_center.setAlignment(Alignment.CENTRE); // ����ˮƽ����
			wcf_center.setWrap(false); // �����Ƿ���

			// �������ľ���
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // ����
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // ���ִ�ֱ����
			wcf_left.setAlignment(Alignment.LEFT); // ����ˮƽ����
			wcf_left.setWrap(false); // �����Ƿ���

			/** ***************������EXCEL��ͷ����⣬��ʱʡ��********************* */
			// sheet.mergeCells(0, 0, colWidth, 0);
			// sheet.addCell(new Label(0, 0, "XX����", wcf_center));
			/** ***************������EXCEL��һ���б���********************* */
			for (int i = 0; i < Title.length; i++) {
				sheet.addCell(new Label(i, 0, Title[i], wcf_center));
			}
			/** ***************������EXCEL��������********************* */
			List<Field> fields = new ArrayList<Field>();
			Field field = null;
			for (int i = 0; i < Field.length; i++) {
				try {
					// ��ȡ���������
					field = tagerClass.getClass().getDeclaredField(Field[i]);
				} catch (Exception e) {
					// ��ȡ���������
					field = tagerClass.getClass().getSuperclass()
							.getDeclaredField(Field[i]);
				}
				field.setAccessible(true);// ����Java����ļ�� ,��˽������Ҳ����
				fields.add(field);
			}
			int i = 1;
			for (T obj : listContent) {
				int j = 0;
				for (Field v : fields) {
					v.setAccessible(true);
					Object va = v.get(obj);
					if (va == null) {
						va = "";
					}
					sheet.addCell(new Label(j, i, va.toString(), wcf_left));
					j++;
				}
				i++;
			}
			/** **********�����ϻ����е�����д��EXCEL�ļ���******** */
			workbook.write();
			/** *********�ر��ļ�************* */
			workbook.close();

		} catch (Exception e) {
			result = "ϵͳ��ʾ��Excel�ļ�����ʧ�ܣ�ԭ��" + e.toString();
			System.out.println(result);
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param fileName
	 *            EXCEL�ļ�����
	 * @param Title
	 *            EXCEL�ļ���һ���б��⼯��
	 * @param listContent
	 *            EXCEL�ļ��������ݼ���
	 * @param response
	 * @return ���ز��������ʾ
	 * @description �����ڵ��������������Ե����ݡ�
	 */
	public final static <T> String exportExcel(String fileName, String[] Title,
			List<T> listContent, HttpServletResponse response) {
		String result = "ϵͳ��ʾ��Excel�ļ������ɹ���";
		// ���¿�ʼ�����EXCEL
		try {
			// ������������Ա�򿪱���Ի���______________________begin
			OutputStream os = response.getOutputStream();// ȡ�������
			response.reset();// ��������
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// �趨����ļ�ͷ
			response.setContentType("application/msexcel");// �����������
			// ������������Ա�򿪱���Ի���_______________________end

			/** **********����������************ */
			WritableWorkbook workbook = Workbook.createWorkbook(os);

			/** **********����������************ */

			WritableSheet sheet = workbook.createSheet("Sheet1", 0);

			/** **********�����ݺ��ӡ��Ĭ��Ϊ�ݴ򣩡���ӡֽ***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			/** ************���õ�Ԫ������************** */
			WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
			WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.BOLD);

			/** ************�����������ֵ�Ԫ����ʽ������************ */
			// ���ڱ������
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // ����
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // ���ִ�ֱ����
			wcf_center.setAlignment(Alignment.CENTRE); // ����ˮƽ����
			wcf_center.setWrap(false); // �����Ƿ���

			// �������ľ���
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // ����
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // ���ִ�ֱ����
			wcf_left.setAlignment(Alignment.LEFT); // ����ˮƽ����
			wcf_left.setWrap(false); // �����Ƿ���

			/** ***************������EXCEL��ͷ����⣬��ʱʡ��********************* */
			// sheet.mergeCells(0, 0, colWidth, 0);
			// sheet.addCell(new Label(0, 0, "XX����", wcf_center));
			/** ***************������EXCEL��һ���б���********************* */
			for (int i = 0; i < Title.length; i++) {
				sheet.addCell(new Label(i, 0, Title[i], wcf_center));
			}
			/** ***************������EXCEL��������********************* */
			Field[] fields = null;
			int i = 1;
			for (T obj : listContent) {
				fields=obj.getClass().getDeclaredFields();
				int j = 0;
				for (Field v : fields) {
					v.setAccessible(true);
					Object va = v.get(obj);
					if (va == null) {
						va = "";
					}
					sheet.addCell(new Label(j, i, va.toString(), wcf_left));
					j++;
				}
				i++;
			}
			/** **********�����ϻ����е�����д��EXCEL�ļ���******** */
			workbook.write();
			/** *********�ر��ļ�************* */
			workbook.close();

		} catch (Exception e) {
			result = "ϵͳ��ʾ��Excel�ļ�����ʧ�ܣ�ԭ��" + e.toString();
			System.out.println(result);
			e.printStackTrace();
		}
		return result;
	}
}