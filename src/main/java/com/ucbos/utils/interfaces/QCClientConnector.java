package com.ucbos.utils.interfaces;

import java.io.IOException;

public interface QCClientConnector {

	public boolean getConnection();
	public boolean read();
	boolean write(String message) throws IOException;
	boolean write(byte[] message) throws IOException;
}
