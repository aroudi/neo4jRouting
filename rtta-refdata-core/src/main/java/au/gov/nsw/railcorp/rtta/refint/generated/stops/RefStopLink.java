
package au.gov.nsw.railcorp.rtta.refint.generated.stops;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * The refStopLink type represents a linked triplet of stops
 * 
 * <p>Java class for refStopLink complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="refStopLink">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="InStopId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="StopId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="OutStopId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="InStopName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="StopName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="OutStopName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Reversible" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="Electric" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="Diesel" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "refStopLink", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference")
public class RefStopLink {

    @XmlAttribute(name = "InStopId")
    protected String inStopId;
    @XmlAttribute(name = "StopId", required = true)
    protected String stopId;
    @XmlAttribute(name = "OutStopId")
    protected String outStopId;
    @XmlAttribute(name = "InStopName")
    protected String inStopName;
    @XmlAttribute(name = "StopName", required = true)
    protected String stopName;
    @XmlAttribute(name = "OutStopName")
    protected String outStopName;
    @XmlAttribute(name = "Reversible", required = true)
    protected boolean reversible;
    @XmlAttribute(name = "Electric", required = true)
    protected boolean electric;
    @XmlAttribute(name = "Diesel", required = true)
    protected boolean diesel;

    /**
     * Gets the value of the inStopId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInStopId() {
        return inStopId;
    }

    /**
     * Sets the value of the inStopId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInStopId(String value) {
        this.inStopId = value;
    }

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
     * Gets the value of the outStopId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutStopId() {
        return outStopId;
    }

    /**
     * Sets the value of the outStopId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutStopId(String value) {
        this.outStopId = value;
    }

    /**
     * Gets the value of the inStopName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInStopName() {
        return inStopName;
    }

    /**
     * Sets the value of the inStopName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInStopName(String value) {
        this.inStopName = value;
    }

    /**
     * Gets the value of the stopName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStopName() {
        return stopName;
    }

    /**
     * Sets the value of the stopName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStopName(String value) {
        this.stopName = value;
    }

    /**
     * Gets the value of the outStopName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutStopName() {
        return outStopName;
    }

    /**
     * Sets the value of the outStopName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutStopName(String value) {
        this.outStopName = value;
    }

    /**
     * Gets the value of the reversible property.
     * 
     */
    public boolean isReversible() {
        return reversible;
    }

    /**
     * Sets the value of the reversible property.
     * 
     */
    public void setReversible(boolean value) {
        this.reversible = value;
    }

    /**
     * Gets the value of the electric property.
     * 
     */
    public boolean isElectric() {
        return electric;
    }

    /**
     * Sets the value of the electric property.
     * 
     */
    public void setElectric(boolean value) {
        this.electric = value;
    }

    /**
     * Gets the value of the diesel property.
     * 
     */
    public boolean isDiesel() {
        return diesel;
    }

    /**
     * Sets the value of the diesel property.
     * 
     */
    public void setDiesel(boolean value) {
        this.diesel = value;
    }

}
