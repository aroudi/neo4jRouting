
package au.gov.nsw.railcorp.rtta.refint.generated.gtfs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The refGtfsNetworks type represents networks
 * 
 * <p>Java class for refGtfsNetworks complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="refGtfsNetworks">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Network" type="{http://www.railcorp.nsw.gov.au/RTTA/reference}refGtfsNetwork" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "refGtfsNetworks", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference", propOrder = {
    "network"
})
public class RefGtfsNetworks {

    @XmlElement(name = "Network", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference")
    protected List<RefGtfsNetwork> network;

    /**
     * Gets the value of the network property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the network property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNetwork().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RefGtfsNetwork }
     * 
     * 
     */
    public List<RefGtfsNetwork> getNetwork() {
        if (network == null) {
            network = new ArrayList<RefGtfsNetwork>();
        }
        return this.network;
    }

}
