package bimoku.extract.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Patternmatch_Douban {

	public static void main(String[] args) {
		String content = "作者: Zola, Emile 页数: 436 定价: $ 33.40 ISSN: 1672-4445  ";
		patternmatchContent(content);
	}

	public static String[] patternmatchContent(String content) {
		String[] paramcontent = new String[6];
		//出版社
		Pattern p1 = Pattern
				.compile("作者: [^/s]*[\u4e00-\u9fa5a-zA-Z]*[^/s]*[/s]*[a-zA-Z]*[\u4e00-\u9fa5a-zA-Z]*[/s]*[^/s]*出版社: ");
		Matcher m1 = p1.matcher(content);
		boolean result1 = m1.find();
		if (result1) {
			paramcontent[0] = m1.group(0).replaceAll("作者: ", "")
					.replaceAll("出版社: ", "").replaceAll("页数", "");
			System.out.println(paramcontent[0]);
			result1 = m1.find();
		}else{
			Pattern p11 = Pattern
					.compile("作者: [^/s]*[\u4e00-\u9fa5a-zA-Z]*[^/s]*[/s]*[a-zA-Z]*[\u4e00-\u9fa5a-zA-Z]*[/s]*[^/s]*页数");
			Matcher m11 = p11.matcher(content);
			boolean result11 = m11.find();
			if(result11){
				paramcontent[0] = m11.group(0).replaceAll("作者: ", "")
						.replaceAll("出版社: ", "").replaceAll("页数", "");
				System.out.println(paramcontent[0]);
				result11 = m11.find();
			}
		}

		//版次
		Pattern p2 = Pattern
				.compile("出版社: [/s]*[\u4e00-\u9fa5a-zA-Z]*[^/s]*[\u4e00-\u9fa5a-zA-Z]*[^/s]*[/s]*[\u4e00-\u9fa5a-zA-Z]*[/s]*[^/s]*副标题:");
		Matcher m2 = p2.matcher(content);
		boolean result2 = m2.find();
		if (result2) {
			paramcontent[1] = m2.group(0)
					.replaceAll("出版社: ", "")
					.replaceAll("出版年:", "")
					.replaceAll("译者:", "").replaceAll("副标题:", "");
			result2 = m2.find();
			paramcontent[1] = paramcontent[1].replaceAll("", "");
		//	System.out.println(paramcontent[1]);
		}else{
			Pattern p22 = Pattern
					.compile("出版社: [/s]*[\u4e00-\u9fa5a-zA-Z]*[^/s]*[\u4e00-\u9fa5a-zA-Z]*[^/s]*[/s]*[\u4e00-\u9fa5a-zA-Z]*[/s]*[^/s]*译者:");
			Matcher m22 = p22.matcher(content);
			boolean result3 = m22.find();
			
			if(result3){paramcontent[1] = m22.group(0)
					.replaceAll("出版社: ", "")
					.replaceAll("出版年:", "")
					.replaceAll("译者:", "").replaceAll("副标题:", "");
			result3 = m22.find();
			paramcontent[1] = paramcontent[1].replaceAll("", "");
			//System.out.println(paramcontent[1]);
			}else{
				Pattern p222 = Pattern
						.compile("出版社: [/s]*[\u4e00-\u9fa5a-zA-Z]*[^/s]*[\u4e00-\u9fa5a-zA-Z]*[^/s]*[/s]*[\u4e00-\u9fa5a-zA-Z]*[/s]*[^/s]*出版年:");
				Matcher m222 = p222.matcher(content);
				boolean result33 = m222.find();
				
				if(result33){paramcontent[1] = m222.group(0)
						.replaceAll("出版社: ", "")
						.replaceAll("出版年:", "")
						.replaceAll("译者:", "").replaceAll("副标题:", "");
				result33 = m222.find();
				paramcontent[1] = paramcontent[1].replaceAll("", "");
				System.out.println(paramcontent[1]);
			}
			}
		}
		
		
		Pattern p3 = Pattern.compile(
				"定价:[ ][/s]*[0123456789]+[.][0123456789]+",
				Pattern.CASE_INSENSITIVE);
		Matcher m3 = p3.matcher(content);
		boolean result3 = m3.find();
		if (result3) {
			paramcontent[2] = m3.group(0).replaceAll("定价: ", "").replaceAll(" $ ", "");
			result3 = m3.find();
			System.out.println(paramcontent[2]);
		}else{
			Pattern p33 = Pattern.compile(
					"定价: \\$ [/s]*[0123456789]+[.][0123456789]+",
					Pattern.CASE_INSENSITIVE);
			Matcher m33 = p33.matcher(content);
			boolean result33 = m33.find();
			if(result33){
				paramcontent[2] = m33.group(0).replaceAll("定价: ", "").replaceAll("\\$", "");
				result33 = m33.find();
				System.out.println(paramcontent[2]);
			}
		}
		
		
		Pattern p4 = Pattern.compile("ISBN: [/s]*[0123456789]+",
				Pattern.CASE_INSENSITIVE);
		Matcher m4 = p4.matcher(content);
		boolean result4 = m4.find();
		if (result4)
			{paramcontent[3] = m4.group(0).replaceAll("ISBN: ", "");
			//System.out.println(paramcontent[3]);
			result4 = m4.find();
		}else{
			Pattern p44 = Pattern.compile("ISSN: [/s]*[0123456789]+-[0123456789]+",
					Pattern.CASE_INSENSITIVE);
			Matcher m44 = p44.matcher(content);
			boolean result44 = m44.find();
			if(result44){
				{paramcontent[3] = m44.group(0).replaceAll("ISSN:", "");
				System.out.println(paramcontent[3]);
				result44 = m44.find();
			}
			}
		}
		
		
		Pattern p5 = Pattern.compile("译者: [/s]*[\u4e00-\u9fa5a-zA-Z]*[^/s]*[\u4e00-\u9fa5a-zA-Z]*[^/s]*[/s]*[\u4e00-\u9fa5a-zA-Z]*[/s]*[^/s]*出版年: ",
				Pattern.CASE_INSENSITIVE);
		Matcher m5 = p5.matcher(content);
		boolean result5 = m5.find();
		while (result5)
			{paramcontent[4] = m5.group(0).replaceAll("出版年: ", "")
			.replaceAll("译者:", "");
		//	System.out.println(paramcontent[4]);
			result5 = m5.find();
		}
		
		
		Pattern p6 = Pattern.compile("出版年: [/s]*[\u4e00-\u9fa5a-zA-Z]*[^/s]*[\u4e00-\u9fa5a-zA-Z]*[^/s]*[/s]*[\u4e00-\u9fa5a-zA-Z]*[/s]*[^/s]*页数:",
				Pattern.CASE_INSENSITIVE);
		Matcher m6 = p6.matcher(content);
		boolean result6 = m6.find();
		while (result6)
			{paramcontent[5] = m6.group(0).replaceAll("出版年: ", "")
			.replaceAll("页数:", "");
			//System.out.println(paramcontent[5]);
			result6 = m6.find();
		}
		return paramcontent;
	}
	public static String[] patternmatchAUT_TRANS(String content) {
		String[] paramcontent = new String[2];
		//出版社
		Pattern p1 = Pattern
				.compile("~ [^/s]*[\u4e00-\u9fa5]*[^/s]*[a-zA-Z]*[\u4e00-\u9fa5]*[/s]*[^/s]*");
		Matcher m1 = p1.matcher(content);
		
		boolean result1 = m1.find();
		while (result1) {
			String temp = m1.group(0);
			//System.out.println(temp);
			//temp.split(",");
			//System.out.println(temp.substring(0, temp.indexOf("(作者)")).trim());
			//paramcontent[0] = temp.replaceAll("(作者)", " ").replaceAll("~ |,", "");
			//System.out.println(paramcontent[0]);
			result1 = m1.find();
			paramcontent[0] = temp;
		}

		//版次
		Pattern p2 = Pattern
				.compile(";[\u4e00-\u9fa5]*[^/s]*[\u4e00-\u9fa5]*[^/s]*[\u4e00-\u9fa5]+[/s]*[^/s]*[平装丛书名精装外文书名语种]");
		Matcher m2 = p2.matcher(content);
		boolean result2 = m2.find();
		while (result2) {
			paramcontent[1] = m2.group(0)
					.replaceAll(";|平装[^/s]*[\u4e00-\u9fa5]*[^/s]*[\u4e00-\u9fa5]+[/s]*[^/s]*|丛书名[^/s]*[\u4e00-\u9fa5]*[^/s]*[\u4e00-\u9fa5]+[/s]*[^/s]*|精装[^/s]*[\u4e00-\u9fa5]*[^/s]*[\u4e00-\u9fa5]+[/s]*[^/s]*|外文书名[^/s]*[\u4e00-\u9fa5]*[^/s]*[\u4e00-\u9fa5]+[/s]*[^/s]*|语种[^/s]*[\u4e00-\u9fa5]*[^/s]*[\u4e00-\u9fa5]+[/s]*[^/s]*", " ")
					.replaceAll("", "");
			result2 = m2.find();
			paramcontent[1] = paramcontent[1].replaceAll("平装|丛书名|精装|外文书名|语种", "");
			System.out.println(paramcontent[1]);
		}
		

		return paramcontent;
	}
}
