package Controller;

import javax.swing.JLabel;

public class Process extends Thread {

	private JLabel chronometerLabel;
	public boolean stop;
	
	public Process(JLabel chronometerLabel) {
		this.chronometerLabel = chronometerLabel;
	}

	public void run() {
		this.stop = false;
		int seg = 0;
		int min = 0;
		int hours = 0;
		while (!stop) {
			try {
				sleep(1000);
				seg++;
				if (seg > 59) {
					seg = 0;
					min++;
				}

				if (min > 59) {
					min = 0;
					hours++;
				}

				String segStr = String.valueOf(seg);
				String minStr = String.valueOf(min);
				String hoursStr = String.valueOf(hours);
				if (seg < 10)
					segStr = "0" + segStr;			
				if (min < 10)
					minStr = "0" + minStr;
				if (hours < 10)
					hoursStr = "0" + hoursStr;

				chronometerLabel.setText(hoursStr + ":" + minStr + ":" + segStr);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	public void setStop(boolean stop) {
		this.stop = stop;
	}
}
