package uk.jkhulme.roboarmcontroller;

/**
 * Created by james on 04/09/2013.
 */
public class World {
    private final String LOGIN = "0000";
    private final String ROTATE_CCW = "0001";
    private final String ROTATE_CW = "0010";
    private final String SHOULDER_UP = "0011";
    private final String SHOULDER_DOWN = "0100";
    private final String ELBOW_UP = "0101";
    private final String ELBOW_DOWN = "0110";
    private final String WRIST_UP = "0111";
    private final String WRIST_DOWN = "1000";
    private final String GRIP_OPEN = "1001";
    private final String GRIP_CLOSE = "1010";
    private final String LIGHT_ON = "1011";

    private String ip = null;
    private String code = null;

    private static World instance = null;

    protected World() {
        // Exists only to defeat instantiation.
    }
    public static World getInstance() {
        if(instance == null) {
            instance = new World();
        }
        return instance;
    }

    public String getLOGIN() {
        return LOGIN;
    }

    public String getROTATE_CCW() {
        return ROTATE_CCW;
    }

    public String getROTATE_CW() {
        return ROTATE_CW;
    }

    public String getSHOULDER_UP() {
        return SHOULDER_UP;
    }

    public String getSHOULDER_DOWN() {
        return SHOULDER_DOWN;
    }

    public String getELBOW_UP() {
        return ELBOW_UP;
    }

    public String getELBOW_DOWN() {
        return ELBOW_DOWN;
    }

    public String getWRIST_UP() {
        return WRIST_UP;
    }

    public String getWRIST_DOWN() {
        return WRIST_DOWN;
    }

    public String getGRIP_OPEN() {
        return GRIP_OPEN;
    }

    public String getGRIP_CLOSE() {
        return GRIP_CLOSE;
    }

    public String getLIGHT_ON() {
        return LIGHT_ON;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
