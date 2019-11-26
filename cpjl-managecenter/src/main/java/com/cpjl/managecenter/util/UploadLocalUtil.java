package com.cpjl.managecenter.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.ueditor.define.FileTypeMap;
import com.cpjl.managecenter.dto.UploadResultDto;
import com.cpjl.managecenter.model.BaseResult;

import net.coobird.thumbnailator.Thumbnails;

/*
 * 上传到本地文件夹
 */
public class UploadLocalUtil {
	private static final Logger logger = LoggerFactory.getLogger(UploadLocalUtil.class);

	/*
	 * 缩略图 宽高裁切
	 */
	public static UploadResultDto thumnail(MultipartFile file, int width, int height) throws IOException {
		UploadResultDto dto = new UploadResultDto();
		// 1、允许的扩展名
		List<String> allowedExt = new ArrayList<String>();
		allowedExt.add(".jpg");
		allowedExt.add(".jpeg");
		allowedExt.add(".png");
		allowedExt.add(".gif");
		// 2、最大上传文件2M
		Long maxSize = 5l * 1024 * 1024;

		// 3、文件基本信息
		String originalFileName = file.getOriginalFilename();
		Integer fileType = FileTypeMap.IMAGE;
		Long fileSize = file.getSize();
		String fileExt = FileUtil.getExt(originalFileName);
		String fileName = UUID.randomUUID() + fileExt;
		InputStream is = file.getInputStream();

		dto.setOriginalFileName(originalFileName);
		dto.setFileType(fileType);
		dto.setFileExt(fileExt);
		dto.setFileSize(fileSize);
		dto.setFileName(fileName);

		// 4.验证
		BaseResult<Object> baseResult1 = FileUtil.isAllowedExt(fileExt, allowedExt);
		if (!baseResult1.getResult()) {
			dto.setResult(false);
			dto.setMessage(baseResult1.getMessage());
			return dto;
		}

		BaseResult<Object> baseResult2 = FileUtil.isExceedingInSize(fileSize, maxSize);
		if (!baseResult2.getResult()) {
			dto.setResult(false);
			dto.setMessage(baseResult2.getMessage());
			return dto;
		}

		// 5.按宽度等比例缩放
		BufferedImage bufferedImage = ImageIO.read(is);
		Map<String, Object> region = ThumbnailUtil.getRegion(bufferedImage, width, height);
		bufferedImage = Thumbnails.of(bufferedImage)//
				.sourceRegion(Integer.parseInt(region.get("x").toString()), //
						Integer.parseInt(region.get("y").toString()), //
						Integer.parseInt(region.get("width").toString()), //
						Integer.parseInt(region.get("height").toString()))
				.scale(Double.parseDouble(region.get("scale").toString()))//
				.asBufferedImage();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, fileExt.substring(1), os);
		is = new ByteArrayInputStream(os.toByteArray());

		// 6.上传
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd");
		String yyyyMMdd = sdf.format(new Date());
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		String savePath = yyyyMMdd + "/" + uuid + fileExt;
		String imageRootPath = PropertiesUtil.getProperty("app.upload.rootPath");
		logger.info("imageRootPath:" + imageRootPath);
		logger.info("savePath:" + savePath);
		dto = FileUploader.saveFileByInputStream(is, savePath, imageRootPath);

