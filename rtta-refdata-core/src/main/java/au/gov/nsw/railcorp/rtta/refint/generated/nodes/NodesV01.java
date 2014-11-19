
package au.gov.nsw.railcorp.rtta.refint.generated.nodes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NodesV01 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NodesV01">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Nodes" type="{http://www.railcorp.nsw.gov.au/RTTA/reference}refNodes"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NodesV01", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference", propOrder = {
    "nodes"
})
public class NodesV01 {

    @XmlElement(name = "Nodes", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference", required = true)
    protected RefNodes nodes;

    /**
     * Gets the value of the nodes property.
     * 
     * @return
     *     possible object is
     *     {@link RefNodes }
     *     
     */
    public RefNodes getNodes() {
        return nodes;
    }

    /**
     * Sets the value of the nodes property.
     * 
     * @param value
     *     allowed object is
     *     {@link RefNodes }
     *     
     */
    public void setNodes(RefNodes value) {
        this.nodes = value;
    }

}
