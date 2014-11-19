
package au.gov.nsw.railcorp.rtta.refint.generated.stops;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StopsV01 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StopsV01">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Stops" type="{http://www.railcorp.nsw.gov.au/RTTA/reference}refStops"/>
 *         &lt;element name="StopLinks" type="{http://www.railcorp.nsw.gov.au/RTTA/reference}refStopLinks"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StopsV01", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference", propOrder = {
    "stops",
    "stopLinks"
})
public class StopsV01 {

    @XmlElement(name = "Stops", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference", required = true)
    protected RefStops stops;
    @XmlElement(name = "StopLinks", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference", required = true)
    protected RefStopLinks stopLinks;

    /**
     * Gets the value of the stops property.
     * 
     * @return
     *     possible object is
     *     {@link RefStops }
     *     
     */
    public RefStops getStops() {
        return stops;
    }

    /**
     * Sets the value of the stops property.
     * 
     * @param value
     *     allowed object is
     *     {@link RefStops }
     *     
     */
    public void setStops(RefStops value) {
        this.stops = value;
    }

    /**
     * Gets the value of the stopLinks property.
     * 
     * @return
     *     possible object is
     *     {@link RefStopLinks }
     *     
     */
    public RefStopLinks getStopLinks() {
        return stopLinks;
    }

    /**
     * Sets the value of the stopLinks property.
     * 
     * @param value
     *     allowed object is
     *     {@link RefStopLinks }
     *     
     */
    public void setStopLinks(RefStopLinks value) {
        this.stopLinks = value;
    }

}
