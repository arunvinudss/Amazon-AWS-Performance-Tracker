package com.arun;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoadSimulatorServlet
 */
public class LoadSimulatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, String> map = new ConcurrentHashMap<String, String>();
	private List<Timer> timers = new ArrayList<Timer>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {

		System.out.println("In LoadSimulatorServlet start");

		for (int i = 0; i < 50; ++i) {
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
				map.put(String.valueOf(Math.random()),
						String.valueOf(Math.random()));
				System.out.println("size==" + map.size());
			}
		}, 0, 6000);
		return timer;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadSimulatorServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.println("Map size==" + map.size());
		pw.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	@Override
	public void destroy() {
		super.destroy();
		if (timers != null) {
			for (Timer timer : timers) {
				timer.cancel();
				timer.purge();
			}
		}
	}
}
