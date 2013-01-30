package XMLParse;

/**
 *  By Zhe Wei
 * 	Last Updated: 30/01/2012 2:22am
 * **/

//import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
//import org.dom4j.io.SAXReader;

public class xmlParser {
	
	// str is String in XML format
	public static String[][] unwrapXml(String str){
		String[][] result=null;
		
		try {
			Document document = DocumentHelper.parseText(str);
			Element root = document.getRootElement();

			List nodes = root.elements();
			
			int row = root.nodeCount()+1;   // number of rows in the table displayed (including field name)
											// nodeCount() only counts the number of direct children
			int col = 0;
			if(!nodes.isEmpty()){
				col = ((Element)(nodes.get(0))).nodeCount(); 
			}
			
			//System.out.println("row = " + row);
			//System.out.println("column = " + col);
			
			
			result = new String[row][col]; // create the displayed table
			
			// insert field names
			for(int i=0;i<col;i++){
				List elms = ((Element)(nodes.get(i))).elements();
				result[0][i]=((Element)(elms.get(i))).getQualifiedName();
				//System.out.println("result[0]["+i+"] = " + result[0][i]);
			}
			
			// insert values
			for(int i=1;i<row;i++){  
				List elms = ((Element)(nodes.get(i-1))).elements();
				//System.out.println("elms in loop size = "+elms.size());
				for(int j=0;j<col;j++){ 
					result[i][j]= ((Element)(elms.get(j))).getText();
					//System.out.println("result["+i+"]["+j+"] = " + result[i][j]);
				}	
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
/*	
	public static void main(String[] args){
		String text = "<persons><person><name>Alice</name><age>21</age></person><person><name>Bei</name><age>22</age></person><person><name>Caro</name><age>26</age></person></persons>";
		String[][] result = unwrapXml(text);
	}
*/
}
