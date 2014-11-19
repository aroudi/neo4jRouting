
package au.gov.nsw.railcorp.rtta.refint.generated.gtfs;

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
 *         &lt;element name="GtfsTopologyV01" type="{http://www.railcorp.nsw.gov.au/RTTA/reference}GtfsTopologyV01"/>
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
    "gtfsTopologyV01"
})
@XmlRootElement(name = "rttaGtfsTopology", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference")
public class RttaGtfsTopology {

    @XmlElement(name = "ReferenceHeader", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference", required = true)
    protected ReferenceHeader referenceHeader;
    @XmlElement(name = "GtfsTopologyV01", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference", required = true)
    protected GtfsTopologyV01 gtfsTopologyV01;

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
     * Gets the value of the gtfsTopologyV01 property.
     * 
     * @return
     *     possible object is
     *     {@link GtfsTopologyV01 }
     *     
     */
    public GtfsTopologyV01 getGtfsTopologyV01() {
        return gtfsTopologyV01;
    }

    /**
     * Sets the value of the gtfsTopologyV01 property.
     * 
     * @param value
     *     allowed object is
     *     {@link GtfsTopologyV01 }
     *     
     */
    public void setGtfsTopologyV01(GtfsTopologyV01 value) {
        this.gtfsTopologyV01 = value;
    }

}
