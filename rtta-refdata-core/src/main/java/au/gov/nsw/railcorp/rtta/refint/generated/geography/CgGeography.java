
package au.gov.nsw.railcorp.rtta.refint.generated.geography;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence maxOccurs="unbounded" minOccurs="0">
 *         &lt;element name="Geov10RC" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Nodes" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Node" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="NodeMasterTimingPoint" maxOccurs="unbounded" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;attribute name="NodeName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                       &lt;element name="NodeMasterJunction" maxOccurs="unbounded" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;attribute name="NodeName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                       &lt;element name="NodeTurnPenaltyBans" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="NodeTurnPenaltyBan" maxOccurs="unbounded" minOccurs="0">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;attribute name="FromNodeName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                                         &lt;attribute name="ToNodeName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                                         &lt;attribute name="Penalty" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                     &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="LongName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="PlatformName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="IsDummy" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                                     &lt;attribute name="IsJunction" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                                     &lt;attribute name="IsWorkingTimingPoint" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                                     &lt;attribute name="IsPublicTimingPoint" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                                     &lt;attribute name="IsEndOfLine" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                                     &lt;attribute name="DwellDuration" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *                                     &lt;attribute name="UpRecoveryDuration" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *                                     &lt;attribute name="DownRecoveryDuration" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *                                     &lt;attribute name="Length" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="SpeedBands" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="SpeedBand" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;attribute name="Id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="TrackSections" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="TrackSection" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;attribute name="Id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="IsUp" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                                     &lt;attribute name="IsPermissive" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Links" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Link" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="RunningTimes" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="RunningTime" maxOccurs="unbounded" minOccurs="0">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;attribute name="SBId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                                         &lt;attribute name="PP" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *                                                         &lt;attribute name="SS" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                     &lt;attribute name="FromNodeName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="ToNodeName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="Length" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *                                     &lt;attribute name="IsBusEnergy" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                                     &lt;attribute name="IsACEnergy" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                                     &lt;attribute name="IsDCEnergy" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                                     &lt;attribute name="IsDieselEnergy" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                                     &lt;attribute name="IsBusGauge" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                                     &lt;attribute name="IsNarrowGauge" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                                     &lt;attribute name="IsStandardGauge" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                                     &lt;attribute name="IsBroadGauge" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                                     &lt;attribute name="IsSiding" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                                     &lt;attribute name="IsCrossOver" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                                     &lt;attribute name="IsRunningLine" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                                     &lt;attribute name="TrackSectionId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Description" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="Owner" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="Date" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "geov10RC"
})
@XmlRootElement(name = "cgGeography", namespace = "http://www.contecint.com.au")
public class CgGeography {

    @XmlElement(name = "Geov10RC", namespace = "http://www.contecint.com.au")
    protected List<CgGeography.Geov10RC> geov10RC;

