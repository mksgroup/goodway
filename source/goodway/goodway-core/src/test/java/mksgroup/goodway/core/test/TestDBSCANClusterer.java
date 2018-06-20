package mksgroup.goodway.core.test;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.DBSCANClusterer;
import org.apache.commons.math3.ml.clustering.DoublePoint;
import org.apache.commons.math3.ml.distance.EuclideanDistance;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestDBSCANClusterer {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testCluster() {
        double eps = 15;
        int minPts = 2;
        DBSCANClusterer<DoublePoint> clusterProc = new DBSCANClusterer<> (eps, minPts);
        List<DoublePoint> mapData = new ArrayList<DoublePoint>();
        
        mapData.add(new DoublePoint(new double[] {1,2}));
        mapData.add(new DoublePoint(new double[] {3,4}));
        mapData.add(new DoublePoint(new double[] {7,8}));
        
        mapData.add(new DoublePoint(new double[] {100,102}));
        mapData.add(new DoublePoint(new double[] {103,104}));
        mapData.add(new DoublePoint(new double[] {107,108}));

        // Measure distance
        out.println("Could distances...");
        debugDistance(mapData, 0, 2);
        debugDistance(mapData, 3, 5);
        
        List<Cluster<DoublePoint>> clusters = clusterProc.cluster(mapData);
        
        out.println("Number of clusters:" + clusters.size());
        List<DoublePoint> points;
        for (Cluster<DoublePoint> cluster : clusters) {
            points = cluster.getPoints();
            out.println(String.format("Cluster with %d nodes.", points.size()));
            
            for (DoublePoint point : points) {
                out.println(String.format("(%s)", point.toString()));
            }
        }
    }

    private void debugDistance(List<DoublePoint> mapData, int startIdx, int endIdx) {
        EuclideanDistance ed = new EuclideanDistance();
        int ii;
        double d;
        for (int i = startIdx; i <= endIdx; i++) {
            ii = (i < endIdx) ? i + 1 : 0;
            d = countDistance(mapData.get(i).getPoint(), mapData.get(ii).getPoint());
        }
    }

    private double countDistance(double[] p1, double[] p2) {
        double d;
        EuclideanDistance ed = new EuclideanDistance();
        
        d = ed.compute(new double[] {p1[0], p2[0]}, new double[] {p1[1], p2[1]});
        out.println(String.format("Distance between(%f, %f) and (%f, %f): %f", p1[0], p1[1], p2[0], p2[1], d));
        
        return d;
    }

}
