
package au.gov.nsw.railcorp.rtta.refint.generated.gtfs;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for eGtfsServiceType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="eGtfsServiceType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="None"/>
 *     &lt;enumeration value="HighSpeed"/>
 *     &lt;enumeration value="InterCity"/>
 *     &lt;enumeration value="Suburban"/>
 *     &lt;enumeration value="OuterSuburban"/>
 *     &lt;enumeration value="FastFreight"/>
 *     &lt;enumeration value="SlowFreight"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "eGtfsServiceType", namespace = "http://www.railcorp.nsw.gov.au/RTTA/reference")
@XmlEnum
public enum EGtfsServiceType {

    @XmlEnumValue("None")
    NONE("None"),
    @XmlEnumValue("HighSpeed")
    HIGH_SPEED("HighSpeed"),
    @XmlEnumValue("InterCity")
    INTER_CITY("InterCity"),
    @XmlEnumValue("Suburban")
    SUBURBAN("Suburban"),
    @XmlEnumValue("OuterSuburban")
    OUTER_SUBURBAN("OuterSuburban"),
    @XmlEnumValue("FastFreight")
    FAST_FREIGHT("FastFreight"),
    @XmlEnumValue("SlowFreight")
    SLOW_FREIGHT("SlowFreight");
    private final String value;

    EGtfsServiceType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EGtfsServiceType fromValue(String v) {
        for (EGtfsServiceType c: EGtfsServiceType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
