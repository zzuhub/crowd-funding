package cn.wcj.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.wcj.entity.CategoryEchart;

/**
 * 
 *�ٶ�Echartsͼ��DAO��
 *
 * ���DAO������DAO�㲻һ��,����Ҫ�̳�IBaseDAO
 * 
 * ��Ҫ���MySQL����ͳ��
 * 
 * ���ظ����Ʋ�
 * 
 * ����JQuery-AJAX����Controller����ɰٶ�EChartsͼ����ʾ
 * 
 * @author ZZU��WangCJ
 *
 * @since 2017-03-31 10:15:06
 *
 */
@Repository("echartsDAO")
public interface IEchartsDAO {

	List<CategoryEchart> findCategoryEcharts()throws Exception  ;
	
}
