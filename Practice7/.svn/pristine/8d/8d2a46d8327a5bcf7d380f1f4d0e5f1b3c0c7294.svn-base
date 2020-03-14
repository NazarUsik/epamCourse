package ua.nure.usik.practice7;

import ua.nure.usik.practice7.controller.DOMController;
import ua.nure.usik.practice7.controller.SAXController;
import ua.nure.usik.practice7.controller.STAXController;
import ua.nure.usik.practice7.entity.Guns;
import ua.nure.usik.practice7.util.Sorter;

public class Main {
    public static void usage() {
        System.out.println("java ua.nure.usik.practice7.Main xmlFileName");
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            usage();
            return;
        }

        String xmlFileName = args[0];
        System.out.println("Input ==> " + xmlFileName);

        ////////////////////////////////////////////////////////
        // DOM
        ////////////////////////////////////////////////////////

        // get
        DOMController domController = new DOMController(xmlFileName);
        domController.parse(true);
        Guns guns = domController.getGuns();

        // sort (case 1)
        Sorter.sortGunsByModel(guns);

        // save
        String outputXmlFile = "output.dom.xml";
        DOMController.saveToXML(guns, outputXmlFile);
        System.out.println("Output ==> " + outputXmlFile);

        ////////////////////////////////////////////////////////
        // SAX
        ////////////////////////////////////////////////////////

        // get
        SAXController saxController = new SAXController(xmlFileName);
        saxController.parse(true);
        guns = saxController.getGuns();

        // sort  (case 2)
        Sorter.sortGunsByOrigin(guns);

        // save
        outputXmlFile = "output.sax.xml";

        // other way:
        DOMController.saveToXML(guns, outputXmlFile);
        System.out.println("Output ==> " + outputXmlFile);

        ////////////////////////////////////////////////////////
        // StAX
        ////////////////////////////////////////////////////////

        // get
        STAXController staxController = new STAXController(xmlFileName);
        staxController.parse();
        guns = staxController.getGuns();

        // sort  (case 3)
        Sorter.sortGunsBySightingRange(guns);

        // save
        outputXmlFile = "output.stax.xml";
        DOMController.saveToXML(guns, outputXmlFile);
        System.out.println("Output ==> " + outputXmlFile);
    }
}
