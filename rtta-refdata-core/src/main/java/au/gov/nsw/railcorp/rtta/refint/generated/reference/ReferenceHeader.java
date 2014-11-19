
package au.gov.nsw.railcorp.rtta.refint.generated.reference;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReferenceHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReferenceHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Content" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContentVersion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContentManager" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Comment" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Sequence" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Date" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="HeaderVersion" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Owner" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReferenceHeader", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference", propOrder = {
    "content",
    "contentVersion",
    "contentManager",
    "description",
    "comment",
    "sequence",
    "date"
})
public class ReferenceHeader {

    @XmlElement(name = "Content", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference", required = true)
    protected String content;
    @XmlElement(name = "ContentVersion", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference", required = true)
    protected String contentVersion;
    @XmlElement(name = "ContentManager", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference", required = true)
    protected String contentManager;
    @XmlElement(name = "Description", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference", required = true)
    protected String description;
    @XmlElement(name = "Comment", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference", required = true)
    protected String comment;
    @XmlElement(name = "Sequence", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference", required = true)
    protected String sequence;
    @XmlElement(name = "Date", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference", required = true)
    protected String date;
    @XmlAttribute(name = "HeaderVersion", required = true)
    protected String headerVersion;
    @XmlAttribute(name = "Owner", required = true)
    protected String owner;

    /**
     * Gets the value of the content property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * Gets the value of the contentVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentVersion() {
        return contentVersion;
    }

    /**
     * Sets the value of the contentVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentVersion(String value) {
        this.contentVersion = value;
    }

    /**
     * Gets the value of the contentManager property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentManager() {
        return contentManager;
    }

    /**
     * Sets the value of the contentManager property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentManager(String value) {
        this.contentManager = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the comment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the value of the comment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComment(String value) {
        this.comment = value;
    }

    /**
     * Gets the value of the sequence property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSequence() {
        return sequence;
    }

    /**
     * Sets the value of the sequence property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSequence(String value) {
        this.sequence = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(String value) {
        this.date = value;
    }

    /**
     * Gets the value of the headerVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHeaderVersion() {
        return headerVersion;
    }

    /**
     * Sets the value of the headerVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHeaderVersion(String value) {
        this.headerVersion = value;
    }

    /**
     * Gets the value of the owner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the value of the owner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwner(String value) {
        this.owner = value;
    }

}
