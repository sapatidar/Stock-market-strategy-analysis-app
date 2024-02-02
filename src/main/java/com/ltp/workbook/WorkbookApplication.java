package com.ltp.workbook;

import com.ltp.externalApis.ExternalCalls;
import com.ltp.model.Listings;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.ltp.jobImpl.*;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
public class WorkbookApplication {

	public static TimeZone tZone = TimeZone.getTimeZone("GMT-5:00");
	private static Boolean marketOpen;
	public static List<StrategyResponse> latestResponse;			//callDBForLatestRecords();
	public static String list1="Thu Dec 07 15:05:00 EST 2023";
	public static String list2="Thu Dec 07 15:06:00 EST 2023";
	public static String list3="Thu Dec 07 15:07:00 EST 2023";
	public static String list4="Thu Dec 07 15:08:00 EST 2023";

	public static void main(String[] args) {
		SpringApplication.run(WorkbookApplication.class, args);
		//targetHitsChecker();
	}

	//@Scheduled(zone = "GMT-5:00", cron = "0 */5 9-15 * * 1-5")
	public static void analysisJobPhase1() throws IOException, InterruptedException, ParseException {
		System.out.println("job1");
		System.out.println("processing list 1 with searchKey:"+list1);
		try{
			list1 = AnalysisJob.runAnalysis(list1);
		}catch(Exception e){
			System.out.println("Failed to process list1. Will retry in 5 mins");
		}
		System.out.println("waiting 1 min");
		Thread.sleep(60000);
		System.out.println("processing list 2 with searchKey:"+list2);
		try{
			list2 = AnalysisJob.runAnalysis(list2);
		}catch(Exception e){
			System.out.println("Failed to process list2. Will retry in 5 mins");
		}
		System.out.println("waiting 1 min");
		Thread.sleep(60000);
		System.out.println("processing list 3 with searchKey:"+list3);
		try{
			list3 = AnalysisJob.runAnalysis(list3);
		}catch(Exception e){
			System.out.println("Failed to process list3. Will retry in 5 mins");
		}
		System.out.println("waiting 1 min");
		Thread.sleep(60000);
		System.out.println("processing list4 with searchKey:"+list4);
		try{
			list4 = AnalysisJob.runAnalysis(list4);
		}catch(Exception e){
			System.out.println("Failed to process list4. Will retry in 5 mins");
		}
		System.out.println(list1+", "+list2+", "+list3+", "+list4);

	}

	//@Scheduled(zone = "GMT-5:00", cron = "0 0-59/5 10-16 * * 1-5")
	public static void analysisJobPhase2() throws InterruptedException {
		System.out.print("job2");
		System.out.println("processing list 1 with searchKey:"+list1);
		try{
			list1 = AnalysisJob.runAnalysis(list1);
		}catch(Exception e){
			System.out.println("Failed to process list1. Will retry in 5 mins");
		}
		System.out.println("waiting 1 min");
		long start = new Date().getTime();
		while(new Date().getTime() - start < 1000L){}
		System.out.println("processing list 2 with searchKey:"+list2);
		try{
			list2 = AnalysisJob.runAnalysis(list2);
		}catch(Exception e){
			System.out.println("Failed to process list2. Will retry in 5 mins");
		}
		System.out.println("waiting 1 min");
		start = new Date().getTime();
		while(new Date().getTime() - start < 1000L){}
		System.out.println("processing list 3 with searchKey:"+list3);
		try{
			list3 = AnalysisJob.runAnalysis(list3);
		}catch(Exception e){
			System.out.println("Failed to process list3. Will retry in 5 mins");
		}
		System.out.println("waiting 1 min");
		start = new Date().getTime();
		while(new Date().getTime() - start < 1000L){}
		System.out.println("processing list4 with searchKey:"+list4);
		try{
			list4 = AnalysisJob.runAnalysis(list4);
		}catch(Exception e){
			System.out.println("Failed to process list4. Will retry in 5 mins");
		}
		System.out.println(list1+", "+list2+", "+list3+", "+list4);
	}

	@Scheduled(cron = "0 2 16 * * 1-5")
	public static void analysisJobCloseMarket(){
		System.out.println("job3");
		System.out.println(list1);
		System.out.println(list2);
		System.out.println(list3);
		System.out.println(list4);

	}

	//@Scheduled(cron = "0 */2 * * * 1-5")
	public static void targetHitsChecker(){
		System.out.println("targetHitsChecker");
		String date = "2023-12-04";
		List<String> companies = Listings.getList1();
		companies.addAll(Listings.getList2());
		companies.addAll(Listings.getList3());
		companies.addAll(Listings.getList4());
		RestTemplate template = new RestTemplateBuilder().build();
		companies.forEach((company)->{
			StrategyResponse response = new StrategyResponse();
			JSONObject latestQuote = ExternalCalls.getLatestQuote(company);
			double current_Price = Double.parseDouble(latestQuote.getString("05. price"));
			response.setCurrentPrice(current_Price);
			double high = Double.parseDouble(latestQuote.getString("03. high"));
			response.setHigh(high);
			double low = Double.parseDouble(latestQuote.getString("04. low"));
			response.setLow(low);
			double open = Double.parseDouble(latestQuote.getString("02. open"));
			response.setOpeningPrice(open);
			double close = Double.parseDouble(latestQuote.getString("08. previous close"));
			response.setClosingPrice(close);
			int volume = Integer.parseInt(latestQuote.getString("06. volume"));
			response.setVolume(volume);
			double change = Double.parseDouble(latestQuote.getString("09. change"));
			response.setPriceChange(change);
			response.setSma(current_Price);
			response.setEma12(current_Price);
			response.setEma26(current_Price);
			response.setEma200(current_Price);

			JSONObject news = ExternalCalls.getNews(company);
			response.setNewsHeadline(news.getString("title"));
			response.setNewsSummary(news.getString("summary"));
			response.setNewsSource(news.getString("source"));
			response.setSearchKey(new Date().toString());
			response.setSymbol(company);
			template.postForObject("http://localhost:9090//strategyResponseController", response, StrategyResponse.class);
		});
	}


	public static void test1(){
		System.out.print("test1");
		RestTemplate template = new RestTemplateBuilder().build();
		StrategyResponse response = new StrategyResponse();
		response.setSymbol("IBM");
		response.setSearchKey("Ub�d^Λ");
		response.setId(new Random().toString());
		template.postForObject("http://localhost:9090//strategyResponseController", response, StrategyResponse.class);
		response.setId(new Random().toString());
		template.postForObject("http://localhost:9090//strategyResponseController", response, StrategyResponse.class);
		response.setId(new Random().toString());
		template.postForObject("http://localhost:9090//strategyResponseController", response, StrategyResponse.class);

	}

}
