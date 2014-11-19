
package au.gov.nsw.railcorp.rtta.refint.generated.stops;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The refStopLinks type represents the set of stop links used to augment links not present in nodal geography
 * 
 * <p>Java class for refStopLinks complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="refStopLinks">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Link" type="{http://www.railcorp.nsw.gov.au/RTTA/reference}refStopLink" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "refStopLinks", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference", propOrder = {
    "link"
})
public class RefStopLinks {

    @XmlElement(name = "Link", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference")
    protected List<RefStopLink> link;

    /**
     * Gets the value of the link property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the link property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLink().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RefStopLink }
     * 
     * 
     */
    public List<RefStopLink> getLink() {
        if (link == null) {
            link = new ArrayList<RefStopLink>();
        }
        return this.link;
    }

}
