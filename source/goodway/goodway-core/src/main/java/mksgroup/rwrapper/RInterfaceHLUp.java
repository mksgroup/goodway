package mksgroup.rwrapper;
import java.lang.reflect.InvocationTargetException;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngine;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.REngineStdOutput;

public class RInterfaceHLUp {
    private static RInterfaceHLUp theInstance;
    private final REngine engine;

    private RInterfaceHLUp(REngine engine) {
        this.engine = engine;
//        this.engine.idleDelay = 500;
//        REngine.DEBUG = 10;
    }

    public static synchronized RInterfaceHLUp getInstance() {
        if (theInstance == null)
            RInterfaceHLUp.initialize("");

        return theInstance;
    }

    public static synchronized void initialize(String loopback) {

        if (theInstance != null)
            throw new IllegalStateException("already initialized");

        // Refer https://github.com/s-u/REngine/blob/master/JRI/test/RTest.java
        REngine instance;
        try {
            REngineStdOutput rEngineStdOutput = new REngineStdOutput();
            instance = REngine.engineForClass("org.rosuda.REngine.JRI.JRIEngine", new String[]{"--vanilla"}, rEngineStdOutput, false);
            theInstance = new RInterfaceHLUp(instance);

        } catch (Exception ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
            
        } 

        
    }

    /* wrapper methods for Rengine */
//    public synchronized long getVersion() {
//        return Rengine.getVersion();
//    }

    public synchronized REXP eval(String s) {
        try {
            return engine.parseAndEval(s);
        } catch (REngineException | REXPMismatchException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        
        return null;
    }

    public static synchronized REngine getMainEngine() {
        return RInterfaceHLUp.getMainEngine();
    }

    public static synchronized boolean waitForR() {
        return RInterfaceHLUp.waitForR();
    }

    public synchronized void destroy() {
        engine.close();
    }

}
