package ua.nure.usik.practice7.controller;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ua.nure.usik.practice7.constants.Constants;
import ua.nure.usik.practice7.constants.Names;
import ua.nure.usik.practice7.entity.Gun;
import ua.nure.usik.practice7.entity.Guns;
import ua.nure.usik.practice7.entity.TTC;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;


public class SAXController extends DefaultHandler {

	private String xmlFileName;

	// current element name holder
	private String currentElement;

	// main container
	private Guns guns;

	private Gun gun;

	private TTC ttc;

	public SAXController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	/**
	 * Parses XML document.
	 *
	 * @param validate
	 *            If true validate XML document against its XML schema. With
	 *            this parameter it is possible make parser validating.
	 */
	public void parse(boolean validate)
			throws ParserConfigurationException, SAXException, IOException {

		// obtain sax parser factory
		SAXParserFactory factory = SAXParserFactory.newInstance();

		// XML document contains namespaces
		factory.setNamespaceAware(true);

		// set validation
		if (validate) {
			factory.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
			factory.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
		}

		SAXParser parser = factory.newSAXParser();
		parser.parse(xmlFileName, this);
	}

	// ///////////////////////////////////////////////////////////
	// ERROR HANDLER IMPLEMENTATION
	// ///////////////////////////////////////////////////////////

	@Override
	public void error(org.xml.sax.SAXParseException e) throws SAXException {
		// if XML document not valid just throw exception
		throw e;
	}

	public Guns getGuns() {
		return guns;
	}

	// ///////////////////////////////////////////////////////////
	// CONTENT HANDLER IMPLEMENTATION
	// ///////////////////////////////////////////////////////////


	@Override
	public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {

		currentElement = localName;

		if (Names.GUNS.equals(currentElement)) {
			guns = new Guns();
			return;
		}

		if (Names.GUN.equals(currentElement)) {
			gun = new Gun();
			return;
		}

		if (Names.TTC.equals(currentElement)) {
			ttc = new TTC();
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		String elementText = new String(ch, start, length).trim();

		// return if content is empty
		if (elementText.isEmpty()) {
			return;
		}

        if (Names.MODEL.equals(currentElement)) {
            gun.setModel(elementText);
            return;
        }

        if (Names.HANDY.equals(currentElement)) {
            gun.setHandy(elementText);
            return;
        }

        if (Names.ORIGIN.equals(currentElement)) {
            gun.setOrigin(elementText);
            return;
        }

        if (Names.MATERIAL.equals(currentElement)) {
            gun.setMaterial(elementText);
            return;
        }

        if (Names.LONG_RANGE.equals(currentElement)) {
            ttc.setLongRange(elementText);
            return;
        }

        if (Names.SIGHTING_RANGE.equals(currentElement)) {
            ttc.setSightingRange(elementText);
            return;
        }

        if (Names.CLIP_AVAILABILITY.equals(currentElement)) {
            ttc.setClipAvailability(elementText);
            return;
        }

        if (Names.OPTICS_AVAILABILITY.equals(currentElement)) {
            ttc.setOpticsAvailability(elementText);
            return;
        }
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (ttc != null) {
			gun.setTtc(ttc);
		}

		if (Names.GUN.equals(localName)) {
			// just add gun to container
			guns.getGunList().add(gun);
			return;
		}
	}

	public static void main(String[] args) throws Exception {

		// try to parse valid XML file (success)
		SAXController saxContr = new SAXController(Constants.VALID_XML_FILE);

		// do parse with validation on (success)
		saxContr.parse(true);

		// obtain container
		Guns guns = saxContr.getGuns();

		// we have Guns object at this point:
		System.out.println("====================================");
		System.out.print("Here is the guns: \n" + guns);
		System.out.println("====================================");

		// now try to parse NOT valid XML (failed)
		saxContr = new SAXController(Constants.INVALID_XML_FILE);
		try {
			// do parse with validation on (failed)
			saxContr.parse(true);
		} catch (Exception ex) {
			System.err.println("====================================");
			System.err.println("Validation is failed:\n" + ex.getMessage());
			System.err
					.println("Try to print guns object:" + saxContr.getGuns());
			System.err.println("====================================");
		}

		// and now try to parse NOT valid XML with validation off (success)
		saxContr.parse(false);

		// we have Guns object at this point:
		System.out.println("====================================");
		System.out.print("Here is the guns: \n" + saxContr.getGuns());
		System.out.println("====================================");
	}
}