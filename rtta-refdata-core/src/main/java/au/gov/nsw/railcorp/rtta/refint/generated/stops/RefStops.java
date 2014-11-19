
package au.gov.nsw.railcorp.rtta.refint.generated.stops;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The refStops type represents the stops on the network
 * 
 * <p>Java class for refStops complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="refStops">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Stop" type="{http://www.railcorp.nsw.gov.au/RTTA/reference}refStop" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "refStops", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference", propOrder = {
    "stop"
})
public class RefStops {

    @XmlElement(name = "Stop", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference")
    protected List<RefStop> stop;

    /**
     * Gets the value of the stop property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stop property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStop().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RefStop }
     * 
     * 
     */
    public List<RefStop> getStop() {
        if (stop == null) {
            stop = new ArrayList<RefStop>();
        }
        return this.stop;
    }

}
