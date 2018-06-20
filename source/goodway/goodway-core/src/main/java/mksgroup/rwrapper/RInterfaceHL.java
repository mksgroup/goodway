package mksgroup.rwrapper;
import org.rosuda.JRI.REXP;
import org.rosuda.JRI.RMainLoopCallbacks;
import org.rosuda.JRI.Rengine;

public class RInterfaceHL {
    private static final boolean RUN_MAIN_LOOP = false;
    private static RInterfaceHL theInstance;
    private final Rengine engine;
    
    private RInterfaceHL(Rengine engine) {
        this.engine = engine;
        this.engine.idleDelay = 500;
        Rengine.DEBUG = 10;
    }

    public static synchronized RInterfaceHL getInstance() {
        if (theInstance == null)
            RInterfaceHL.initialize("");

        return theInstance;
    }

    public static synchronized void initialize(String loopback) {

        if (theInstance != null)
            throw new IllegalStateException("already initialized");

        RMainLoopCallbacks rmainLoopCallbacks = new RMainLoopCallbacksImpl();
        theInstance = new RInterfaceHL(new Rengine(new String[]{"--vanilla"}, RUN_MAIN_LOOP, rmainLoopCallbacks));
    }

    /* wrapper methods for Rengine */
    public synchronized long getVersion() {
        return Rengine.getVersion();
    }

    public synchronized REXP eval(String s) {
        return engine.eval(s);
    }

    public static synchronized Rengine getMainEngine() {
        return RInterfaceHL.getMainEngine();
    }

    public static synchronized boolean waitForR() {
        return RInterfaceHL.waitForR();
    }

    public synchronized void destroy() {
        engine.end();
    }
}
