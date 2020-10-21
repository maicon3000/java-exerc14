package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exception.DomainException;

public class Reservation {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;

	public Reservation() {
	}

	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
		Exceptions(checkIn, checkOut);
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}	
	
	public long Duration() {
		long diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public void updateDates(Date checkIn, Date checkOut) {
		Exceptions(checkIn, checkOut);
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	public void Exceptions(Date checkIn, Date checkOut) {
		Date now = new Date();
		if(checkIn.after(checkOut)) {
			throw new DomainException("Error in reservation: Check-out date must be after check-in date");
		} else if(checkIn.before(now)) {
			throw new DomainException("Error in reservation: Reservation dates must be future dates");
		}
	}

	@Override
	public String toString() {
		return "\nReservation: Room "
				+ roomNumber
				+ ", Check-In: "
				+ sdf.format(checkIn)
				+ ", Check-Out: "
				+ sdf.format(checkOut)
				+ ", "
				+ Duration()
				+ " nights";
	}
}
