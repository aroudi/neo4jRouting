
package au.gov.nsw.railcorp.rtta.refint.generated.gtfs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GtfsTopologyV01 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GtfsTopologyV01">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Networks" type="{http://www.railcorp.nsw.gov.au/RTTA/reference}refGtfsNetworks"/>
 *         &lt;element name="NetworkLines" type="{http://www.railcorp.nsw.gov.au/RTTA/reference}refGtfsNetworkLines"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GtfsTopologyV01", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference", propOrder = {
    "networks",
    "networkLines"
})
public class GtfsTopologyV01 {

    @XmlElement(name = "Networks", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference", required = true)
    protected RefGtfsNetworks networks;
    @XmlElement(name = "NetworkLines", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference", required = true)
    protected RefGtfsNetworkLines networkLines;

    /**
     * Gets the value of the networks property.
     * 
     * @return
     *     possible object is
     *     {@link RefGtfsNetworks }
     *     
     */
    public RefGtfsNetworks getNetworks() {
        return networks;
    }

    /**
     * Sets the value of the networks property.
     * 
     * @param value
     *     allowed object is
     *     {@link RefGtfsNetworks }
     *     
     */
    public void setNetworks(RefGtfsNetworks value) {
        this.networks = value;
    }

    /**
     * Gets the value of the networkLines property.
     * 
     * @return
     *     possible object is
     *     {@link RefGtfsNetworkLines }
     *     
     */
    public RefGtfsNetworkLines getNetworkLines() {
        return networkLines;
    }

    /**
     * Sets the value of the networkLines property.
     * 
     * @param value
     *     allowed object is
     *     {@link RefGtfsNetworkLines }
     *     
     */
    public void setNetworkLines(RefGtfsNetworkLines value) {
        this.networkLines = value;
    }

}
