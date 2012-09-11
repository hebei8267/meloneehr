package com.tjhx.common.utils;

import javax.imageio.ImageIO;

import com.tjhx.globals.Constants;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

public class PhotoUtils {
	/**
	 * 等比例缩放图片
	 * 
	 * @param source
	 * @param targetW
	 * @param targetH
	 * @return
	 */
	private static BufferedImage _imageResize(BufferedImage source, int targetW, int targetH) {
		// targetW，targetH分别表示目标长和宽
		int type = source.getType();
		BufferedImage target = null;
		double sx = (double) targetW / source.getWidth();
		double sy = (double) targetH / source.getHeight();
		// 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
		// 则将下面的if else语句注释即可
		if (sx > sy) {
			sx = sy;
			targetW = (int) (sx * source.getWidth());
		} else {
			sy = sx;
			targetH = (int) (sy * source.getHeight());
		}

		if (type == BufferedImage.TYPE_CUSTOM) {
			ColorModel cm = source.getColorModel();
			WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetH);
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			target = new BufferedImage(cm, raster, alphaPremultiplied, null);
		} else {
			target = new BufferedImage(targetW, targetH, type);
		}
		Graphics2D g = target.createGraphics();

		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
		g.dispose();

		return target;
	}

	/**
	 * 等比例缩放图片
	 * 
	 * @param fromFileStr
	 * @param saveToFileStr
	 * @param width
	 * @param hight
	 * @throws IOException
	 */
	public static void imageResize(String fromFileStr, String saveToFileStr, int width, int hight) throws IOException {

		String imgType = "jpeg";
		if (fromFileStr.toLowerCase().endsWith(".png")) {
			imgType = "png";
		}

		File saveFile = new File(saveToFileStr);
		File fromFile = new File(fromFileStr);
		BufferedImage srcImage = ImageIO.read(fromFile);
		if (width > 0 || hight > 0) {
			srcImage = _imageResize(srcImage, width, hight);
		}
		ImageIO.write(srcImage, imgType, saveFile);
	}

	/**
	 * 等比例缩放图片
	 * 
	 * @param fromFileStr
	 * @param saveToFileStr
	 * @param width
	 * @param hight
	 * @throws IOException
	 */
	public static void imageResize(File fromFile, File saveToFile, int width, int hight) throws IOException {

		String imgType = "jpeg";

		if (fromFile.getName().endsWith(".png")) {
			imgType = "png";
		}

		BufferedImage srcImage = ImageIO.read(fromFile);
		if (width > 0 || hight > 0) {
			srcImage = _imageResize(srcImage, width, hight);
		}
		ImageIO.write(srcImage, imgType, saveToFile);
	}

	/**
	 * 等比例缩放图片
	 * 
	 * @param fromFileStr
	 * @param saveToFileStr
	 * @throws Exception
	 */
	public static void imageResize(String fromFileStr, String saveToFileStr) throws IOException {
		imageResize(fromFileStr, saveToFileStr, Constants.PHOTO_IMG_WIDTH, Constants.PHOTO_IMG_HEIGHT);
	}

	/**
	 * 等比例缩放图片
	 * 
	 * @param fromFileStr
	 * @param saveToFileStr
	 * @throws Exception
	 */
	public static void imageResize(File fromFile, File saveToFile) throws IOException {
		imageResize(fromFile, saveToFile, Constants.PHOTO_IMG_WIDTH, Constants.PHOTO_IMG_HEIGHT);
	}

	public static void main(String argv[]) {
		try {
			PhotoUtils.imageResize("C:/melone/img/product/test.jpg", "C:/melone/img/product/test3.jpg", 150, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
