
package au.gov.nsw.railcorp.rtta.refint.generated.gtfs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The refGtfsLinePaths type represents a set of station paths
 * 
 * <p>Java class for refGtfsLinePaths complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="refGtfsLinePaths">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LinePath" type="{http://www.railcorp.nsw.gov.au/RTTA/reference}refGtfsLinePath" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "refGtfsLinePaths", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference", propOrder = {
    "linePath"
})
public class RefGtfsLinePaths {

    @XmlElement(name = "LinePath", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference")
    protected List<RefGtfsLinePath> linePath;

    /**
     * Gets the value of the linePath property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the linePath property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLinePath().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RefGtfsLinePath }
     * 
     * 
     */
    public List<RefGtfsLinePath> getLinePath() {
        if (linePath == null) {
            linePath = new ArrayList<RefGtfsLinePath>();
        }
        return this.linePath;
    }

}
