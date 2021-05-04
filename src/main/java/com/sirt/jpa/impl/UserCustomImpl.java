package com.sirt.jpa.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sirt.jpa.UserCustom;

public class UserCustomImpl implements UserCustom{
	
	private final String GET_REPORT = "select to_char(transaction_date,'YYYY') || to_char(transaction_date,'MM') EPOCH, CUSTOMER_NAME,\r\n"
			+ "sum(CASE WHEN transaction_amount > 100\r\n"
			+ "         THEN (transaction_amount-100)*2+50\r\n"
			+ "         WHEN transaction_amount > 50 and transaction_amount < 101 \r\n"
			+ "         THEN transaction_amount-50 else 0 end) reward\r\n"
			+ "from transaction\r\n"
			+ "where transaction_date >= dateadd(day, -60, CURRENT_TIMESTAMP) \r\n"
			+ "group by EPOCH, CUSTOMER_NAME\r\n"
			+ "order by 1 desc,2 asc";
	
	@PersistenceContext
    private EntityManager em;
//	
//	public List<ReportResponse> getReport(){
//		List<ReportResponse> responses = new ArrayList<>();
//		
//		Query query = em.createNativeQuery(GET_REPORT);
//		List<Object[][]> resultSet = query.getResultList();
//		
//		if(resultSet.size()==0) return responses;
//		
//		int total = 0;
//		String lastName = null;
//		
//		List<MonthReport> monthReports = new ArrayList<>();
//		
//		for(Object[] tuple : resultSet) {
//			String name = (String)tuple[1];
//			if(lastName!=null && !name.equals(lastName)) {
//				ReportResponse response = new ReportResponse();
//				response.setTotalReward(total);
//				response.setMonthReports(monthReports);
//				response.setName(name);
//				responses.add(response);
//				total = 0;
//				monthReports = new ArrayList<>();
//			}
//			lastName = name;
//			int monthTotal = ((BigInteger)tuple[2]).intValue();
//			total += monthTotal;
//			MonthReport monthReport = new MonthReport(Month.of(Integer.parseInt(((String)tuple[0]).substring(4))).name(), monthTotal);
//			monthReports.add(monthReport);
//		}
//				
//		ReportResponse response = new ReportResponse();
//		response.setTotalReward(total);
//		response.setMonthReports(monthReports);
//		response.setName(lastName);
//		responses.add(response);
//
//		return responses;
//		
//	}

}
