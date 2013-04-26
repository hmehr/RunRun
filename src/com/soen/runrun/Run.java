package com.soen.runrun;

public class Run {

	int lapsCompleted = 0;
	int totalDistance = 0;
	float topSpeed = 0;
	int totalTime = 0;
	float initialSpeed = 0;
	float averageSpeed = 0;

	public int getLapsCompleted() {
		return lapsCompleted;
	}

	public void setLapsCompleted(int lapsCompleted) {
		this.lapsCompleted = lapsCompleted;
	}

	public int getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(int totalDistance) {
		this.totalDistance = totalDistance;
	}

	public float getTopSpeed() {
		return topSpeed;
	}

	public void setTopSpeed(float topSpeed) {
		this.topSpeed = topSpeed;
	}

	public int getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}

	public float getInitialSpeed() {
		return initialSpeed;
	}

	public void setInitialSpeed(float initialSpeed) {
		this.initialSpeed = initialSpeed;
	}

	public float getAverageSpeed() {
		return averageSpeed;
	}

	public void setAverageSpeed(float averageSpeed) {
		this.averageSpeed = averageSpeed;
	}

	public Run(int lapsCompleted, int totalDistance, float topSpeed,
			int totalTime, float initialSpeed, float averageSpeed) {
		super();
		this.lapsCompleted = lapsCompleted;
		this.totalDistance = totalDistance;
		this.topSpeed = topSpeed;
		this.totalTime = totalTime;
		this.initialSpeed = initialSpeed;
		this.averageSpeed = averageSpeed;
	}

	public Run() {

	}

}
