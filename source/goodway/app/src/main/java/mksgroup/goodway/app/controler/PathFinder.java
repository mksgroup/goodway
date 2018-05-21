package mksgroup.goodway.app.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PathFinder {
    
    /**
     *  Lập kế hoạch giao hàng.
     * @param orderIds danh sách mã đơn hàng (ID trong cơ sở dữ liệu).
     * @param vehicleIds danh sách xe (ID trong cơ sở dữ liệu)
     * @param nday số ngày gợi ý có thể giao hàng 
     * @return danh sách các phương án giao hàng. Mỗi phương án có ID tương ứng trong CSDL.
     * Thông tin sơ bộ của một kết quả phương án giao hàng gồm:
     */
    @GetMapping
    public Long[] startFindPath(long[] orderIds, long[] vehicleIds, int nday) {
        return null;
    }
}
