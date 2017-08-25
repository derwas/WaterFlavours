package controllers;

public class Stats {
	private static int calls ;
	private static int mCalls ;
	private static int fCalls ;
	private static int cCalls ;
	private static int uCalls ;

	
	public static void setmCalls(int num) {
		calls = calls - mCalls;
		Stats.mCalls = num;
		calls = calls + num;

	}
	public static void setfCalls(int num) {
		calls = calls - fCalls;
		Stats.fCalls = num;
		calls = calls + num;

	}
	public static void setcCalls(int num) {
		calls = calls - cCalls;
		Stats.cCalls = num;
		calls = calls + num;

	}
	public static void setuCalls(int num) {
		calls = calls - uCalls;
		Stats.uCalls = num;
		calls = calls + num;
	}
	public static int getCalls() {
		return calls;
	}
	public static int getmCalls() {
		return mCalls;
	}
	public static int getfCalls() {
		return fCalls;
	}
	public static int getcCalls() {
		return cCalls;
	}
	public static int getuCalls() {
		return uCalls;
	}

	
	public static void oneMoreCall() {
		calls ++;
	}
	
	public static void oneMoreMetaphorCall() {
		oneMoreCall();
		mCalls ++;
	}
	
	public static void oneMoreFootprintCall() {
		oneMoreCall();
		fCalls ++;
	}
	public static void oneMoreUseCall() {
		oneMoreCall();
		uCalls ++;
	}
	
	public static void oneMoreCostCall() {
		oneMoreCall();
		cCalls ++;
	}

}
