package com.example.net;

/**
 * 20209 获取血糖统计信息
 */
public class GetSugarStaticsRequestBean extends BaesRequest
{
	private String startTime, endTime;

	
	
	public GetSugarStaticsRequestBean(String startTime, String endTime)
	{
		setStartTime(startTime);
		setEndTime(endTime);
		setPatientId(10000039);
		setActId(Action.GET_SUGAR_STATISTICS_VALUE);
	}

	public String getStartTime()
	{
		return startTime;
	}

	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}

	public String getEndTime()
	{
		return endTime;
	}

	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}
	
	
	
}
