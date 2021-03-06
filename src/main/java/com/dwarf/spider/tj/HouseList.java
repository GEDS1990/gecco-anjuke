package com.dwarf.spider.tj;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;

@Gecco(matchUrl="https://tj.lianjia.com/ershoufang/pg{currentPage}/", pipelines={"consolePipeline"})
public class HouseList implements HtmlBean{

	private static final long serialVersionUID = 3192658614874493142L;

	@Request
	private HttpRequest request;

	@Text
	@HtmlField(cssPath=".page-box house-lst-page-box .on")
	private String currentPage;
	
	@Text
	@HtmlField(cssPath=".list-page>.total>em")
	private int totalCount;
	
	public HttpRequest getRequest() {
		return request;
	}

	public void setRequest(HttpRequest request) {
		this.request = request;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 这是只是为了测试，不是入口
	 * @param args
	 */
	public static void main(String[] args) {
		HttpRequest request = new HttpGetRequest("https://tj.lianjia.com/ershoufang/pg1/");
		GeccoEngine.create()
		.classpath("com.dwarf.spider.tj")
		//开始抓取的页面地址
		.start(request)
		//.debug(true)
		//开启几个爬虫线程,线程数量最好不要大于start request数量
		.thread(1)
		//单个爬虫每次抓取完一个请求后的间隔时间
		.interval(100)
		.run();
	}
	
}
