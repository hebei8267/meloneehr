package cn.com.free.framework.generator.provider;

import java.util.Map;

/**
 * 该接口用于为 模板及路径提供相关变量的引用
 */
@SuppressWarnings("rawtypes")
public interface IGeneratorModelProvider {

	public String getDisaplyText();

	/** 得到文件路径可以引用的变量 */

	public void mergeTemplateModel(Map model) throws Exception;

	/** 得到模板文件可以引用的变量 */
	public void mergeFilePathModel(Map model) throws Exception;

}
