
package au.gov.nsw.railcorp.rtta.refint.generated.gtfs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * The refGtfsPathStation type repesents a path station
 * 
 * <p>Java class for refGtfsPathStation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="refGtfsPathStation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="StationTSNID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ShortName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "refGtfsPathStation", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference")
public class RefGtfsPathStation {

    @XmlAttribute(name = "StationTSNID", required = true)
    protected String stationTSNID;
    @XmlAttribute(name = "ShortName", required = true)
    protected String shortName;

    /**
     * Gets the value of the stationTSNID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStationTSNID() {
        return stationTSNID;
    }

    /**
     * Sets the value of the stationTSNID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStationTSNID(String value) {
        this.stationTSNID = value;
    }

    /**
     * Gets the value of the shortName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Sets the value of the shortName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortName(String value) {
        this.shortName = value;
    }

}
