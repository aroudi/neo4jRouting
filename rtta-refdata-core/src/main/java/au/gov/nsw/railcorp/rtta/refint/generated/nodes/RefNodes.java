
package au.gov.nsw.railcorp.rtta.refint.generated.nodes;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The refNodes type represents the nodes on the network
 * 
 * <p>Java class for refNodes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="refNodes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Node" type="{http://www.railcorp.nsw.gov.au/RTTA/reference}refNode" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "refNodes", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference", propOrder = {
    "node"
})
public class RefNodes {

    @XmlElement(name = "Node", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference")
    protected List<RefNode> node;

    /**
     * Gets the value of the node property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the node property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RefNode }
     * 
     * 
     */
    public List<RefNode> getNode() {
        if (node == null) {
            node = new ArrayList<RefNode>();
        }
        return this.node;
    }

}
