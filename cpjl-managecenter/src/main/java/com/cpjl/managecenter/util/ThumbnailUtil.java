package com.cpjl.managecenter.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ThumbnailUtil {
	/**
	 * 宽高固定，截取图片的一部分 （不露白） 可用于新闻的缩略图
	 * 
	 * 如果原图宽高不够，则以短边为准，等比例裁切
	 * 
	 * @param width
	 *            裁切后的宽度
	 * 
	 * @param height
	 *            裁切后的高度
	 * 
	 * @return map
	 * 
	 * @throws IOException
	 * 
	 */
	public static Map<String, Object> getRegion(BufferedImage bufferedImage, Integer width, Integer height)
			throws IOException {

		if (bufferedImage == null) {
			throw new IllegalArgumentException("bufferedImage == null!");
		}
		Integer originalWidth = bufferedImage.getWidth();
		Integer originalHeight = bufferedImage.getHeight();

		if (width == null || width <= 0) {
			throw new IllegalArgumentException("width,must greater than 0!");
		}
		if (height == null || height <= 0) {
			throw new IllegalArgumentException("height,must  greater than 0!");
		}
		double w = width.doubleValue() / originalWidth.doubleValue();
		double h = height.doubleValue() / originalHeight.doubleValue();

		int x = 0, y = 0, x_width = 0, y_height = 0;
		double scale = 1.0;

		// 从原图中裁切，原图宽高都大于 需要的
		// 先裁切 再缩小
		if (w < 1 && h < 1) {
			if (w < h) {
				y_height = originalHeight;
				x_width = (int) (width / h);
				x = (originalWidth - x_width) / 2;
				y = 0;
				scale = (double) height / y_height;

			} else {
				x_width = originalWidth;
				y_height = (int) (height / w);
				x = 0;
				y = (originalHeight - y_height) / 2;
				scale = width / x_width;
			}
		}
		// 先裁切，再放大
		else if (w < 1 && h >= 1) {
			y_height = originalHeight;
			x_width = (int) (width / h);
			x = (originalWidth - x_width) / 2;
			y = 0;
			scale = 1;
		} else if (h < 1 && w >= 1) {
			x_width = originalWidth;
			y_height = (int) (height / w);
			x = 0;
			y = (originalHeight - y_height) / 2;
			scale = 1;
		} else if (w >= 1 && h >= 1) {
			if (w < h) {
				y_height = originalHeight;
				x_width = (int) (width / h);
				x = (originalWidth - x_width) / 2;
				y = 0;
				scale = 1;
			} else {
				x_width = originalWidth;
				y_height = (int) (height / w);
				x = 0;
				y = (originalHeight - y_height) / 2;
				scale = 1;
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("x", x);
		map.put("y", y);
		map.put("width", x_width);
		map.put("height", y_height);
		map.put("scale", scale);

		return map;
	}

	/*
	 * 宽度固定，等比例缩放
	 */
	public static Map<String, Double> getRegion(BufferedImage bufferedImage, Integer width) throws IOException {
		// TODO 自动生成的方法存根

		if (bufferedImage == null) {
			throw new IllegalArgumentException("bufferedImage == null!");
		}
		Integer originalWidth = bufferedImage.getWidth();
		if (width == null || width <= 0) {
			throw new IllegalArgumentException("width,must greater than 0!");
		}
		Double scale = 1.0;
		if (width > originalWidth) {
			scale = 1.0;
		} else {
			scale = width.doubleValue() / originalWidth.doubleValue();
		}

		Map<String, Double> map = new HashMap<String, Double>();
		map.put("scale", scale);

		return map;
	}
}
