
package au.gov.nsw.railcorp.rtta.refint.generated.gtfs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The refGtfsNetworkLines type represents network lines
 * 
 * <p>Java class for refGtfsNetworkLines complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="refGtfsNetworkLines">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NetworkLine" type="{http://www.railcorp.nsw.gov.au/RTTA/reference}refGtfsNetworkLine" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "refGtfsNetworkLines", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference", propOrder = {
    "networkLine"
})
public class RefGtfsNetworkLines {

    @XmlElement(name = "NetworkLine", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference")
    protected List<RefGtfsNetworkLine> networkLine;

    /**
     * Gets the value of the networkLine property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the networkLine property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNetworkLine().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RefGtfsNetworkLine }
     * 
     * 
     */
    public List<RefGtfsNetworkLine> getNetworkLine() {
        if (networkLine == null) {
            networkLine = new ArrayList<RefGtfsNetworkLine>();
        }
        return this.networkLine;
    }

}
