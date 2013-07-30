package test;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class EncoderHandler {

	/**
	 * @param args the command line arguments
	 * @throws IOException
	 * @throws WriterException
	 */
	public static void main(String[] args) throws IOException, WriterException {
		EncoderHandler.qRCodeEncoder();
		EncoderHandler.code128Encoder();
	}

	public static void qRCodeEncoder() throws WriterException, IOException {
		String content = "�ҵĲ��Դ���!�㿴����û��?EMail[ test@gmail.com ]";

		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		// ָ�������ȼ�
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		// ָ�������ʽ
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 200, 200, hints);

		File file = new File("D:/QRCode.png");
		MatrixToImageWriter.writeToFile(bitMatrix, "png", file);

	}

	public static void code128Encoder() throws WriterException, IOException {
		String content = "1234567890";

		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		// ָ�������ȼ�
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		// ָ�������ʽ
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.CODE_128, 200, 200, hints);

		File file = new File("D:/QRCode1.png");
		MatrixToImageWriter.writeToFile(bitMatrix, "png", file);

	}

}