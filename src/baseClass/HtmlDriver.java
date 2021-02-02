package baseClass;

import java.util.List;

public class HtmlDriver {
	public static String convertHtml(List<List<String>> tabData){
		StringBuilder s = new StringBuilder();
		//Akshaya :change in code start
		s.append("<html> \n" + 
				 "<style> \n"+
				 "table {outline:2px solid black; font-family: arial, sans-serif; border-collapse: collapse; width:60%;font-size:12px;border-spacing: 2px;} \n"+
				 "th {font: bold 12px sans-serif;border: 1px solid;background-color:#0080FF;height:50px}"+
				 "td {font: 12px sans-serif;border: 1px solid;}"+
		 		 "tr:nth-child(odd){border-top:2px solid black;}\n"+
		         "tr:nth-child(even){border-bottom:2px solid black;}\n");
			//for (int i = 0; i < tabData.size(); i++) {
			//if(tabData.get(i).get(3).equals("Red")){
			//s.append("tr:nth-child("+(i+5)+"){background-color:#FFDAB9;} \n ");
			//}
			//else{
			s.append("tr{background-color:#bddca9;} \n ");
			
			
		//}
		s.append(".oval{height:7.5px;width:12.5px;border-radius:50%;background-color:green;display:inline-block;} \n"+
				 ".oval1{height:7.5px;width:12.5px;border-radius:50%;background-color:red;display:inline-block;} \n"+
				 ".oval2{height:7.5px;width:12.5px;border-radius:50%;background-color:grey;display:inline-block;} \n"+
				 "</style> \n"+
				 "<h3>");
		
		s.append(tabData.get(1).get(1));
		s.append("</h3> \n");
		s.append("<table> \n"+         
		          "<tr> \n"); 
		
		
		for (int i = 0; i < tabData.get(2).size(); i++) {
			s.append("<th>"+tabData.get(2).get(i)+"</th> \n");
		}
		s.append("</tr> \n");
		  
		for (int i = 3; i <tabData.size(); i++) {
			s.append("<tr> \n");
			for (int j = 0; j <tabData.get(0).size(); j++){
				if(tabData.get(i).get(j).equals("Pass")){
					s.append("<td>"+"<font color=\"Green\"><b>Pass</b></font>"+"</td>\n");
				}else if(tabData.get(i).get(j).equals("Fail")){
					s.append("<td>"+"<font color=\"Red\"><b>Fail</font></b>"+"</td>\n");
				}else if(tabData.get(i).get(j).equals("Grey")){
					s.append("<td>"+"<div class=\"oval2\"></div>"+"</td>\n");
				}else{
					s.append("<td>"+tabData.get(i).get(j)+"</td>\n");
				}
			}
			s.append("</tr> \n"+"\n");
		}
		//Akshaya : end of change in code
		s.append("</table>\n" + "</html> \n \n \n");
		//System.out.println(s.toString());
		return s.toString();
	}
}