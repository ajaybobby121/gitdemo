package baseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class TestReportDriver{

	public void createWord(String Testcasename) {
		try {
			
		
		//creting a folder with currentdate
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy"); //-hh-mm
		String foldername = ft.format(dNow);
		//String foldername = "newFOLDER";
		//String foldername = System.getProperty("TestReportfolderName");

		String pathScreenShot = System.getProperty("user.dir") + "\\TestReport";
		String pathtestreport = System.getProperty("user.dir")
				+ "\\TestReport\\" + foldername;
		// creating a folder
		new File(pathtestreport).mkdir();

		//File f = new File(pathScreenShot);
//		int count = 0;
//		for (File file : f.listFiles()) {
//			if (file.isFile() && (file.getName().endsWith(".png"))) {
//				count++;
//			}
//		}

		try {

			XWPFDocument doc = new XWPFDocument();
			XWPFParagraph p = doc.createParagraph();
			XWPFRun xwpfRun = p.createRun();
			String[] IMageargs = { pathScreenShot + "\\1.png",
					pathScreenShot + "\\2.png", pathScreenShot + "\\3.png" };
			
			for (String imgFile : IMageargs) {
				int format = XWPFDocument.PICTURE_TYPE_JPEG;
				xwpfRun.setText(imgFile);
				xwpfRun.addBreak();
				xwpfRun.addPicture(new FileInputStream(imgFile), format,
						imgFile, Units.toEMU(200), Units.toEMU(200)); // 200x200//
																		// pixels
				// xwpfRun.addBreak(BreakType.PAGE);
			}
			FileOutputStream out = new FileOutputStream(pathtestreport+"\\"
					+ Testcasename + ".docx");
			doc.write(out);
			out.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
			
		
	
	} catch (Exception e) {
		// TODO: handle exception
	}
	
		
	}
}
