package ua.nure.usik.practice7.controller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import ua.nure.usik.practice7.constants.Constants;
import ua.nure.usik.practice7.constants.Names;
import ua.nure.usik.practice7.entity.Gun;
import ua.nure.usik.practice7.entity.Guns;
import ua.nure.usik.practice7.entity.TTC;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;


public class DOMController {

    private String xmlFileName;

    // main container
    private Guns guns;

    public DOMController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public Guns getGuns() {
        return guns;
    }

    /**
     * Parses XML document.
     *
     * @param validate If true validate XML document against its XML schema.
     */
    public void parse(boolean validate)
            throws ParserConfigurationException, SAXException, IOException {

        // obtain DOM parser
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        // set properties for Factory

        // XML document contains namespaces
        dbf.setNamespaceAware(true);

        // make parser validating
        if (validate) {
            // turn validation on
            dbf.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);

            // turn on xsd validation
            dbf.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
        }

        DocumentBuilder db = dbf.newDocumentBuilder();

        // set error handler
        db.setErrorHandler(new DefaultHandler() {
            @Override
            public void error(SAXParseException e) throws SAXException {
                // throw exception if XML document is NOT valid
                throw e;
            }
        });

        // parse XML document
        Document document = db.parse(xmlFileName);

        // get root element
        Element root = document.getDocumentElement();

        // create container
        guns = new Guns();

        // obtain guns nodes
        NodeList gunNodes = root
                .getElementsByTagName(Names.GUN);

