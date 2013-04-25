package com.soen.runrun;

import android.os.Parcel;
import android.os.Parcelable;

public class Preference implements Parcelable {

	int distance;
	int initialTimeInterval;
	int decrementRate;
	String beepSound;
	String musicSound;
	String runnerName;

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getInitialTimeInterval() {
		return initialTimeInterval;
	}

	public void setInitialTimeInterval(int initialTimeInterval) {
		this.initialTimeInterval = initialTimeInterval;
	}

	public int getDecrementRate() {
		return decrementRate;
	}

	public void setDecrementRate(int decrementRate) {
		this.decrementRate = decrementRate;
	}

	public String getBeepSound() {
		return beepSound;
	}

	public void setBeepSound(String beepSound) {
		this.beepSound = beepSound;
	}

	public String getMusicSound() {
		return musicSound;
	}

	public void setMusicSound(String musicSound) {
		this.musicSound = musicSound;
	}

	public String getRunnerName() {
		return runnerName;
	}

	public void setRunnerName(String runnerName) {
		this.runnerName = runnerName;
	}

	public Preference() {
	}

	public Preference(int distance, int initialTimeInterval, int decrementRate,
			String beepSound, String musicSound, String runnerName) {

		this.distance = distance;
		this.initialTimeInterval = initialTimeInterval;
		this.decrementRate = decrementRate;
		this.beepSound = beepSound;
		this.musicSound = musicSound;
		this.runnerName = runnerName;
	}

	Preference(Parcel parcel) {
		distance = parcel.readInt();
		initialTimeInterval = parcel.readInt();
		decrementRate = parcel.readInt();
		beepSound = parcel.readString();
		musicSound = parcel.readString();
		runnerName = parcel.readString();

	}

	@Override
	public int describeContents() {

		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int arg1) {
		out.writeInt(distance);
		out.writeInt(initialTimeInterval);
		out.writeInt(decrementRate);
		out.writeString(beepSound);
		out.writeString(musicSound);
		out.writeString(runnerName);

	}

	public static final Parcelable.Creator<Preference> CREATOR = new Parcelable.Creator<Preference>() {

		public Preference createFromParcel(Parcel in) {
			return new Preference(in);
		}

		public Preference[] newArray(int size) {
			return new Preference[size];
		}
	};

}
