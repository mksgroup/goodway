package mksgroup.rservice;

import java.util.List;

import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.DoublePoint;
import org.rosuda.JRI.REXP;

public interface RService {
    REXP run(String cmd);
    REXP setup();
    REXP generateImage(String fileName);
    
    Cluster<DoublePoint> performCluster(List<DoublePoint> listAddr);
}