        // process guns nodes
        for (int j = 0; j < gunNodes.getLength(); j++) {
            Gun question = getGun(gunNodes.item(j));
            // add gun to container
            guns.getGunList().add(question);
        }
    }

    /**
     * Extracts question object from the question XML node.
     *
     * @param qNode Question node.
     * @return Question object.
     */
    private static Gun getGun(Node qNode) {
        Gun gun = new Gun();
        Element qElement = (Element) qNode;

        // process model
        Node modNode = qElement.getElementsByTagName(Names.MODEL)
                .item(0);
        // set model
        gun.setModel(modNode.getTextContent());

        // process handy
        Node hanNode = qElement.getElementsByTagName(Names.HANDY)
                .item(0);
        // set handy
        gun.setHandy(hanNode.getTextContent());

        // process origin
        Node orNode = qElement.getElementsByTagName(Names.ORIGIN)
                .item(0);
        // set origin
        gun.setOrigin(orNode.getTextContent());

        // process material
        Node matNode = qElement.getElementsByTagName(Names.MATERIAL)
                .item(0);
        // set material
        gun.setMaterial(matNode.getTextContent());

        // process ttc
        TTC ttc = new TTC(getTTC(qElement.getElementsByTagName(Names.TTC)));
        // set ttc
        gun.setTtc(ttc);

        return gun;
    }

    /**
     * Extracts TTC object from the TTC XML node.
     *
     * @param nodeList Answer node.
     * @return TTC object.
     */
    private static TTC getTTC(NodeList nodeList) {
        TTC ttc = new TTC();
		Element ttcElement = (Element) nodeList.item(0);

        // process long range
        Node lrNode = ttcElement.getElementsByTagName(Names.LONG_RANGE).item(0);
        // set long range
        ttc.setLongRange(lrNode.getTextContent());

        // process sighting range
        Node srNode = ttcElement.getElementsByTagName(Names.SIGHTING_RANGE).item(0);
        // set sighting range
        ttc.setSightingRange(srNode.getTextContent());

        // process clip availability
        Node clipNode = ttcElement.getElementsByTagName(Names.CLIP_AVAILABILITY).item(0);
        // set clip availability
        ttc.setClipAvailability(clipNode.getTextContent());

        // process optics availability
        Node opticsNode = ttcElement.getElementsByTagName(Names.OPTICS_AVAILABILITY).item(0);
        // set optics availability
        ttc.setOpticsAvailability(opticsNode.getTextContent());

        return ttc;
    }

    // //////////////////////////////////////////////////////
    // Static util methods
    // //////////////////////////////////////////////////////

    /**
     * Creates and returns DOM of the Guns container.
     *
     * @param guns Guns object.
     * @throws ParserConfigurationException
     */
    public static Document getDocument(Guns guns) throws ParserConfigurationException {

        // obtain DOM parser
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        // set properties for Factory

        // XML document contains namespaces
        dbf.setNamespaceAware(true);

        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();

        // create root element
        Element root = document.createElement(Names.GUNS);

        // add root element
        document.appendChild(root);

        // add questions elements
        for (Gun gun : guns.getGunList()) {

            // add gun
            Element gElement = document.createElement(Names.GUN);
            root.appendChild(gElement);

            // add model
            Element modElement =
                    document.createElement(Names.MODEL);
            modElement.setTextContent(gun.getModel());
            gElement.appendChild(modElement);

            // add handy
            Element hElement =
                    document.createElement(Names.HANDY);
            hElement.setTextContent(gun.getHandy());
            gElement.appendChild(hElement);

            // add origin
            Element orElement =
                    document.createElement(Names.ORIGIN);
            orElement.setTextContent(gun.getOrigin());
            gElement.appendChild(orElement);

            // add material
            Element matElement =
                    document.createElement(Names.MATERIAL);
            matElement.setTextContent(gun.getMaterial());
            gElement.appendChild(matElement);

            // add ttc
            Element ttcElement = document.createElement(Names.TTC);

            // add long range
            Element lrElement = document.createElement(Names.LONG_RANGE);
            lrElement.setTextContent(gun.getTtc().getLongRange());
            ttcElement.appendChild(lrElement);

            // add sighting range
            Element srElement = document.createElement(Names.SIGHTING_RANGE);
            srElement.setTextContent(gun.getTtc().getSightingRange());
            ttcElement.appendChild(srElement);

            // add clip availability
            Element clipElement = document.createElement(Names.CLIP_AVAILABILITY);
            clipElement.setTextContent(gun.getTtc().getClipAvailability());
            ttcElement.appendChild(clipElement);

            // add optics availability
            Element optElement = document.createElement(Names.OPTICS_AVAILABILITY);
            optElement.setTextContent(gun.getTtc().getOpticsAvailability());
            ttcElement.appendChild(optElement);

            gElement.appendChild(ttcElement);
        }

        return document;
    }

    /**
     * Saves Guns object to XML file.
     *
     * @param guns        Guns object to be saved.
     * @param xmlFileName Output XML file name.
     */
    public static void saveToXML(Guns guns, String xmlFileName)
            throws ParserConfigurationException, TransformerException {
        // Test -> DOM -> XML
        saveToXML(getDocument(guns), xmlFileName);
    }

    /**
     * Save DOM to XML.
     *
     * @param document    DOM to be saved.
     * @param xmlFileName Output XML file name.
     */
    public static void saveToXML(Document document, String xmlFileName)
            throws TransformerException {

        StreamResult result = new StreamResult(new File(xmlFileName));

        // set up transformation
        TransformerFactory tf = TransformerFactory.newInstance();
        javax.xml.transform.Transformer t = tf.newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");

        // run transformation
        t.transform(new DOMSource(document), result);
    }

    public static void main(String[] args) throws Exception {

        // try to parse NOT valid XML document with validation on (failed)
        DOMController domContr = new DOMController(Constants.VALID_XML_FILE);
        try {
            // parse with validation (failed)
            domContr.parse(true);
        } catch (SAXException ex) {
            System.err.println("====================================");
            System.err.println("XML not valid");
            System.err.println("Guns object --> " + domContr.getGuns());
            System.err.println("====================================");
        }

        // try to parse NOT valid XML document with validation off (success)
        domContr.parse(false);

        // we have Guns object at this point:
        System.out.println("====================================");
        System.out.print("Here is the guns: \n" + domContr.getGuns());
        System.out.println("====================================");

        // save guns in XML file
        Guns guns = domContr.getGuns();
        DOMController.saveToXML(guns, Constants.VALID_XML_FILE + ".dom-result.xml");
    }
}
