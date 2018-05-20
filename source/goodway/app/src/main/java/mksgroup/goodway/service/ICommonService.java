/**
 * Copyright (C) 2018 MKSGROUP - All Rights Reserved.
 */
package mksgroup.goodway.service;

public interface ICommonService {
    /**
     *  Lập kế hoạch giao hàng.
     * @param orderIds danh sách mã đơn hàng (ID trong cơ sở dữ liệu).
     * @param vehicleIds danh sách xe (ID trong cơ sở dữ liệu)
     * @param nday số ngày gợi ý có thể giao hàng 
     * @return danh sách các phương án giao hàng. Mỗi phương án có ID tương ứng trong CSDL.
     * Thông tin sơ bộ của một kết quả phương án giao hàng gồm:
     * - DeliveryPlan
     * 
     */
    long[] makeDeliveryPlan(long[] orderIds, long[] vehicleIds, int nday);
}
