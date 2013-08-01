package fr.jetoile.sample.springbatch.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.listener.ItemListenerSupport;

import java.util.List;

public class ItemFailureLoggerListener extends ItemListenerSupport {
	private static Logger LOGGER = LoggerFactory.getLogger(ItemFailureLoggerListener.class);

	@Override
	public void onProcessError(Object item, Exception e) {
		LOGGER.error("unable to process {}: {}", item, e.getMessage());
	}

	@Override
	public void onWriteError(Exception ex, List items) {
		for (Object item : items) {
			LOGGER.error("unable to write {}: {}", item, ex.getMessage());
		}
	}

	@Override
	public void onReadError(Exception ex) {
		LOGGER.error("unable to read", ex.getMessage());
	}



}