		return dto;
	}

	/*
	 * 文章正文图片，宽度640
	 */
	public static UploadResultDto toWidth(MultipartFile file, int width) throws IOException {
		UploadResultDto dto = new UploadResultDto();
		// 1、允许的扩展名
		List<String> allowedExt = new ArrayList<String>();
		allowedExt.add(".jpg");
		allowedExt.add(".jpeg");
		allowedExt.add(".png");
		allowedExt.add(".gif");
		// 2、最大上传文件2M
		Long maxSize = 5l * 1024 * 1024;

		// 3、文件基本信息
		String originalFileName = file.getOriginalFilename();
		Integer fileType = FileTypeMap.IMAGE;
		Long fileSize = file.getSize();
		String fileExt = FileUtil.getExt(originalFileName);
		String fileName = UUID.randomUUID() + fileExt;
		InputStream is = file.getInputStream();

		dto.setOriginalFileName(originalFileName);
		dto.setFileType(fileType);
		dto.setFileExt(fileExt);
		dto.setFileSize(fileSize);
		dto.setFileName(fileName);

		// 4.验证
		BaseResult<Object> baseResult1 = FileUtil.isAllowedExt(fileExt, allowedExt);
		if (!baseResult1.getResult()) {
			dto.setResult(false);
			dto.setMessage(baseResult1.getMessage());
			return dto;
		}

		BaseResult<Object> baseResult2 = FileUtil.isExceedingInSize(fileSize, maxSize);
		if (!baseResult2.getResult()) {
			dto.setResult(false);
			dto.setMessage(baseResult2.getMessage());
			return dto;
		}

		// 5.按宽度等比例缩放
		BufferedImage bufferedImage = ImageIO.read(is);
		Map<String, Double> region = ThumbnailUtil.getRegion(bufferedImage, width);
		if (!region.get("scale").equals(1.0)) {
			bufferedImage = Thumbnails.of(bufferedImage)//
					// .sourceRegion(Integer.parseInt(region.get("x")), //
					// Integer.parseInt(region.get("y")), //
					// Integer.parseInt(region.get("width")), //
					// Integer.parseInt(region.get("height")))
					.scale(region.get("scale"))//
					.asBufferedImage();
		}
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, fileExt.substring(1), os);
		is = new ByteArrayInputStream(os.toByteArray());

		// 6.上传
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd");
		String yyyyMMdd = sdf.format(new Date());
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		String savePath = yyyyMMdd + "/" + uuid + fileExt;
		String rootPath = PropertiesUtil.getProperty("app.upload.rootPath");
		logger.info("savePath:" + savePath);
		logger.info("imageRootPath:" + rootPath);
		dto = FileUploader.saveFileByInputStream(is, savePath, rootPath);
		return dto;
	}

	public static UploadResultDto video(MultipartFile file, String uuid, HttpServletRequest request)
			throws IOException {
		// TODO Auto-generated method stub
		UploadResultDto dto = new UploadResultDto();
		// 1、允许的扩展名
		List<String> allowedExt = new ArrayList<String>();
		allowedExt.add(".mp4");
		// 2、最大上传文件2G
		Long maxSize = 2l * 1024 * 1024 * 1024;

		// 3、文件基本信息
		String originalFileName = file.getOriginalFilename();
		Integer fileType = FileTypeMap.VIDEO;
		Long fileSize = file.getSize();
		String fileExt = FileUtil.getExt(originalFileName);
		String fileName = UUID.randomUUID() + fileExt;
		InputStream is = file.getInputStream();

		dto.setOriginalFileName(originalFileName);
		dto.setFileType(fileType);
		dto.setFileExt(fileExt);
		dto.setFileSize(fileSize);
		dto.setFileName(fileName);

		// 4.验证
		BaseResult<Object> baseResult1 = FileUtil.isAllowedExt(fileExt, allowedExt);
		if (!baseResult1.getResult()) {
			dto.setResult(false);
			dto.setMessage(baseResult1.getMessage());
			return dto;
		}

		BaseResult<Object> baseResult2 = FileUtil.isExceedingInSize(fileSize, maxSize);
		if (!baseResult2.getResult()) {
			dto.setResult(false);
			dto.setMessage(baseResult2.getMessage());
			return dto;
		}

		// 5.设置视频总大小
		HttpSession session = request.getSession();
		session.setAttribute(uuid + "totalBytes", fileSize);
		session.setAttribute(uuid + "transferredBytes", 0);
		// 6.上传
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd");
		String yyyyMMdd = sdf.format(new Date());
		String filenameUUID = UUID.randomUUID().toString().replaceAll("-", "");
		String savePath = "/video" + yyyyMMdd + "/" + filenameUUID + fileExt;
		String rootPath = PropertiesUtil.getProperty("app.upload.rootPath");
		logger.info("savePath:" + savePath);
		logger.info("imageRootPath:" + rootPath);
		dto = FileUploader.saveVideoByInputStream(is, savePath, rootPath, uuid, request);
		return dto;
	}
}
