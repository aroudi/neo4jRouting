
package au.gov.nsw.railcorp.rtta.refint.generated.gtfs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The refGtfsNetworkLine type represents a network line, for example "Eastern Suburbs and Illawarra Line"
 * 
 * <p>Java class for refGtfsNetworkLine complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="refGtfsNetworkLine">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LinePaths" type="{http://www.railcorp.nsw.gov.au/RTTA/reference}refGtfsLinePaths"/>
 *       &lt;/sequence>
 *       &lt;attribute name="NetworkLineName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="LongName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="NetworkName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="eServiceType" use="required" type="{http://www.railcorp.nsw.gov.au/RTTA/reference}eGtfsServiceType" />
 *       &lt;attribute name="LineColourHex" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TextColourHex" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "refGtfsNetworkLine", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference", propOrder = {
    "linePaths"
})
public class RefGtfsNetworkLine {

    @XmlElement(name = "LinePaths", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference", required = true)
    protected RefGtfsLinePaths linePaths;
    @XmlAttribute(name = "NetworkLineName", required = true)
    protected String networkLineName;
    @XmlAttribute(name = "LongName", required = true)
    protected String longName;
    @XmlAttribute(name = "NetworkName", required = true)
    protected String networkName;
    @XmlAttribute(name = "eServiceType", required = true)
    protected EGtfsServiceType eServiceType;
    @XmlAttribute(name = "LineColourHex", required = true)
    protected String lineColourHex;
    @XmlAttribute(name = "TextColourHex", required = true)
    protected String textColourHex;

    /**
     * Gets the value of the linePaths property.
     * 
     * @return
     *     possible object is
     *     {@link RefGtfsLinePaths }
     *     
     */
    public RefGtfsLinePaths getLinePaths() {
        return linePaths;
    }

    /**
     * Sets the value of the linePaths property.
     * 
     * @param value
     *     allowed object is
     *     {@link RefGtfsLinePaths }
     *     
     */
    public void setLinePaths(RefGtfsLinePaths value) {
        this.linePaths = value;
    }

    /**
     * Gets the value of the networkLineName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetworkLineName() {
        return networkLineName;
    }

    /**
     * Sets the value of the networkLineName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetworkLineName(String value) {
        this.networkLineName = value;
    }

    /**
     * Gets the value of the longName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLongName() {
        return longName;
    }

    /**
     * Sets the value of the longName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLongName(String value) {
        this.longName = value;
    }

    /**
     * Gets the value of the networkName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetworkName() {
        return networkName;
    }

    /**
     * Sets the value of the networkName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetworkName(String value) {
        this.networkName = value;
    }

    /**
     * Gets the value of the eServiceType property.
     * 
     * @return
     *     possible object is
     *     {@link EGtfsServiceType }
     *     
     */
    public EGtfsServiceType getEServiceType() {
        return eServiceType;
    }

    /**
     * Sets the value of the eServiceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link EGtfsServiceType }
     *     
     */
    public void setEServiceType(EGtfsServiceType value) {
        this.eServiceType = value;
    }

    /**
     * Gets the value of the lineColourHex property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLineColourHex() {
        return lineColourHex;
    }

    /**
     * Sets the value of the lineColourHex property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLineColourHex(String value) {
        this.lineColourHex = value;
    }

    /**
     * Gets the value of the textColourHex property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextColourHex() {
        return textColourHex;
    }

    /**
     * Sets the value of the textColourHex property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextColourHex(String value) {
        this.textColourHex = value;
    }

}
