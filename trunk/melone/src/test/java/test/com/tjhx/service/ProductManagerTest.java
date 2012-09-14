package test.com.tjhx.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjhx.dao.jpa.product.ProductJpaDao;
import com.tjhx.dto.ProductDTO;
import com.tjhx.entity.product.Product;

@DirtiesContext
@ContextConfiguration(locations = { "classpath*:/applicationContext.xml" })
@TransactionConfiguration(transactionManager = "defaultTransactionManager")
public class ProductManagerTest extends SpringTransactionalTestCase {
	@Resource
	private ProductJpaDao productJpaDao;

	@Test
	public void initFunction() throws JsonGenerationException, JsonMappingException, IOException,
			IllegalAccessException, InvocationTargetException {
		List<Product> _plist = (List<Product>) productJpaDao.findAll();

		List<ProductDTO> _pdtolist = new ArrayList<ProductDTO>();
		for (Product product : _plist) {
			ProductDTO _pdto = new ProductDTO();
			BeanUtils.copyProperties(_pdto, product);

			_pdtolist.add(_pdto);
		}

		ObjectMapper mapper = new ObjectMapper();
		String productsJsonStr = mapper.writeValueAsString(_pdtolist);
		System.out.println(productsJsonStr);
	}

}
