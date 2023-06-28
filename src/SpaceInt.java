import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SpaceInt {

    public static void main(String[] args) throws ParseException {

        double[] transfers = {
                6057.40, 8836.62, 9764.25, 7497.91, 4357.27, 720.01, 6172.99, 3955.23,
                6139.59, 6789.37, 3784.11, 8038.22, 5890.01, 6968.98, 5482.94, 262.01,
                4106.93, 9971.85, 7207.67, 4488.62
        };


        String[] transfer_dates = {
                "13-Jul-05", "15-Oct-22", "15-Apr-22", "15-Jan-22", "13-Jul-22", "13-Jul-22",
                "15-Mar-23", "15-Feb-23", "15-Jan-23", "15-Jul-21", "15-Apr-23", "15-Dec-20",
                "15-May-22", "13-Jul-22", "15-Jun-23", "13-Jul-22", "15-Mar-22", "3-May-23",
                "13-Jul-22", "13-Jul-22"
        };


    }

    public static int calculateAverageOfLastSixMonth(String[] transferDates, double[] transfers) throws ParseException {

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yy");


        Date tempDate = dateFormatter.parse(transferDates[0]); //initializing tempDate

        for (int i = 0; i < transferDates.length; i++) {   //calculating maxTransactionDate

            Date parseDate = dateFormatter.parse(transferDates[i]);
            {
                if (parseDate.after(tempDate)) {
                    tempDate = parseDate;
                }
            }
        }

        Date maxDate = tempDate;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(maxDate);
        calendar.add(Calendar.MONTH, -6);
        Date lastSixMonth = calendar.getTime();


        double sumOfLastSixMonthTransfers = 0.0;

        int countOfLastSixMonthTransfers = 0;


        for (int i = 0; i < transfers.length; i++) {

            Date parsedDate = dateFormatter.parse(transferDates[i]);

            if (parsedDate.after(lastSixMonth) && transfers[i] >= 300) {

                sumOfLastSixMonthTransfers += transfers[i];
                countOfLastSixMonthTransfers++;
            }
        }

        return (int) sumOfLastSixMonthTransfers / countOfLastSixMonthTransfers;

    }


    
    public static String calculateMaximumTransferredMoneyInOneMonth(String[] transferDates, double[] transfers) throws ParseException {

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yy");
        double maxAmount = transfers[0];
        Date month = null;

        Map<Date, Double> datesAndTransfers = new HashMap<>();

        for (int i = 0; i < transfers.length; i++) {

            Date tempDate = dateFormatter.parse(transferDates[i]);

            if (transfers[i] >= 300.0) {
                if (datesAndTransfers.containsKey(tempDate)) {

                    double sumOfSameMonthTransfers = datesAndTransfers.get(tempDate) + transfers[i];

                    datesAndTransfers.put(tempDate, sumOfSameMonthTransfers);

                } else {
                    datesAndTransfers.put(tempDate, transfers[i]);
                }
            }
        }

        for (Date transDate : datesAndTransfers.keySet()) {

            if (datesAndTransfers.get(transDate) > maxAmount) {
                maxAmount = datesAndTransfers.get(transDate);
                month = transDate;
            }
        }

        return "Max amount: " + maxAmount + " " + "Month: " + month.toString();
    }


}
