package au.gov.nsw.railcorp.rttarefdata.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by arash on 7/04/2015.
 */
@Component
public class TurnPenaltyBanLoader {
    private final Logger logger = LoggerFactory.getLogger(TurnPenaltyBanLoader.class);
    private HashMap<String, String> penaltyBans;

    /**
     * add new entry to the hashMap.
     * @param key key
     * @param penalty penalty
     */
    public void addEntry(String key, String penalty) {
        try {
            if (key == null) {
                return;
            }
            if (penaltyBans == null) {
                penaltyBans = new HashMap<String, String>();
            }
            if (!penaltyBans.containsKey(key)) {
                penaltyBans.put(key, penalty);
            }
        } catch (Exception e) {
            return;
        }
    }

    /**
     * return penalty.
     * @param key key
     * @return penalty
     */
    public String getPenalty(String key) {
        try {
            if (penaltyBans == null || key == null) {
                return null;
            }
            if (penaltyBans.containsKey(key)) {
                return (String) penaltyBans.get(key);
            }
            return null;
        } catch (Exception e) {
          return null;
        }
    }

    /**
     * remove all entries.
     */
    public void emptyPenaltyBans() {
        if (penaltyBans == null) {
            return;
        }
        penaltyBans.clear();
    }
}
