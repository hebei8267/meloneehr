package com.tjhx.service.sell;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.common.utils.UuidUtils;
import com.tjhx.dao.jpa.sell.OrderJpaDao;
import com.tjhx.dto.PaymentDTO;
import com.tjhx.entity.sell.Order;
import com.tjhx.entity.sell.OrderItem;
import com.tjhx.service.product.ProductManager;

@Service
@Transactional(readOnly = true)
public class SellManager {
	@Resource
	private OrderJpaDao orderJpaDao;
	@Resource
	private ProductManager productManager;

	/**
	 * 付款-结账
	 * 
	 * @param payment
	 * @return 交易流水号(失败时返回null)
	 */
	public String payment(PaymentDTO payment) {

		// 效验附件对象的正确性
		if (null == payment || null == payment.getBarCode() || payment.getAmount().length <= 0) {
			return null;
		}

		// 购物单
		Order order = new Order();
		// 购物单流水号
		order.setOrderSerial(UuidUtils.getUuid());
		// 交易日期-YYYYMMDD
		order.setTransDate(DateUtils.getCurrentDateShortStr());

		for (int i = 0; i < payment.getAmount().length; i++) {

			OrderItem orderItem = new OrderItem();

			// 购物单流水号
			orderItem.setOrderSerial(order.getOrderSerial());
			// 商品编号/条形码
			orderItem.setBarCode(payment.getBarCode()[i]);
			// 商品价格(原始)
			orderItem.setPrice(productManager.getProductFromMemcached(payment.getBarCode()[i]).getRetailPrice());
			// 商品数量
			orderItem.setAmount(payment.getAmount()[i]);
			// 添加购物单-子项
			order.addItem(orderItem);
		}
		// 计算累计销售额
		order.calSales();
		orderJpaDao.save(order);

		return order.getOrderSerial();

	}
}
