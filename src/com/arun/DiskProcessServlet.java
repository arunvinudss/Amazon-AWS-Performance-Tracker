package com.arun;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DiskProcessServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7037423400091900073L;
	private List<Timer> timers = new ArrayList<Timer>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.println("processing");
		pw.flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	@Override
	public void destroy() {
		if (timers != null) {
			for (Timer timer : timers) {
				timer.cancel();
				timer.purge();
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#getServletInfo()
	 */
	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return super.getServletInfo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {

		super.init();
		System.out.println("In diskprocess1 start");
		
		for(int i=0;i<50;++i){
		  timers.add(createTimer());
		}
	}

	/**
	 * 
	 */
	private Timer createTimer() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {

				boolean isTan = false;
				double seed = 40;
				for (long i = 0; i < 10000000l; ++i) {
					if (isTan) {
						seed = Math.atan(seed);
					} else {
						seed = Math.tan(seed);
					}
					isTan = !isTan;
				}
				new TextMatcher().match(getRandomWordToMatch());
		//		System.out.println("seed " + seed + " "
			//			+ (System.currentTimeMillis() - start));
			}
		}, 0, 60000);
		return timer;
	}

	protected String getRandomWordToMatch() {
        double rnd = Math.random();
        String[] wordsToSearch = {"weighing","goldsmith","appointed","exquisitely","successive","leadership","difference","architect","eighteen","determining","ceremony"};
        int arrInd = getRandomNumberInRange(0,wordsToSearch.length-1,new Random());
        //System.out.println("arrInd=="+arrInd+" "+wordsToSearch[arrInd]);
		return wordsToSearch[arrInd];
	}

	private static int getRandomNumberInRange(int aStart, int aEnd, Random aRandom){
	    if ( aStart > aEnd ) {
	      throw new IllegalArgumentException("Start cannot exceed End.");
	    }
	    //get the range, casting to long to avoid overflow problems
	    long range = (long)aEnd - (long)aStart + 1;
	    // compute a fraction of the range, 0 <= frac < range
	    long fraction = (long)(range * aRandom.nextDouble());
	    int randomNumber =  (int)(fraction + aStart);    
	    return randomNumber;
	  }
}
