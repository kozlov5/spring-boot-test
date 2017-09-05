package enums;

import lombok.Getter;

/**
 * Created by Vladimir on 04.09.2017.
 */
public enum CarStatus {

	CRASH("Сломан", () -> {
		System.out.println("Машина поломана");
	}),
	NO_CRASH("На ходу", () -> {
		System.out.println("Машина в рабочем состоянии");
	});

	@Getter
	private String title;
	@Getter
	private Event event;

	CarStatus(String title, Event event) {
		this.title = title;
		this.event = event;
	}

	@Override
	public String toString() {
		return title;
	}
}
