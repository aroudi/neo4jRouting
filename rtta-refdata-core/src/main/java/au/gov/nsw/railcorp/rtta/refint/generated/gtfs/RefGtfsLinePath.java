
package au.gov.nsw.railcorp.rtta.refint.generated.gtfs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The refGtfsLinePath type represents a station path
 * 
 * <p>Java class for refGtfsLinePath complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="refGtfsLinePath">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Station" type="{http://www.railcorp.nsw.gov.au/RTTA/reference}refGtfsPathStation" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="LinePathName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="LongName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="InterchangePoints" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ElectricPath" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="DieselPath" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="StationPath" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="PathMatchInclude" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "refGtfsLinePath", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference", propOrder = {
    "station"
})
public class RefGtfsLinePath {

    @XmlElement(name = "Station", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference")
    protected List<RefGtfsPathStation> station;
    @XmlAttribute(name = "LinePathName", required = true)
    protected String linePathName;
    @XmlAttribute(name = "LongName", required = true)
    protected String longName;
    @XmlAttribute(name = "InterchangePoints")
    protected String interchangePoints;
    @XmlAttribute(name = "ElectricPath", required = true)
    protected boolean electricPath;
    @XmlAttribute(name = "DieselPath", required = true)
    protected boolean dieselPath;
    @XmlAttribute(name = "StationPath", required = true)
    protected String stationPath;
    @XmlAttribute(name = "PathMatchInclude")
    protected String pathMatchInclude;

    /**
     * Gets the value of the station property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the station property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RefGtfsPathStation }
     * 
     * 
     */
    public List<RefGtfsPathStation> getStation() {
        if (station == null) {
            station = new ArrayList<RefGtfsPathStation>();
        }
        return this.station;
    }

    /**
     * Gets the value of the linePathName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinePathName() {
        return linePathName;
    }

    /**
     * Sets the value of the linePathName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinePathName(String value) {
        this.linePathName = value;
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
     * Gets the value of the interchangePoints property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInterchangePoints() {
        return interchangePoints;
    }

    /**
     * Sets the value of the interchangePoints property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInterchangePoints(String value) {
        this.interchangePoints = value;
    }

    /**
     * Gets the value of the electricPath property.
     * 
     */
    public boolean isElectricPath() {
        return electricPath;
    }

    /**
     * Sets the value of the electricPath property.
     * 
     */
    public void setElectricPath(boolean value) {
        this.electricPath = value;
    }

    /**
     * Gets the value of the dieselPath property.
     * 
     */
    public boolean isDieselPath() {
        return dieselPath;
    }

    /**
     * Sets the value of the dieselPath property.
     * 
     */
    public void setDieselPath(boolean value) {
        this.dieselPath = value;
    }

    /**
     * Gets the value of the stationPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStationPath() {
        return stationPath;
    }

    /**
     * Sets the value of the stationPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStationPath(String value) {
        this.stationPath = value;
    }

    /**
     * Gets the value of the pathMatchInclude property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPathMatchInclude() {
        return pathMatchInclude;
    }

    /**
     * Sets the value of the pathMatchInclude property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPathMatchInclude(String value) {
        this.pathMatchInclude = value;
    }

}
