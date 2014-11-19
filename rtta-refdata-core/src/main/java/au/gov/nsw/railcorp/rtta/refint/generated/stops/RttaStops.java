
package au.gov.nsw.railcorp.rtta.refint.generated.stops;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import au.gov.nsw.railcorp.rtta.refint.generated.reference.ReferenceHeader;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ReferenceHeader" type="{http://www.railcorp.nsw.gov.au/RTTA/reference}ReferenceHeader"/>
 *         &lt;element name="StopsV01" type="{http://www.railcorp.nsw.gov.au/RTTA/reference}StopsV01"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "referenceHeader",
    "stopsV01"
})
@XmlRootElement(name = "rttaStops", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference")
public class RttaStops {

    @XmlElement(name = "ReferenceHeader", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference", required = true)
    protected ReferenceHeader referenceHeader;
    @XmlElement(name = "StopsV01", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference", required = true)
    protected StopsV01 stopsV01;

    /**
     * Gets the value of the referenceHeader property.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceHeader }
     *     
     */
    public ReferenceHeader getReferenceHeader() {
        return referenceHeader;
    }

    /**
     * Sets the value of the referenceHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceHeader }
     *     
     */
    public void setReferenceHeader(ReferenceHeader value) {
        this.referenceHeader = value;
    }

    /**
     * Gets the value of the stopsV01 property.
     * 
     * @return
     *     possible object is
     *     {@link StopsV01 }
     *     
     */
    public StopsV01 getStopsV01() {
        return stopsV01;
    }

    /**
     * Sets the value of the stopsV01 property.
     * 
     * @param value
     *     allowed object is
     *     {@link StopsV01 }
     *     
     */
    public void setStopsV01(StopsV01 value) {
        this.stopsV01 = value;
    }

}
