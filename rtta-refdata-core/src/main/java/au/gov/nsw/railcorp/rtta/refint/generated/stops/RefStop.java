
package au.gov.nsw.railcorp.rtta.refint.generated.stops;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * The refStop type represents a stop on the network
 * 
 * <p>Java class for refStop complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="refStop">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="StopId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="LongName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ParentStopId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Source" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="StationStop" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="Latitude" type="{http://www.w3.org/2001/XMLSchema}double" default="0.0" />
 *       &lt;attribute name="Longitude" type="{http://www.w3.org/2001/XMLSchema}double" default="0.0" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "refStop", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference")
public class RefStop {

    @XmlAttribute(name = "StopId", required = true)
    protected String stopId;
    @XmlAttribute(name = "Name", required = true)
    protected String name;
    @XmlAttribute(name = "LongName", required = true)
    protected String longName;
    @XmlAttribute(name = "ParentStopId")
    protected String parentStopId;
    @XmlAttribute(name = "Source", required = true)
    protected String source;
    @XmlAttribute(name = "StationStop", required = true)
    protected boolean stationStop;
    @XmlAttribute(name = "Latitude")
    protected Double latitude;
    @XmlAttribute(name = "Longitude")
    protected Double longitude;

    /**
     * Gets the value of the stopId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStopId() {
        return stopId;
    }

    /**
     * Sets the value of the stopId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStopId(String value) {
        this.stopId = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
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
     * Gets the value of the parentStopId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentStopId() {
        return parentStopId;
    }

    /**
     * Sets the value of the parentStopId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentStopId(String value) {
        this.parentStopId = value;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSource(String value) {
        this.source = value;
    }

    /**
     * Gets the value of the stationStop property.
     * 
     */
    public boolean isStationStop() {
        return stationStop;
    }

    /**
     * Sets the value of the stationStop property.
     * 
     */
    public void setStationStop(boolean value) {
        this.stationStop = value;
    }

    /**
     * Gets the value of the latitude property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public double getLatitude() {
        if (latitude == null) {
            return  0.0D;
        } else {
            return latitude;
        }
    }

    /**
     * Sets the value of the latitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setLatitude(Double value) {
        this.latitude = value;
    }

    /**
     * Gets the value of the longitude property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public double getLongitude() {
        if (longitude == null) {
            return  0.0D;
        } else {
            return longitude;
        }
    }

    /**
     * Sets the value of the longitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setLongitude(Double value) {
        this.longitude = value;
    }

}