    /**
     * Gets the value of the geov10RC property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the geov10RC property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGeov10RC().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CgGeography.Geov10RC }
     * 
     * 
     */
    public List<CgGeography.Geov10RC> getGeov10RC() {
        if (geov10RC == null) {
            geov10RC = new ArrayList<CgGeography.Geov10RC>();
        }
        return this.geov10RC;
    }


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
     *         &lt;element name="Nodes" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Node" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="NodeMasterTimingPoint" maxOccurs="unbounded" minOccurs="0">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;attribute name="NodeName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                             &lt;element name="NodeMasterJunction" maxOccurs="unbounded" minOccurs="0">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;attribute name="NodeName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                             &lt;element name="NodeTurnPenaltyBans" minOccurs="0">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="NodeTurnPenaltyBan" maxOccurs="unbounded" minOccurs="0">
     *                                         &lt;complexType>
     *                                           &lt;complexContent>
     *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                               &lt;attribute name="FromNodeName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                                               &lt;attribute name="ToNodeName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                                               &lt;attribute name="Penalty" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
     *                                             &lt;/restriction>
     *                                           &lt;/complexContent>
     *                                         &lt;/complexType>
     *                                       &lt;/element>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                           &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="LongName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="PlatformName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="IsDummy" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *                           &lt;attribute name="IsJunction" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *                           &lt;attribute name="IsWorkingTimingPoint" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *                           &lt;attribute name="IsPublicTimingPoint" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *                           &lt;attribute name="IsEndOfLine" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *                           &lt;attribute name="DwellDuration" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
     *                           &lt;attribute name="UpRecoveryDuration" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
     *                           &lt;attribute name="DownRecoveryDuration" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
     *                           &lt;attribute name="Length" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="SpeedBands" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="SpeedBand" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;attribute name="Id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="TrackSections" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="TrackSection" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;attribute name="Id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="IsUp" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *                           &lt;attribute name="IsPermissive" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="Links" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Link" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="RunningTimes" minOccurs="0">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="RunningTime" maxOccurs="unbounded" minOccurs="0">
     *                                         &lt;complexType>
     *                                           &lt;complexContent>
     *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                               &lt;attribute name="SBId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                                               &lt;attribute name="PP" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
     *                                               &lt;attribute name="SS" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
     *                                             &lt;/restriction>
     *                                           &lt;/complexContent>
     *                                         &lt;/complexType>
     *                                       &lt;/element>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                           &lt;attribute name="FromNodeName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="ToNodeName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="Length" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
     *                           &lt;attribute name="IsBusEnergy" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *                           &lt;attribute name="IsACEnergy" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *                           &lt;attribute name="IsDCEnergy" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *                           &lt;attribute name="IsDieselEnergy" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *                           &lt;attribute name="IsBusGauge" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *                           &lt;attribute name="IsNarrowGauge" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *                           &lt;attribute name="IsStandardGauge" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *                           &lt;attribute name="IsBroadGauge" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *                           &lt;attribute name="IsSiding" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *                           &lt;attribute name="IsCrossOver" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *                           &lt;attribute name="IsRunningLine" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *                           &lt;attribute name="TrackSectionId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="Description" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="Owner" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="Date" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "nodes",
        "speedBands",
        "trackSections",
        "links"
    })
    public static class Geov10RC {

        @XmlElement(name = "Nodes", namespace = "http://www.contecint.com.au")
        protected CgGeography.Geov10RC.Nodes nodes;
        @XmlElement(name = "SpeedBands", namespace = "http://www.contecint.com.au")
        protected CgGeography.Geov10RC.SpeedBands speedBands;
        @XmlElement(name = "TrackSections", namespace = "http://www.contecint.com.au")
        protected CgGeography.Geov10RC.TrackSections trackSections;
        @XmlElement(name = "Links", namespace = "http://www.contecint.com.au")
        protected CgGeography.Geov10RC.Links links;
        @XmlAttribute(name = "Description", required = true)
        protected String description;
        @XmlAttribute(name = "Owner", required = true)
        protected String owner;
        @XmlAttribute(name = "Date", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar date;

        /**
         * Gets the value of the nodes property.
         * 
         * @return
         *     possible object is
         *     {@link CgGeography.Geov10RC.Nodes }
         *     
         */
        public CgGeography.Geov10RC.Nodes getNodes() {
            return nodes;
        }

        /**
         * Sets the value of the nodes property.
         * 
         * @param value
         *     allowed object is
         *     {@link CgGeography.Geov10RC.Nodes }
         *     
         */
        public void setNodes(CgGeography.Geov10RC.Nodes value) {
            this.nodes = value;
        }

        /**
         * Gets the value of the speedBands property.
         * 
         * @return
         *     possible object is
         *     {@link CgGeography.Geov10RC.SpeedBands }
         *     
         */
        public CgGeography.Geov10RC.SpeedBands getSpeedBands() {
            return speedBands;
        }

        /**
         * Sets the value of the speedBands property.
         * 
         * @param value
         *     allowed object is
         *     {@link CgGeography.Geov10RC.SpeedBands }
         *     
         */
        public void setSpeedBands(CgGeography.Geov10RC.SpeedBands value) {
            this.speedBands = value;
        }

        /**
         * Gets the value of the trackSections property.
         * 
         * @return
         *     possible object is
         *     {@link CgGeography.Geov10RC.TrackSections }
         *     
         */
        public CgGeography.Geov10RC.TrackSections getTrackSections() {
            return trackSections;
        }

        /**
         * Sets the value of the trackSections property.
         * 
         * @param value
         *     allowed object is
         *     {@link CgGeography.Geov10RC.TrackSections }
         *     
         */
        public void setTrackSections(CgGeography.Geov10RC.TrackSections value) {
            this.trackSections = value;
        }

        /**
         * Gets the value of the links property.
         * 
         * @return
         *     possible object is
         *     {@link CgGeography.Geov10RC.Links }
         *     
         */
        public CgGeography.Geov10RC.Links getLinks() {
            return links;
        }

        /**
         * Sets the value of the links property.
         * 
         * @param value
         *     allowed object is
         *     {@link CgGeography.Geov10RC.Links }
         *     
         */
        public void setLinks(CgGeography.Geov10RC.Links value) {
            this.links = value;
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

        /**
         * Gets the value of the date property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDate() {
            return date;
        }

        /**
         * Sets the value of the date property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDate(XMLGregorianCalendar value) {
            this.date = value;
        }


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
         *         &lt;element name="Link" maxOccurs="unbounded" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="RunningTimes" minOccurs="0">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="RunningTime" maxOccurs="unbounded" minOccurs="0">
         *                               &lt;complexType>
         *                                 &lt;complexContent>
         *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                                     &lt;attribute name="SBId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                                     &lt;attribute name="PP" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
         *                                     &lt;attribute name="SS" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
         *                                   &lt;/restriction>
         *                                 &lt;/complexContent>
         *                               &lt;/complexType>
         *                             &lt;/element>
         *                           &lt;/sequence>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *                 &lt;attribute name="FromNodeName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="ToNodeName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="Length" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
         *                 &lt;attribute name="IsBusEnergy" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *                 &lt;attribute name="IsACEnergy" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *                 &lt;attribute name="IsDCEnergy" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *                 &lt;attribute name="IsDieselEnergy" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *                 &lt;attribute name="IsBusGauge" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *                 &lt;attribute name="IsNarrowGauge" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *                 &lt;attribute name="IsStandardGauge" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *                 &lt;attribute name="IsBroadGauge" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *                 &lt;attribute name="IsSiding" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *                 &lt;attribute name="IsCrossOver" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *                 &lt;attribute name="IsRunningLine" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *                 &lt;attribute name="TrackSectionId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
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
            "link"
        })
        public static class Links {

            @XmlElement(name = "Link", namespace = "http://www.contecint.com.au")
            protected List<CgGeography.Geov10RC.Links.Link> link;

            /**
             * Gets the value of the link property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the link property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getLink().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link CgGeography.Geov10RC.Links.Link }
             * 
             * 
             */
            public List<CgGeography.Geov10RC.Links.Link> getLink() {
                if (link == null) {
                    link = new ArrayList<CgGeography.Geov10RC.Links.Link>();
                }
                return this.link;
            }


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
             *         &lt;element name="RunningTimes" minOccurs="0">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="RunningTime" maxOccurs="unbounded" minOccurs="0">
             *                     &lt;complexType>
             *                       &lt;complexContent>
             *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                           &lt;attribute name="SBId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *                           &lt;attribute name="PP" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
             *                           &lt;attribute name="SS" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
             *                         &lt;/restriction>
             *                       &lt;/complexContent>
             *                     &lt;/complexType>
             *                   &lt;/element>
             *                 &lt;/sequence>
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *       &lt;/sequence>
             *       &lt;attribute name="FromNodeName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="ToNodeName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="Length" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
             *       &lt;attribute name="IsBusEnergy" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
             *       &lt;attribute name="IsACEnergy" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
             *       &lt;attribute name="IsDCEnergy" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
             *       &lt;attribute name="IsDieselEnergy" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
             *       &lt;attribute name="IsBusGauge" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
             *       &lt;attribute name="IsNarrowGauge" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
             *       &lt;attribute name="IsStandardGauge" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
             *       &lt;attribute name="IsBroadGauge" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
             *       &lt;attribute name="IsSiding" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
             *       &lt;attribute name="IsCrossOver" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
             *       &lt;attribute name="IsRunningLine" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
             *       &lt;attribute name="TrackSectionId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "runningTimes"
            })
            public static class Link {

                @XmlElement(name = "RunningTimes", namespace = "http://www.contecint.com.au")
                protected CgGeography.Geov10RC.Links.Link.RunningTimes runningTimes;
                @XmlAttribute(name = "FromNodeName", required = true)
                protected String fromNodeName;
                @XmlAttribute(name = "ToNodeName", required = true)
                protected String toNodeName;
                @XmlAttribute(name = "Length", required = true)
                @XmlSchemaType(name = "unsignedInt")
                protected long length;
                @XmlAttribute(name = "IsBusEnergy", required = true)
                protected boolean isBusEnergy;
                @XmlAttribute(name = "IsACEnergy", required = true)
                protected boolean isACEnergy;
                @XmlAttribute(name = "IsDCEnergy", required = true)
                protected boolean isDCEnergy;
                @XmlAttribute(name = "IsDieselEnergy", required = true)
                protected boolean isDieselEnergy;
                @XmlAttribute(name = "IsBusGauge", required = true)
                protected boolean isBusGauge;
                @XmlAttribute(name = "IsNarrowGauge", required = true)
                protected boolean isNarrowGauge;
                @XmlAttribute(name = "IsStandardGauge", required = true)
                protected boolean isStandardGauge;
                @XmlAttribute(name = "IsBroadGauge", required = true)
                protected boolean isBroadGauge;
                @XmlAttribute(name = "IsSiding", required = true)
                protected boolean isSiding;
                @XmlAttribute(name = "IsCrossOver", required = true)
                protected boolean isCrossOver;
                @XmlAttribute(name = "IsRunningLine", required = true)
                protected boolean isRunningLine;
                @XmlAttribute(name = "TrackSectionId", required = true)
                protected String trackSectionId;

                /**
                 * Gets the value of the runningTimes property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link CgGeography.Geov10RC.Links.Link.RunningTimes }
                 *     
                 */
                public CgGeography.Geov10RC.Links.Link.RunningTimes getRunningTimes() {
                    return runningTimes;
                }

                /**
                 * Sets the value of the runningTimes property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link CgGeography.Geov10RC.Links.Link.RunningTimes }
                 *     
                 */
                public void setRunningTimes(CgGeography.Geov10RC.Links.Link.RunningTimes value) {
                    this.runningTimes = value;
                }

                /**
                 * Gets the value of the fromNodeName property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getFromNodeName() {
                    return fromNodeName;
                }

                /**
                 * Sets the value of the fromNodeName property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setFromNodeName(String value) {
                    this.fromNodeName = value;
                }

                /**
                 * Gets the value of the toNodeName property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getToNodeName() {
                    return toNodeName;
                }

                /**
                 * Sets the value of the toNodeName property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setToNodeName(String value) {
                    this.toNodeName = value;
                }

                /**
                 * Gets the value of the length property.
                 * 
                 */
                public long getLength() {
                    return length;
                }

                /**
                 * Sets the value of the length property.
                 * 
                 */
                public void setLength(long value) {
                    this.length = value;
                }

                /**
                 * Gets the value of the isBusEnergy property.
                 * 
                 */
                public boolean isIsBusEnergy() {
                    return isBusEnergy;
                }

                /**
                 * Sets the value of the isBusEnergy property.
                 * 
                 */
                public void setIsBusEnergy(boolean value) {
                    this.isBusEnergy = value;
                }

                /**
                 * Gets the value of the isACEnergy property.
                 * 
                 */
                public boolean isIsACEnergy() {
                    return isACEnergy;
                }

                /**
                 * Sets the value of the isACEnergy property.
                 * 
                 */
                public void setIsACEnergy(boolean value) {
                    this.isACEnergy = value;
                }

                /**
                 * Gets the value of the isDCEnergy property.
                 * 
                 */
                public boolean isIsDCEnergy() {
                    return isDCEnergy;
                }

                /**
                 * Sets the value of the isDCEnergy property.
                 * 
                 */
                public void setIsDCEnergy(boolean value) {
                    this.isDCEnergy = value;
                }

                /**
                 * Gets the value of the isDieselEnergy property.
                 * 
                 */
                public boolean isIsDieselEnergy() {
                    return isDieselEnergy;
                }

                /**
                 * Sets the value of the isDieselEnergy property.
                 * 
                 */
                public void setIsDieselEnergy(boolean value) {
                    this.isDieselEnergy = value;
                }

                /**
                 * Gets the value of the isBusGauge property.
                 * 
                 */
                public boolean isIsBusGauge() {
                    return isBusGauge;
                }

                /**
                 * Sets the value of the isBusGauge property.
                 * 
                 */
                public void setIsBusGauge(boolean value) {
                    this.isBusGauge = value;
                }

                /**
                 * Gets the value of the isNarrowGauge property.
                 * 
                 */
                public boolean isIsNarrowGauge() {
                    return isNarrowGauge;
                }

                /**
                 * Sets the value of the isNarrowGauge property.
                 * 
                 */
                public void setIsNarrowGauge(boolean value) {
                    this.isNarrowGauge = value;
                }

                /**
                 * Gets the value of the isStandardGauge property.
                 * 
                 */
                public boolean isIsStandardGauge() {
                    return isStandardGauge;
                }

                /**
                 * Sets the value of the isStandardGauge property.
                 * 
                 */
                public void setIsStandardGauge(boolean value) {
                    this.isStandardGauge = value;
                }

                /**
                 * Gets the value of the isBroadGauge property.
                 * 
                 */
                public boolean isIsBroadGauge() {
                    return isBroadGauge;
                }

                /**
                 * Sets the value of the isBroadGauge property.
                 * 
                 */
                public void setIsBroadGauge(boolean value) {
                    this.isBroadGauge = value;
                }

                /**
                 * Gets the value of the isSiding property.
                 * 
                 */
                public boolean isIsSiding() {
                    return isSiding;
                }

                /**
                 * Sets the value of the isSiding property.
                 * 
                 */
                public void setIsSiding(boolean value) {
                    this.isSiding = value;
                }

                /**
                 * Gets the value of the isCrossOver property.
                 * 
                 */
                public boolean isIsCrossOver() {
                    return isCrossOver;
                }

                /**
                 * Sets the value of the isCrossOver property.
                 * 
                 */
                public void setIsCrossOver(boolean value) {
                    this.isCrossOver = value;
                }

                /**
                 * Gets the value of the isRunningLine property.
                 * 
                 */
                public boolean isIsRunningLine() {
                    return isRunningLine;
                }

                /**
                 * Sets the value of the isRunningLine property.
                 * 
                 */
                public void setIsRunningLine(boolean value) {
                    this.isRunningLine = value;
                }

                /**
                 * Gets the value of the trackSectionId property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getTrackSectionId() {
                    return trackSectionId;
                }

                /**
                 * Sets the value of the trackSectionId property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setTrackSectionId(String value) {
                    this.trackSectionId = value;
                }


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
                 *         &lt;element name="RunningTime" maxOccurs="unbounded" minOccurs="0">
                 *           &lt;complexType>
                 *             &lt;complexContent>
                 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                 &lt;attribute name="SBId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *                 &lt;attribute name="PP" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
                 *                 &lt;attribute name="SS" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
                 *               &lt;/restriction>
                 *             &lt;/complexContent>
                 *           &lt;/complexType>
                 *         &lt;/element>
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
                    "runningTime"
                })
                public static class RunningTimes {

                    @XmlElement(name = "RunningTime", namespace = "http://www.contecint.com.au")
                    protected List<CgGeography.Geov10RC.Links.Link.RunningTimes.RunningTime> runningTime;

                    /**
                     * Gets the value of the runningTime property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list,
                     * not a snapshot. Therefore any modification you make to the
                     * returned list will be present inside the JAXB object.
                     * This is why there is not a <CODE>set</CODE> method for the runningTime property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * <pre>
                     *    getRunningTime().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list
                     * {@link CgGeography.Geov10RC.Links.Link.RunningTimes.RunningTime }
                     * 
                     * 
                     */
                    public List<CgGeography.Geov10RC.Links.Link.RunningTimes.RunningTime> getRunningTime() {
                        if (runningTime == null) {
                            runningTime = new ArrayList<CgGeography.Geov10RC.Links.Link.RunningTimes.RunningTime>();
                        }
                        return this.runningTime;
                    }


                    /**
                     * <p>Java class for anonymous complex type.
                     * 
                     * <p>The following schema fragment specifies the expected content contained within this class.
                     * 
                     * <pre>
                     * &lt;complexType>
                     *   &lt;complexContent>
                     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                     *       &lt;attribute name="SBId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
                     *       &lt;attribute name="PP" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
                     *       &lt;attribute name="SS" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
                     *     &lt;/restriction>
                     *   &lt;/complexContent>
                     * &lt;/complexType>
                     * </pre>
                     * 
                     * 
                     */
                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "")
                    public static class RunningTime {

                        @XmlAttribute(name = "SBId", required = true)
                        protected String sbId;
                        @XmlAttribute(name = "PP", required = true)
                        protected Duration pp;
                        @XmlAttribute(name = "SS", required = true)
                        protected Duration ss;

                        /**
                         * Gets the value of the sbId property.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getSBId() {
                            return sbId;
                        }

                        /**
                         * Sets the value of the sbId property.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setSBId(String value) {
                            this.sbId = value;
                        }

                        /**
                         * Gets the value of the pp property.
                         * 
                         * @return
                         *     possible object is
                         *     {@link Duration }
                         *     
                         */
                        public Duration getPP() {
                            return pp;
                        }

                        /**
                         * Sets the value of the pp property.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link Duration }
                         *     
                         */
                        public void setPP(Duration value) {
                            this.pp = value;
                        }

                        /**
                         * Gets the value of the ss property.
                         * 
                         * @return
                         *     possible object is
                         *     {@link Duration }
                         *     
                         */
                        public Duration getSS() {
                            return ss;
                        }

                        /**
                         * Sets the value of the ss property.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link Duration }
                         *     
                         */
                        public void setSS(Duration value) {
                            this.ss = value;
                        }

                    }

                }

            }

        }


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
         *         &lt;element name="Node" maxOccurs="unbounded" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="NodeMasterTimingPoint" maxOccurs="unbounded" minOccurs="0">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;attribute name="NodeName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                   &lt;element name="NodeMasterJunction" maxOccurs="unbounded" minOccurs="0">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;attribute name="NodeName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                   &lt;element name="NodeTurnPenaltyBans" minOccurs="0">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="NodeTurnPenaltyBan" maxOccurs="unbounded" minOccurs="0">
         *                               &lt;complexType>
         *                                 &lt;complexContent>
         *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                                     &lt;attribute name="FromNodeName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                                     &lt;attribute name="ToNodeName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                                     &lt;attribute name="Penalty" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
         *                                   &lt;/restriction>
         *                                 &lt;/complexContent>
         *                               &lt;/complexType>
         *                             &lt;/element>
         *                           &lt;/sequence>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *                 &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="LongName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="PlatformName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="IsDummy" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *                 &lt;attribute name="IsJunction" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *                 &lt;attribute name="IsWorkingTimingPoint" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *                 &lt;attribute name="IsPublicTimingPoint" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *                 &lt;attribute name="IsEndOfLine" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *                 &lt;attribute name="DwellDuration" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
         *                 &lt;attribute name="UpRecoveryDuration" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
         *                 &lt;attribute name="DownRecoveryDuration" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
         *                 &lt;attribute name="Length" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
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
            "node"
        })
        public static class Nodes {

            @XmlElement(name = "Node", namespace = "http://www.contecint.com.au")
            protected List<CgGeography.Geov10RC.Nodes.Node> node;

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
             * {@link CgGeography.Geov10RC.Nodes.Node }
             * 
             * 
             */
            public List<CgGeography.Geov10RC.Nodes.Node> getNode() {
                if (node == null) {
                    node = new ArrayList<CgGeography.Geov10RC.Nodes.Node>();
                }
                return this.node;
            }


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
             *         &lt;element name="NodeMasterTimingPoint" maxOccurs="unbounded" minOccurs="0">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;attribute name="NodeName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *         &lt;element name="NodeMasterJunction" maxOccurs="unbounded" minOccurs="0">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;attribute name="NodeName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *         &lt;element name="NodeTurnPenaltyBans" minOccurs="0">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="NodeTurnPenaltyBan" maxOccurs="unbounded" minOccurs="0">
             *                     &lt;complexType>
             *                       &lt;complexContent>
             *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                           &lt;attribute name="FromNodeName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *                           &lt;attribute name="ToNodeName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *                           &lt;attribute name="Penalty" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
             *                         &lt;/restriction>
             *                       &lt;/complexContent>
             *                     &lt;/complexType>
             *                   &lt;/element>
             *                 &lt;/sequence>
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *       &lt;/sequence>
             *       &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="LongName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="PlatformName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="IsDummy" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
             *       &lt;attribute name="IsJunction" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
             *       &lt;attribute name="IsWorkingTimingPoint" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
             *       &lt;attribute name="IsPublicTimingPoint" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
             *       &lt;attribute name="IsEndOfLine" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
             *       &lt;attribute name="DwellDuration" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
             *       &lt;attribute name="UpRecoveryDuration" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
             *       &lt;attribute name="DownRecoveryDuration" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
             *       &lt;attribute name="Length" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "nodeMasterTimingPoint",
                "nodeMasterJunction",
                "nodeTurnPenaltyBans"
            })
            public static class Node {

                @XmlElement(name = "NodeMasterTimingPoint", namespace = "http://www.contecint.com.au")
                protected List<CgGeography.Geov10RC.Nodes.Node.NodeMasterTimingPoint> nodeMasterTimingPoint;
                @XmlElement(name = "NodeMasterJunction", namespace = "http://www.contecint.com.au")
                protected List<CgGeography.Geov10RC.Nodes.Node.NodeMasterJunction> nodeMasterJunction;
                @XmlElement(name = "NodeTurnPenaltyBans", namespace = "http://www.contecint.com.au")
                protected CgGeography.Geov10RC.Nodes.Node.NodeTurnPenaltyBans nodeTurnPenaltyBans;
                @XmlAttribute(name = "Name", required = true)
                protected String name;
                @XmlAttribute(name = "LongName", required = true)
                protected String longName;
                @XmlAttribute(name = "PlatformName", required = true)
                protected String platformName;
                @XmlAttribute(name = "IsDummy", required = true)
                protected boolean isDummy;
                @XmlAttribute(name = "IsJunction", required = true)
                protected boolean isJunction;
                @XmlAttribute(name = "IsWorkingTimingPoint", required = true)
                protected boolean isWorkingTimingPoint;
                @XmlAttribute(name = "IsPublicTimingPoint", required = true)
                protected boolean isPublicTimingPoint;
                @XmlAttribute(name = "IsEndOfLine", required = true)
                protected boolean isEndOfLine;
                @XmlAttribute(name = "DwellDuration", required = true)
                protected Duration dwellDuration;
                @XmlAttribute(name = "UpRecoveryDuration", required = true)
                protected Duration upRecoveryDuration;
                @XmlAttribute(name = "DownRecoveryDuration", required = true)
                protected Duration downRecoveryDuration;
                @XmlAttribute(name = "Length", required = true)
                @XmlSchemaType(name = "unsignedInt")
                protected long length;

                /**
                 * Gets the value of the nodeMasterTimingPoint property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the nodeMasterTimingPoint property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getNodeMasterTimingPoint().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link CgGeography.Geov10RC.Nodes.Node.NodeMasterTimingPoint }
                 * 
                 * 
                 */
                public List<CgGeography.Geov10RC.Nodes.Node.NodeMasterTimingPoint> getNodeMasterTimingPoint() {
                    if (nodeMasterTimingPoint == null) {
                        nodeMasterTimingPoint = new ArrayList<CgGeography.Geov10RC.Nodes.Node.NodeMasterTimingPoint>();
                    }
                    return this.nodeMasterTimingPoint;
                }

                /**
                 * Gets the value of the nodeMasterJunction property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the nodeMasterJunction property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getNodeMasterJunction().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link CgGeography.Geov10RC.Nodes.Node.NodeMasterJunction }
                 * 
                 * 
                 */
                public List<CgGeography.Geov10RC.Nodes.Node.NodeMasterJunction> getNodeMasterJunction() {
                    if (nodeMasterJunction == null) {
                        nodeMasterJunction = new ArrayList<CgGeography.Geov10RC.Nodes.Node.NodeMasterJunction>();
                    }
                    return this.nodeMasterJunction;
                }

                /**
                 * Gets the value of the nodeTurnPenaltyBans property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link CgGeography.Geov10RC.Nodes.Node.NodeTurnPenaltyBans }
                 *     
                 */
                public CgGeography.Geov10RC.Nodes.Node.NodeTurnPenaltyBans getNodeTurnPenaltyBans() {
                    return nodeTurnPenaltyBans;
                }

                /**
                 * Sets the value of the nodeTurnPenaltyBans property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link CgGeography.Geov10RC.Nodes.Node.NodeTurnPenaltyBans }
                 *     
                 */
                public void setNodeTurnPenaltyBans(CgGeography.Geov10RC.Nodes.Node.NodeTurnPenaltyBans value) {
                    this.nodeTurnPenaltyBans = value;
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
                 * Gets the value of the platformName property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getPlatformName() {
                    return platformName;
                }

                /**
                 * Sets the value of the platformName property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setPlatformName(String value) {
                    this.platformName = value;
                }

                /**
                 * Gets the value of the isDummy property.
                 * 
                 */
                public boolean isIsDummy() {
                    return isDummy;
                }

                /**
                 * Sets the value of the isDummy property.
                 * 
                 */
                public void setIsDummy(boolean value) {
                    this.isDummy = value;
                }

                /**
                 * Gets the value of the isJunction property.
                 * 
                 */
                public boolean isIsJunction() {
                    return isJunction;
                }

                /**
                 * Sets the value of the isJunction property.
                 * 
                 */
                public void setIsJunction(boolean value) {
                    this.isJunction = value;
                }

                /**
                 * Gets the value of the isWorkingTimingPoint property.
                 * 
                 */
                public boolean isIsWorkingTimingPoint() {
                    return isWorkingTimingPoint;
                }

                /**
                 * Sets the value of the isWorkingTimingPoint property.
                 * 
                 */
                public void setIsWorkingTimingPoint(boolean value) {
                    this.isWorkingTimingPoint = value;
                }

                /**
                 * Gets the value of the isPublicTimingPoint property.
                 * 
                 */
                public boolean isIsPublicTimingPoint() {
                    return isPublicTimingPoint;
                }

                /**
                 * Sets the value of the isPublicTimingPoint property.
                 * 
                 */
                public void setIsPublicTimingPoint(boolean value) {
                    this.isPublicTimingPoint = value;
                }

                /**
                 * Gets the value of the isEndOfLine property.
                 * 
                 */
                public boolean isIsEndOfLine() {
                    return isEndOfLine;
                }

                /**
                 * Sets the value of the isEndOfLine property.
                 * 
                 */
                public void setIsEndOfLine(boolean value) {
                    this.isEndOfLine = value;
                }

                /**
                 * Gets the value of the dwellDuration property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Duration }
                 *     
                 */
                public Duration getDwellDuration() {
                    return dwellDuration;
                }

                /**
                 * Sets the value of the dwellDuration property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Duration }
                 *     
                 */
                public void setDwellDuration(Duration value) {
                    this.dwellDuration = value;
                }

                /**
                 * Gets the value of the upRecoveryDuration property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Duration }
                 *     
                 */
                public Duration getUpRecoveryDuration() {
                    return upRecoveryDuration;
                }

                /**
                 * Sets the value of the upRecoveryDuration property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Duration }
                 *     
                 */
                public void setUpRecoveryDuration(Duration value) {
                    this.upRecoveryDuration = value;
                }

                /**
                 * Gets the value of the downRecoveryDuration property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Duration }
                 *     
                 */
                public Duration getDownRecoveryDuration() {
                    return downRecoveryDuration;
                }

                /**
                 * Sets the value of the downRecoveryDuration property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Duration }
                 *     
                 */
                public void setDownRecoveryDuration(Duration value) {
                    this.downRecoveryDuration = value;
                }

                /**
                 * Gets the value of the length property.
                 * 
                 */
                public long getLength() {
                    return length;
                }

                /**
                 * Sets the value of the length property.
                 * 
                 */
                public void setLength(long value) {
                    this.length = value;
                }


                /**
                 * <p>Java class for anonymous complex type.
                 * 
                 * <p>The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;complexContent>
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *       &lt;attribute name="NodeName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *     &lt;/restriction>
                 *   &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class NodeMasterJunction {

                    @XmlAttribute(name = "NodeName", required = true)
                    protected String nodeName;

                    /**
                     * Gets the value of the nodeName property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getNodeName() {
                        return nodeName;
                    }

                    /**
                     * Sets the value of the nodeName property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setNodeName(String value) {
                        this.nodeName = value;
                    }

                }


                /**
                 * <p>Java class for anonymous complex type.
                 * 
                 * <p>The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;complexContent>
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *       &lt;attribute name="NodeName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *     &lt;/restriction>
                 *   &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class NodeMasterTimingPoint {

                    @XmlAttribute(name = "NodeName", required = true)
                    protected String nodeName;

                    /**
                     * Gets the value of the nodeName property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getNodeName() {
                        return nodeName;
                    }

                    /**
                     * Sets the value of the nodeName property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setNodeName(String value) {
                        this.nodeName = value;
                    }

                }


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
                 *         &lt;element name="NodeTurnPenaltyBan" maxOccurs="unbounded" minOccurs="0">
                 *           &lt;complexType>
                 *             &lt;complexContent>
                 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                 &lt;attribute name="FromNodeName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *                 &lt;attribute name="ToNodeName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *                 &lt;attribute name="Penalty" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
                 *               &lt;/restriction>
                 *             &lt;/complexContent>
                 *           &lt;/complexType>
                 *         &lt;/element>
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
                    "nodeTurnPenaltyBan"
                })
                public static class NodeTurnPenaltyBans {

                    @XmlElement(name = "NodeTurnPenaltyBan", namespace = "http://www.contecint.com.au")
                    protected List<CgGeography.Geov10RC.Nodes.Node.NodeTurnPenaltyBans.NodeTurnPenaltyBan> nodeTurnPenaltyBan;

                    /**
                     * Gets the value of the nodeTurnPenaltyBan property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list,
                     * not a snapshot. Therefore any modification you make to the
                     * returned list will be present inside the JAXB object.
                     * This is why there is not a <CODE>set</CODE> method for the nodeTurnPenaltyBan property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * <pre>
                     *    getNodeTurnPenaltyBan().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list
                     * {@link CgGeography.Geov10RC.Nodes.Node.NodeTurnPenaltyBans.NodeTurnPenaltyBan }
                     * 
                     * 
                     */
                    public List<CgGeography.Geov10RC.Nodes.Node.NodeTurnPenaltyBans.NodeTurnPenaltyBan> getNodeTurnPenaltyBan() {
                        if (nodeTurnPenaltyBan == null) {
                            nodeTurnPenaltyBan = new ArrayList<CgGeography.Geov10RC.Nodes.Node.NodeTurnPenaltyBans.NodeTurnPenaltyBan>();
                        }
                        return this.nodeTurnPenaltyBan;
                    }


                    /**
                     * <p>Java class for anonymous complex type.
                     * 
                     * <p>The following schema fragment specifies the expected content contained within this class.
                     * 
                     * <pre>
                     * &lt;complexType>
                     *   &lt;complexContent>
                     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                     *       &lt;attribute name="FromNodeName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
                     *       &lt;attribute name="ToNodeName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
                     *       &lt;attribute name="Penalty" use="required" type="{http://www.w3.org/2001/XMLSchema}duration" />
                     *     &lt;/restriction>
                     *   &lt;/complexContent>
                     * &lt;/complexType>
                     * </pre>
                     * 
                     * 
                     */
                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "")
                    public static class NodeTurnPenaltyBan {

                        @XmlAttribute(name = "FromNodeName", required = true)
                        protected String fromNodeName;
                        @XmlAttribute(name = "ToNodeName", required = true)
                        protected String toNodeName;
                        @XmlAttribute(name = "Penalty", required = true)
                        protected Duration penalty;

                        /**
                         * Gets the value of the fromNodeName property.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getFromNodeName() {
                            return fromNodeName;
                        }

                        /**
                         * Sets the value of the fromNodeName property.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setFromNodeName(String value) {
                            this.fromNodeName = value;
                        }

                        /**
                         * Gets the value of the toNodeName property.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getToNodeName() {
                            return toNodeName;
                        }

                        /**
                         * Sets the value of the toNodeName property.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setToNodeName(String value) {
                            this.toNodeName = value;
                        }

                        /**
                         * Gets the value of the penalty property.
                         * 
                         * @return
                         *     possible object is
                         *     {@link Duration }
                         *     
                         */
                        public Duration getPenalty() {
                            return penalty;
                        }

                        /**
                         * Sets the value of the penalty property.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link Duration }
                         *     
                         */
                        public void setPenalty(Duration value) {
                            this.penalty = value;
                        }

                    }

                }

            }

        }


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
         *         &lt;element name="SpeedBand" maxOccurs="unbounded" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;attribute name="Id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
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
            "speedBand"
        })
        public static class SpeedBands {

            @XmlElement(name = "SpeedBand", namespace = "http://www.contecint.com.au")
            protected List<CgGeography.Geov10RC.SpeedBands.SpeedBand> speedBand;

            /**
             * Gets the value of the speedBand property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the speedBand property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getSpeedBand().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link CgGeography.Geov10RC.SpeedBands.SpeedBand }
             * 
             * 
             */
            public List<CgGeography.Geov10RC.SpeedBands.SpeedBand> getSpeedBand() {
                if (speedBand == null) {
                    speedBand = new ArrayList<CgGeography.Geov10RC.SpeedBands.SpeedBand>();
                }
                return this.speedBand;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;attribute name="Id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class SpeedBand {

                @XmlAttribute(name = "Id", required = true)
                protected String id;
                @XmlAttribute(name = "Name", required = true)
                protected String name;

                /**
                 * Gets the value of the id property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getId() {
                    return id;
                }

                /**
                 * Sets the value of the id property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setId(String value) {
                    this.id = value;
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

            }

        }


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
         *         &lt;element name="TrackSection" maxOccurs="unbounded" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;attribute name="Id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="IsUp" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *                 &lt;attribute name="IsPermissive" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
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
            "trackSection"
        })
        public static class TrackSections {

            @XmlElement(name = "TrackSection", namespace = "http://www.contecint.com.au")
            protected List<CgGeography.Geov10RC.TrackSections.TrackSection> trackSection;

            /**
             * Gets the value of the trackSection property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the trackSection property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getTrackSection().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link CgGeography.Geov10RC.TrackSections.TrackSection }
             * 
             * 
             */
            public List<CgGeography.Geov10RC.TrackSections.TrackSection> getTrackSection() {
                if (trackSection == null) {
                    trackSection = new ArrayList<CgGeography.Geov10RC.TrackSections.TrackSection>();
                }
                return this.trackSection;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;attribute name="Id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="IsUp" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
             *       &lt;attribute name="IsPermissive" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class TrackSection {

                @XmlAttribute(name = "Id", required = true)
                protected String id;
                @XmlAttribute(name = "Name", required = true)
                protected String name;
                @XmlAttribute(name = "IsUp", required = true)
                protected boolean isUp;
                @XmlAttribute(name = "IsPermissive", required = true)
                protected boolean isPermissive;

                /**
                 * Gets the value of the id property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getId() {
                    return id;
                }

                /**
                 * Sets the value of the id property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setId(String value) {
                    this.id = value;
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
                 * Gets the value of the isUp property.
                 * 
                 */
                public boolean isIsUp() {
                    return isUp;
                }

                /**
                 * Sets the value of the isUp property.
                 * 
                 */
                public void setIsUp(boolean value) {
                    this.isUp = value;
                }

                /**
                 * Gets the value of the isPermissive property.
                 * 
                 */
                public boolean isIsPermissive() {
                    return isPermissive;
                }

                /**
                 * Sets the value of the isPermissive property.
                 * 
                 */
                public void setIsPermissive(boolean value) {
                    this.isPermissive = value;
                }

            }

        }

    }

}
