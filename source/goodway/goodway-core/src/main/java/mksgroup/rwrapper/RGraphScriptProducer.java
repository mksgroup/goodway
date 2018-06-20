/**
 * Licensed to Open-Ones Group under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Open-Ones Group licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package mksgroup.rwrapper;

import java.util.Vector;

/**
 * @author ThachLN
 */
public class RGraphScriptProducer {
    /*
     * produces a pie chart of the Top 5 Champions League clubs
     */
    public static RGraphScript getTop5PieChart(String fileout, int[] p) {

        Vector v = new Vector();

        fileout = fileout.replace("\\", "/");
        v.add("library(Cairo)");
        v.add("CairoFonts(regular='Verdana:style=regular', bold='Verdana:style=bold')");
        v.add("Cairo(width=500, height=500, file = '" + fileout + "', type='png', quality=100)");
//        v.add("CairoPDF('" + fileout + "', 6,6, bg = 'white')");
        v.add("slices = c(10, 7, 5, 5, 5)");
        v.add("lbls = c('Real Madrid 10', 'Milano 7', 'Bayern Munich 5', 'Barcelona 5', 'Liverpool 5')");
        v.add("pie(slices, labels = lbls, main='Champions League top 5', clockwise=TRUE)");
        v.add("dev.off()");

        return new RGraphScript("Champions League top 5", (String[]) v.toArray(new String[0]));
    }
}
