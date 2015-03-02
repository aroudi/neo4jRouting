// RailCorp 2014
package au.gov.nsw.railcorp.rttarefdata.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by arash on 30/05/14.
 */
public class DateUtil {

    private   static final Logger LOGGER_1 = LoggerFactory.getLogger(DateUtil.class);

    /**
     * dateToString.
     * @param date
     *          timeStamp
     * @return
     *      String
     */
    public static String dateToString(Date date) {
        String result = null;
        if (date == null) {
            return result;
        }
        try {
            result = (new SimpleDateFormat("dd-MM-yyyy hh:mma")).format(date);
            return result;
        } catch (Exception e) {
            LOGGER_1.error ("Error in formatting date:" + e);
            return null;
        }
    }

    /**
     * string to date.
     * @param str
     *          string in special format
     * @return
     *      timestamp
     */
    public static Date stringToDate(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        try {
            final Date date = (new SimpleDateFormat("dd-MM-yyyy hh:mma")).parse(str);
            return date;
        } catch (Exception e) {
            LOGGER_1.error ("Error in formatting date:" + e);
            return null;
        }
    }
}
