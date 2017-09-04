package enums;

import lombok.Getter;

/**
 * Created by Vladimir on 04.09.2017.
 */
public enum CarStatus {

	CRASH("Машина поломана", () -> {
		System.out.println("Машина поломана");
	}),
	NO_CRASH("Машина в рабочем состоянии", () -> {
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
}
