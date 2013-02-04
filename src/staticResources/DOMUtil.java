package staticResources;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class DOMUtil {

	public static InputStream docToInputStream(Document doc)
	{
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer;
		ByteArrayInputStream bais = null;
		try {
			transformer = tFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			StreamResult result = new StreamResult(baos);
			transformer.transform(source, result); 
			bais = new ByteArrayInputStream(baos.toByteArray());
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bais;

	}

	public static Document inputStreamToDoc(InputStream is)
	{
		Document document = null;
		DocumentBuilderFactory builderFactory =
				DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = builderFactory.newDocumentBuilder();
			document = builder.parse(
					is);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();  
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return document;

	}

	public static Document buildSourceForTemplate(String dbname)
	{
		Document ret = null;
		DocumentBuilderFactory factory =
				DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder =
					factory.newDocumentBuilder();
			ret = builder.newDocument();  
			Element root = (Element) ret.createElement("database");
			root.appendChild(ret.createTextNode(dbname));
			ret.appendChild(root);
		} catch (ParserConfigurationException pce) {
			// Parser with specified options can't be built
			pce.printStackTrace();
		}
		return ret;
	}

	public static Document buildHibernateCFG(String dbname)
	{
		Document doc = null;
		javax.xml.transform.Source xmlSource =
				new javax.xml.transform.stream.StreamSource(docToInputStream(buildSourceForTemplate(dbname)));
		javax.xml.transform.Source xsltSource =
				new javax.xml.transform.stream.StreamSource("C:\\Users\\WBalasundaram\\workspace\\FinalVersion\\src\\src\\staticResources\\hibernatecfgtemplate.xsl");
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		javax.xml.transform.Result result =
				new javax.xml.transform.stream.StreamResult(baos);

		// create an instance of TransformerFactory
		javax.xml.transform.TransformerFactory transFact =
				javax.xml.transform.TransformerFactory.newInstance( );

		javax.xml.transform.Transformer trans;
		try {
			trans = transFact.newTransformer(xsltSource);
			trans.transform(xmlSource, result);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			doc = inputStreamToDoc(bais);
			
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return doc;
	}


}