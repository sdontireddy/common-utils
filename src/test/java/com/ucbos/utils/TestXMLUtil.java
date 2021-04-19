package com.ucbos.utils;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Document;

import com.ucbos.utils.XMLUtil;

public class TestXMLUtil {

	@Test
	@Ignore
	public void testXMLNodeUpdatedValue() throws Exception {
		String filePath = "C://Users//sdontireddy//Desktop//Work//PO//Sample_PO.xml";

		String expression = "/OrderRequest/WebOrder/SiteID";
		String newValue = "99999";

		XMLUtil xmlUtil = XMLUtil.init(filePath);

		xmlUtil.updateDocument(xmlUtil.getDocument(), expression, newValue);

		assertThat(xmlUtil.readValue(xmlUtil.getDocument(), expression).toString(), containsString("99999"));
	}

	@Test
	@Ignore
	public void testXMLFileCreationWithUpdatedNodevalue() throws Exception {
		String filePath = "data//Sample_PO.xml";
		String newFilePath = "data//Sample_PO_Updated.xml";

		String expression = "/OrderRequest/OrderTaxRates/OrderTaxRate/TaxRate";
		String newValue = "111111";

		XMLUtil xmlUtil = XMLUtil.init(filePath);

		Document updatedDocument = xmlUtil.updateDocument(xmlUtil.getDocument(), expression, newValue);

		xmlUtil.createXMLFile(newFilePath, updatedDocument);
		File updatedFile = new File(newFilePath);
		assertTrue(updatedFile.exists());

		xmlUtil = XMLUtil.init(newFilePath);

		xmlUtil.updateDocument(xmlUtil.getDocument(), expression, newValue);

		assertEquals(xmlUtil.readValue(xmlUtil.getDocument(), expression).toString(), "111111");

	}
	
	@Test

	public void generateUpdatedDOXML() throws Exception {
		String filePath = "data//DO//Sample_DO.xml";
						   
		String newFilePath = "data//DO//Sample_DO_Updated.xml";

		String expression = "/tXML/Message/DistributionOrder/DistributionOrderId";
		String newValue = "111111";

		XMLUtil xmlUtil = XMLUtil.init(filePath);

		Document updatedDocument = xmlUtil.updateDocument(xmlUtil.getDocument(), expression, newValue);

		xmlUtil.createXMLFile(newFilePath, updatedDocument);
		File updatedFile = new File(newFilePath);
		assertTrue(updatedFile.exists());

		xmlUtil = XMLUtil.init(newFilePath);

		xmlUtil.updateDocument(xmlUtil.getDocument(), expression, newValue);

		assertEquals(xmlUtil.readValue(xmlUtil.getDocument(), expression).toString(), "111111");

	}


	
	
	@Test
	@Ignore
	public void testXMLFileAddNodevalue() throws Exception {
		String filePath = "data//Sample_PO.xml";
		String newFilePath = "data//Sample_PO_Updated.xml";

		

		XMLUtil xmlUtil = XMLUtil.init(filePath);

		xmlUtil.appendNewNode(xmlUtil.getDocument());

	}

}
