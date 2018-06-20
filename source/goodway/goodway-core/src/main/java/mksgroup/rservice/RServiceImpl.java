package mksgroup.rservice;

import java.util.List;

import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.DoublePoint;
import org.rosuda.JRI.REXP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import mksgroup.rwrapper.RInterfaceHL;

@Service
public class RServiceImpl implements RService {
    /** For logging. */
    private static final Logger LOG = LoggerFactory.getLogger(RServiceImpl.class);

    @Value("${output.folder}")
    String outputFolder;
    
    @Value("${install.packages}")
    String installPackages;

    private RInterfaceHL re = RInterfaceHL.getInstance();

    /**
     * Đối với một số thư viện, bạn cần phải install lại khi chạy trong ứng dụng.
     * @see mksgroup.rservice.RService#setup()
     */
    @Override
    public REXP setup() {
        REXP rexp = null;
        if (!StringUtils.isEmpty(installPackages)) { 
            rexp = re.eval(installPackages);
            if (rexp != null) {
                LOG.debug(rexp.asString());
            } else {
                LOG.error(String.format("Error in execute command %s", installPackages));
            }
        }
        
        return rexp;
    }
    
    @Override
    public REXP run(String cmd) {

        return re.eval(cmd);
    }

    @Override
    public REXP generateImage(String fileName) {
        String[] commands = {
//                "install.package('Cairo')",
                "library(Cairo)",
                String.format("Cairo(600, 600, file='%s%s', type='png', bg='white')", outputFolder, fileName),
                "plot(rnorm(4000),rnorm(4000),col='#ff000018',pch=19,cex=2) # semi-transparent red",
                "dev.off() # creates a file 'plot.png' with the above plot"
        };

        /*
         * generate R image
         */
        REXP rexp = null;
        for (String cmd : commands) {
            rexp = re.eval(cmd);

            if (rexp != null) {
                LOG.debug(rexp.asString());
            } else {
                System.err.println(cmd);
            }
        }
        
        return rexp;
    }

    @Override
    public Cluster<DoublePoint> performCluster(List<DoublePoint> listAddr) {
        // TODO Auto-generated method stub
        return null;
    }
}
