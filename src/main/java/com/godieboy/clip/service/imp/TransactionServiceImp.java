package com.godieboy.clip.service.imp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godieboy.clip.dao.TransactionDAO;
import com.godieboy.clip.dto.TransactionReportDTO;
import com.godieboy.clip.dto.WeekRange;
import com.godieboy.clip.exception.TransactionException;
import com.godieboy.clip.model.Transaction;
import com.godieboy.clip.service.TransactionService;
import com.godieboy.clip.util.TransactionUtil;

@Service
public class TransactionServiceImp implements TransactionService {

	@Autowired
	private TransactionDAO transactionDAO;

	@Override
	public Transaction add(Transaction transaction) throws TransactionException {
		if (transaction.getAmount() < 1) {
			throw new TransactionException("Amount must to be positive", 10);
		}
		return transactionDAO.save(transaction);
	}

	@Override
	public Transaction show(long userId, String transactionId) throws TransactionException{
		Transaction findByIdAndUserId = transactionDAO.findByIdAndUserId(transactionId, userId);
		if(findByIdAndUserId == null) {
			throw new TransactionException("Transaction not found", 30);
		}
		return findByIdAndUserId;
	}

	@Override
	public List<Transaction> findByUser(long userId) {
		return transactionDAO.findByUserId(userId);
	}

	@Override
	public double sumByUser(long userId) {
		double sum = 0;
		List<Transaction> transactions = transactionDAO.findByUserId(userId);
		for (Transaction t : transactions) {
			sum += t.getAmount();
		}
		sum = Math.round(sum * 100) / 100.0d; // para redondear
		return sum;
	}

	@Override
	public List<TransactionReportDTO> getReport(long userId) {
		List<Transaction> transactions = transactionDAO.findByUserId(userId);
		System.out.printf("Transactions found %d %n", transactions.size());

		Date maxDate = new Date(); // transactions.stream().map(t -> t.getDate()).max(Date::compareTo).get();
		Date minDate = transactions.stream().map(t -> t.getDate()).min(Date::compareTo).get();

		List<WeekRange> weekRanges = getWeeks(minDate, maxDate);

		double totalAmount = 0;
		List<TransactionReportDTO> transactionReport = new ArrayList<TransactionReportDTO>();
		for (WeekRange weekrange : weekRanges) {

			List<Transaction> filtered = transactions.stream().filter(p -> weekrange.isInRange(p.getDate()))
					.collect(Collectors.toList());

			TransactionReportDTO transactionReportDTO = new TransactionReportDTO();
			transactionReportDTO.setQuantity(filtered.size());
			transactionReportDTO.setTotalAmount(totalAmount);
			transactionReportDTO.setWeekStartDate(TransactionUtil.formatDayWitday(weekrange.getStartDate()));
			transactionReportDTO.setWeekEndDate(TransactionUtil.formatDayWitday(weekrange.getEndDate()));
			transactionReportDTO.setUserId(userId);
			double totalRange = filtered.stream().mapToDouble(p -> p.getAmount()).sum();

			transactionReportDTO.setAmount(totalRange);

//				System.out.printf("week %s - %s count= %d, anterior = %f, acomulado=%f %n",
//						TransactionUtil.formatDayWitday(weekrange.getStartDate()),
//						TransactionUtil.formatDayWitday(weekrange.getEndDate()), filtered.size(), totalAmount,
//						totalAmount + totalRange);
			totalAmount = totalAmount + totalRange;

			transactionReport.add(transactionReportDTO);

		}

		return transactionReport;
	}

	private List<WeekRange> getWeeks(Date minDate, Date maxDate) {
		List<WeekRange> weekRanges = new ArrayList<>();
		Calendar cMin = Calendar.getInstance();
		cMin.setTime(minDate);
		Calendar cMax = Calendar.getInstance();
		cMax.setTime(maxDate);
		cMax.add(Calendar.WEEK_OF_YEAR, -1);
		maxDate = cMax.getTime();
		cMin.setFirstDayOfWeek(Calendar.FRIDAY);
		while (cMin.getTime().before(maxDate)) {
			int dayOfWeek = cMin.get(Calendar.DAY_OF_WEEK);
			int monthStart = cMin.get(Calendar.MONTH);
			if (dayOfWeek != Calendar.FRIDAY) {
				cMin.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
			}
			Date startDate = cMin.getTime();
			// String start = dateFormat2.format(startDate);
			cMin.add(Calendar.WEEK_OF_YEAR, 1);
			cMin.add(Calendar.DAY_OF_YEAR, -1);

			int monthEnd = cMin.get(Calendar.MONTH);
			if (monthStart != monthEnd) {// if month are diferents then set last day and first day
				Calendar calAux = Calendar.getInstance();
				calAux.setTime(startDate);
				int lastDayOfMonth = calAux.getActualMaximum(Calendar.DATE);
				calAux.set(Calendar.DAY_OF_MONTH, lastDayOfMonth);
				Date endDate = calAux.getTime();
				// String end = dateFormat2.format(endDate);

				weekRanges.add(new WeekRange(startDate, endDate));

				calAux.add(Calendar.MONTH, 1);
				calAux.set(Calendar.DAY_OF_MONTH, 1);

				startDate = calAux.getTime();
				// start = dateFormat2.format(startDate);
			}
			Date endDate = cMin.getTime();
			// String end = dateFormat2.format(endDate);
			cMin.add(Calendar.DAY_OF_YEAR, 1);

			weekRanges.add(new WeekRange(startDate, endDate));
		}
		// weekRanges.stream().forEach(wr -> System.out.printf("week %s - %s %n",
		// TransactionUtil.formatDayWitday(wr.getStartDate()),
		// TransactionUtil.formatDayWitday(wr.getEndDate())));

		return weekRanges;
	}

}
